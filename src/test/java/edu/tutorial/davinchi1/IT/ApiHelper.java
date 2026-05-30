package edu.tutorial.davinchi1.IT;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class ApiHelper {

    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .basicAuthentication("user", "1234")
                .connectTimeout(java.time.Duration.ofSeconds(10))

        );
    }
}
