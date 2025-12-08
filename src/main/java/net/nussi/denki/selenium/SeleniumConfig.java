package net.nussi.denki.selenium;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;


@Configuration
@ConfigurationProperties(prefix = "net.nussi.denki.selenium")
@Getter
@Setter
public class SeleniumConfig {

    private URL url;

}
