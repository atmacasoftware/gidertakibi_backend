package net.atmacacode.backend.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "gidertakibi")
@Configuration
@Getter
@Setter
public class GiderTakibiProperties {

    private Email email;

    private Client client;

    public static record Email(
            String username,
            String password,
            String host,
            int port,
            String from
    ){}

    public static record Client(
            String host
    ){}

}
