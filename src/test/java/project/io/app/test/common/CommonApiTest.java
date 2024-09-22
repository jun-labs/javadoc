package project.io.app.test.common;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import project.io.app.test.ApiTestBase;

class CommonApiTest extends ApiTestBase {

    @Test
    void whenHealthCheckV1ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/health-check/v1")
                .contentType("application/json"))
            .andExpect(status().isOk());
    }

    @Test
    void whenHealthCheckV2ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/health-check/v2")
                .content("{\"serverId\": 1}")
                .contentType("application/json"))
            .andExpect(status().isOk());
    }
}
