package ma.formations.spring.resttemplatefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResttemplateFrontApplication extends SpringBootServletInitializer{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResttemplateFrontApplication.class);
    }
    public static void main(String[] args) {

        SpringApplication.run(ResttemplateFrontApplication.class, args);
        System.out.println("Application démarrée");
    }

}
