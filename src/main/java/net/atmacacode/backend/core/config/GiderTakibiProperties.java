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

    private Storage storage = new Storage();
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

    public static class Storage{
        String root = "uploads";

        public String getRoot(){
            return root;
        }

        public void setRoot(String root){
            this.root = root;
        }

        String profile = "profile";

        public String getProfile(){
            return profile;
        }

        public void setProfile(String profile){
            this.profile = profile;
        }

    }

}
