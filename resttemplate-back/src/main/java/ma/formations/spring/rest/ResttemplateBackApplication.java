package ma.formations.spring.rest;

import ma.formations.spring.rest.domaine.EmpVo;
import ma.formations.spring.rest.service.IService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResttemplateBackApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(ResttemplateBackApplication.class, args);
        System.out.println("Application démarrée");
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResttemplateBackApplication.class);
    }
    @Bean
    public CommandLineRunner initDatabase(IService service) {
        return args -> {
            service.deleteAll();
            service.save(EmpVo.builder().firstName("ALAMI").salaire(10000D).fonction("INGENIEUR").build());
            service.save(EmpVo.builder().firstName("amrani").salaire(200000D).fonction("DIRECTEUR").build());
            service.save(EmpVo.builder().firstName("Jamali").salaire(3000D).fonction("Technicien").build());
            service.save(EmpVo.builder().firstName("KAOUTAR").salaire(20000D).fonction("INGENIEUR").build());
        };
    }

}
