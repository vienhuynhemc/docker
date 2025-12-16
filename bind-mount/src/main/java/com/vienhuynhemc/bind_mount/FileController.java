/* vienhuynhemc */
package com.vienhuynhemc.bind_mount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/files")
public class FileController {

  private final Path storgePath;

  public FileController(@Value("${storage-dir}") Path storgePath) {
    this.storgePath = storgePath;
  }

  @GetMapping
  public ResponseEntity<String[]> getAllFiles() {
    return ResponseEntity.ok(storgePath.toFile().list());
  }

  @PostMapping
  public ResponseEntity<Void> createFile() {
    final Path newPath = storgePath.resolve(UUID.randomUUID().toString());

    try {
      if (!Files.exists(storgePath)) {
        Files.createDirectories(storgePath);
      }

      Files.writeString(newPath, "Hello world", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

      return ResponseEntity.created(newPath.toUri()).build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
