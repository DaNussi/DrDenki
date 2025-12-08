package net.nussi.denki.temp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "net.nussi.denki.temp")
@Getter
@Setter
public class TempConfig {
    private String username;
    private String password;
}
