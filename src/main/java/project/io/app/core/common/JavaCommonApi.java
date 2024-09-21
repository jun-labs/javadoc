package project.io.app.core.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides common API functionalities such as health checks.
 */
@RequestMapping(path = "/api")
@RestController(value = "javaCommonApi")
public class JavaCommonApi {

    /**
     * Health check endpoint.
     *
     * @return A ResponseEntity with an HTTP 200 status.
     */
    @GetMapping(path = "/health-check/v1")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok()
            .build();
    }
}
