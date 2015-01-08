package com.eklib.desktopviewer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by alex on 06.01.2015.
 */
public class SnapshotUtil {

    /**
     * Read bytes from image
     *
     * @param fileName
     * @return
     */
    public static byte[] readByteArrayFromFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            //ToDO: logging info about file doesn't save
            return new byte[0];
        }
    }
}
