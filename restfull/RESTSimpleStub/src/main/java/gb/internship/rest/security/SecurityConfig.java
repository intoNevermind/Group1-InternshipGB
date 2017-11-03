package gb.internship.rest.security;

import gb.internship.rest.db.DbWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * https://docs.spring.io/spring-security/site/docs/current/guides/html5/hellomvc-javaconfig.html
 * https://habrahabr.ru/post/245415/
 * https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");

//        auth.jdbcAuthentication().dataSource(DbWrapper.getInstance().getDataSource())
//                .usersByUsernameQuery("select username,password, enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
//        auth.jdbcAuthentication().dataSource(DbWrapper.getInstance().getDataSource())
//                .usersByUsernameQuery("SELECT \"Login\", \"Password\", \"Active\" FROM users WHERE \"Login\" = ?;");

        auth.authenticationProvider(customAuthProvider);
//        auth.inMemoryAuthentication()
//                .withUser("memuser")
//                .password("pass")
//                .roles("USER");;
    }

    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("user","pass,ROLE_USER,enabled"); //add whatever other user you need
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/unauthorized/**");
    }

}