package ru.skillbox.adminpanel;

import jakarta.servlet.http.Cookie;
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
import ru.skillbox.adminpanel.entity.Admin;
import ru.skillbox.adminpanel.repository.AdminRepository;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ALL")
@SpringBootTest
@ContextConfiguration(initializers = {StatisticsControllerTest.Initializer.class})
@Testcontainers
@AutoConfigureMockMvc
@Sql(value = {"/auth-before-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

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
    void getCurrentUserInfoRsTest() throws Exception {

        Admin admin = adminRepository.findByAdminLoginIgnoreCase("test@mail.com").get();
        String token = jwtTokenUtils.generateToken(admin);
        Cookie cookie = new Cookie("jwtToken", token);


        this.mockMvc.perform(get("/admin-console/statistics")
                        .cookie(cookie))
                .andDo(print())
                .andExpect(status().isOk());
                /*.andExpect(xpath("/html/body/div/div/div[2]/main/div[3]/div/div/p").exists())
                .andExpect(xpath("/html/body/div/div/div[2]/main/div[2]/div/div/div[1]/span[2]").exists())
                .andExpect(xpath("/html/body/div/div/div[2]/main/div[1]/div/div/div[1]/span[2]").exists())
                .andExpect(xpath("/html/body/div/div/div[2]/main/div[4]/div/div/p").exists());*/
    }

}
