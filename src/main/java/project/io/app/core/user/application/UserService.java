package project.io.app.core.user.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * User service class that handles user-related operations.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public void execute(final Long userId) {
        log.info("[UserService] userId:{}---------------------x>", userId);
    }
}
