package project.io.app.test.user;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import project.io.app.test.ApiTestBase;

class UserReadApiTest extends ApiTestBase {

    @Test
    void whenSearchUserByValidIdThenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/users/{userId}", 1))
            .andExpect(status().isOk());
    }

    @Test
    void whenSearchUserByInValidIdThenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/users/{userId}", "asdfsafa"))
            .andExpect(status().isBadRequest());
    }
}

