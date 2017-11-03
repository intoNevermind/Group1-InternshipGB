package gb.internship.rest.security;

import gb.internship.rest.db.operations.UserAuthentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;


/**
 * Custom Authentication Provider
 * http://www.baeldung.com/spring-security-multiple-auth-providers
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private Log LOG = LogFactory.getLog(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        UserAuthentication userAuthentication = new UserAuthentication();

        try {
            if (userAuthentication.isUserCorrect(username, password)) {
                return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            } else {
                throw new BadCredentialsException("External system authentication failed");
            }
        } catch (SQLException ex) {
            LOG.warn("Error during validating user in DB.");
            ex.printStackTrace();
            throw new BadCredentialsException("External system authentication failed");
        }


    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
