package net.atmacacode.backend.service.impl;

import net.atmacacode.backend.core.config.GiderTakibiProperties;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    GiderTakibiProperties giderTakibiProperties;

    Tika tika = new Tika();

    public String saveBase64StringAsFile(String image) {
        String filename = UUID.randomUUID().toString();

        Path path = getProfileImagePath(filename);
        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());
            outputStream.write(decodedImage(image));
            outputStream.close();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String detectType(String value) {
        return tika.detect(decodedImage(value));
    }

    private byte[] decodedImage(String encodedImage) {
        return Base64.getDecoder().decode(encodedImage.split(",")[1]);
    }

    public void deleteProfileImage(String image) {
        if(image == null) return;
        Path path = getProfileImagePath(image);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Path getProfileImagePath(String filename){
        return Paths.get(giderTakibiProperties.getStorage().getRoot(), giderTakibiProperties.getStorage().getProfile(), filename);
    }
}
