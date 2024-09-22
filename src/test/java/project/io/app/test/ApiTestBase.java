package project.io.app.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public abstract class ApiTestBase {

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;

    @BeforeEach
    public void setup(final RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(restDocumentation))
            .alwaysDo(document("{class-name}/{method-name}"))
            .build();
    }
}
