package net.nussi.denki.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "net.nussi.denki.login")
@Getter
@Setter
public class LoginConfig {

    private String url;

    private ElementConfig username;
    private ElementConfig password;
    private ElementConfig submit;

    public static class ElementConfig extends net.nussi.denki.common.ElementConfig { }

}
