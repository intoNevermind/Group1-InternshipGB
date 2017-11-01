package gb.internship.rest.db;

import gb.internship.rest.db.initialization.PostgreSqlInitialization;
import gb.internship.rest.db.initialization.SqLiteInitialization;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Properties;


/**
 * Обёртка над базой для получения Connection.
 *
 * @author Aleksandr Vvedensk
 */
public class DbWrapper {
    private Log LOG = LogFactory.getLog(DbWrapper.class);
    private static final String CONFIG_FILE = "pgsql_conf.properties";
    private String driverName;
    private String connectionString;
    private String username;
    private String password;

    private static volatile DbWrapper instance;

    private Connection connection;

    /**
     * Ищет конфиг. Если находит - инициализирует коннект к PostgreSQL. Иначе к SQLite
     */
    private DbWrapper() {
        if (readConnectionParamFromFile() && sqlConnectionParamValidation()) {
            LOG.info("Connection initialized as PostgreSQL");
            this.connection = new PostgreSqlInitialization(driverName, connectionString, username, password).getConnection();
        } else {
            LOG.info("Connection initialized as SQLite");
            this.connection = new SqLiteInitialization().getConnection();
        }
    }

    /**
     * Используем паттерн Singletone.
     *
     * @return экземпляр объекта.
     */
    public static DbWrapper getInstance() {
        if (instance == null) {
            synchronized (DbWrapper.class) {
                if (instance == null) {
                    instance = new DbWrapper();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Читает парамерты подключения к базе из конфига.
     *
     * @return true, если получилось прочитать файл. Иначе false.
     */
    private boolean readConnectionParamFromFile() {
        Path path = Paths.get(CONFIG_FILE);
        if (Files.exists(path) && !Files.isDirectory(path)) {
            LOG.info("Config file " + CONFIG_FILE + " exist. Read config.");
            Properties property = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
                property.load(fileInputStream);
                driverName = property.getProperty("db.driverName");
                connectionString = property.getProperty("db.connectionString");
                username = property.getProperty("db.username");
                password = property.getProperty("db.password");
            } catch (IOException e) {
                LOG.warn("Config file " + CONFIG_FILE + " not read.");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет заполненность параметров подключения к базе.
     *
     * @return true, если все параметры заполнены. Иначе false.
     */
    private boolean sqlConnectionParamValidation() {
        if (driverName == null) {
            LOG.warn("driverName is null");
            return false;
        }
        if (connectionString == null) {
            LOG.warn("connectionString is null");
            return false;
        }
        if (username == null) {
            LOG.warn("username is null");
            return false;
        }
        if (password == null) {
            LOG.warn("password is null");
            return false;
        }
        return true;
    }
}
