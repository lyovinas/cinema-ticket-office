package ru.sbercourse.cinema.ticketoffice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FileHelper {

    @Value("${posters.path}")
    private String postersPath;


    public String createFile(MultipartFile file, String existingFileName, Long id) {
        String fileExtension = Objects.requireNonNull(file.getOriginalFilename())
                .split("\\.")[file.getOriginalFilename().split("\\.").length - 1];
        String newFileName = id + "." + fileExtension;

        try (InputStream inputStream = file.getInputStream()) {
            Path pathToNewFile = Paths.get(postersPath + "/" + newFileName).toAbsolutePath();
            if (!pathToNewFile.toFile().exists()) {
                Files.createDirectories(pathToNewFile);
            }
            Files.copy(inputStream, pathToNewFile, StandardCopyOption.REPLACE_EXISTING);

            if (existingFileName != null && !existingFileName.isBlank() && !newFileName.equals(existingFileName)) {
                Path pathToExistingFile = Paths.get(postersPath + "/" + existingFileName).toAbsolutePath();
                Files.deleteIfExists(pathToExistingFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newFileName;
    }
}
