package com.yvesis.flyers.core.storage;

/**
 * Created by louly on 2017-03-07.
 */
public interface IFileManager {
    byte[] readAllBytes(String filename);

    boolean fileExists(String filename);

    boolean writeAllBytes(String filename, byte[] bytes);
}
