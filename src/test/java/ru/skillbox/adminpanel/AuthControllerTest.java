package ru.skillbox.adminpanel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ALL")
@SpringBootTest
@ContextConfiguration(initializers = {AuthControllerTest.Initializer.class})
@Testcontainers
@AutoConfigureMockMvc
@Sql(value = {"/auth-before-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String testLogin = "test@mail.com";
    private final String testPassword = "1!g6k0f4";


    @Container
    public static PostgreSQLContainer<?> postgreSqlContainer =
            new PostgreSQLContainer<>("postgres:15.1")
                    .withDatabaseName("socialNet-test")
                    .withUsername("root")
                    .withPassword("root");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSqlContainer.getUsername(),
                    "spring.datasource.password=" + postgreSqlContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void successLoginTest() throws Exception {

        this.mockMvc.perform(post("/admin-console/login")
                        .param("email", testLogin)
                        .param("password", testPassword))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("statistics"));
    }

    @Test
    void wrongPasswordTest() throws Exception {

        this.mockMvc.perform(post("/admin-console/login")
                        .param("email", testLogin)
                        .param("password", "wrongPassword"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
