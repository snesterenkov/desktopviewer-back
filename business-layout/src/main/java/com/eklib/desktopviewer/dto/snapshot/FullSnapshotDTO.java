package com.eklib.desktopviewer.dto.snapshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by alex on 8/27/2015.
 */
public class FullSnapshotDTO extends SnapshotDTO {

    private byte[] contentFile;

    public byte[] getContentFile() throws IOException {
        Path path = Paths.get(getFileName());
        return Files.readAllBytes(path);
    }
}
