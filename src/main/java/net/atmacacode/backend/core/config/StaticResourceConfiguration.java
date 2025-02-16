package net.atmacacode.backend.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Autowired
    GiderTakibiProperties giderTakibiProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = Paths.get(giderTakibiProperties.getStorage().getRoot()).toAbsolutePath().toString() + "/";
        registry.addResourceHandler("/assets/**").addResourceLocations("file:"+path).setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    @Bean
    CommandLineRunner createStorageDirs(){
        return args -> {
            createFolder(Paths.get(giderTakibiProperties.getStorage().getRoot()));
            createFolder(Paths.get(giderTakibiProperties.getStorage().getRoot(), giderTakibiProperties.getStorage().getProfile()));
        };
    }

    private void createFolder(Path path){
        File file = path.toFile();
        boolean isFolderExists = file.exists() && file.isDirectory();
        if(!isFolderExists){
            file.mkdir();
        }
    }

}
