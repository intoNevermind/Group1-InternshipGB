package gb.internship.rest.security;

import gb.internship.rest.db.operations.UserAuthentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Custom Authentication Provider
 * http://www.baeldung.com/spring-security-multiple-auth-providers
 *
 * @author Aleksandr Vvedensky
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private Log LOG = LogFactory.getLog(CustomAuthenticationProvider.class);

    /**
     * Проверяет корерктность логина/пароля и выдаёт права.
     *
     * @param username имя пользователя.
     * @param password пароль.
     */
    private List<GrantedAuthority> initGrantedAuthority(String username, String password) {
        UserAuthentication userAuthentication = new UserAuthentication();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        try {
            switch (userAuthentication.getUserAuthorityId(username, password)) {
                case 1:
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    break;
                case 2:
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    break;
                default:
                    throw new BadCredentialsException("External system authentication failed");
            }
        } catch (SQLException ex) {
            LOG.warn("Error during validating user in DB.");
            ex.printStackTrace();
            throw new BadCredentialsException("External system authentication failed");
        }
        return grantedAuthorities;
    }

    /**
     * Аутентификация и авторизация пользователя.
     *
     * @param auth AuthenticationManagerBuilder.
     * @return UsernamePasswordAuthenticationToken.
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        return new UsernamePasswordAuthenticationToken(username, password,
                initGrantedAuthority(username, password));
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
