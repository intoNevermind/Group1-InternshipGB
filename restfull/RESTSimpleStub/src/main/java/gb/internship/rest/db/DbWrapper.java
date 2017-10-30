package gb.internship.rest.db;

import gb.internship.rest.db.initialization.SqLiteInitialization;

import java.sql.Connection;


/**
 * Обёртка над базой для получения Connection.
 *
 * @author Aleksandr Vvedensk
 */
public class DbWrapper {

    private static volatile DbWrapper instance;

    private Connection connection;

    private DbWrapper() {
        this.connection = new SqLiteInitialization().getConnection();
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
}
