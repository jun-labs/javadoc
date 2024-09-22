package project.io.app.core.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.io.app.core.user.application.UserService;
import project.io.app.core.user.presentation.response.UserInfoResponse;

/**
 * Controller for handling read operations on users.
 * This controller provides an endpoint to retrieve user information by user ID.
 */
@RestController
@RequestMapping(path = "/api/users")
public class UserReadApi {

    private final UserService userService;

    public UserReadApi(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves user information by the given user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A ResponseEntity containing the user information.
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserInfoResponse> findById(@PathVariable(name = "userId") final Long userId) {
        userService.execute(userId);
        return ResponseEntity.ok(new UserInfoResponse(userId));
    }
}
