package be.cm.security.services;

import be.cm.exceptions.UnauthorizatedException;
import be.cm.exceptions.UnknownUserException;
import be.cm.exceptions.WrongPasswordException;
import be.cm.security.DecodedCredentials;
import be.cm.security.Feature;
import be.cm.security.user.User;
import be.cm.security.user.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.Base64;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class SecurityService {
    private final UserRepository userRepository;
    private String errorMessage;
    private final Logger logger = Logger.getLogger(SecurityService.class);

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateAuthorization(@Nullable String authorization, Feature feature) {
        DecodedCredentials credentials = getUsernamePassword(Optional.ofNullable(authorization)
                .orElseThrow(() -> new UnauthorizatedException("You do not have authorization")));

        User user = userRepository.checkUserId(credentials.getUsername())
                .orElseThrow(()-> new UnknownUserException(String.format("User %s unknown.", credentials.getUsername())));

        if (!user.doesPasswordMatch(credentials.getPassword())) {
            errorMessage = String.format("Password does not match for user %s", credentials.getUsername());
            logger.errorf(errorMessage);
            throw new WrongPasswordException(errorMessage);
        }

        if (!user.canHaveAccessTo(feature)) {
            errorMessage = String.format("User %s does not have access to %s", credentials.getUsername(), feature);
            logger.errorf(errorMessage);
            throw new UnauthorizatedException(errorMessage);
        }
        return user;
    }

    private DecodedCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new DecodedCredentials(username, password);
    }
}