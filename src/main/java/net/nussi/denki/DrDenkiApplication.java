package net.nussi.denki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "net.nussi.denki.quizbot")
@SpringBootApplication
public class DrDenkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrDenkiApplication.class, args);
    }

}
