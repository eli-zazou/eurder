package be.cm.security.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> checkUserId(String username) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(find("username = ?1", username.toLowerCase()).singleResult());
        } catch (NoResultException noResultException) {
            final Logger logger = Logger.getLogger(UserRepository.class);
            logger.info("Username " + username + " not found");
            user = Optional.empty();
        }
        return user;
    }

    public void addUser(User user) {
        persist(user);

    }
}
