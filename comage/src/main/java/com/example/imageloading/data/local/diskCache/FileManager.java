package com.example.imageloading.data.local.diskCache;

import java.io.File;
import java.io.IOException;

public interface FileManager {

    File journal();

    void prepare() throws IOException;

    File get(String name);

    File accept(File extFile, String name) throws IOException;

    boolean exists(String name);

    void delete(String name) throws IOException;

}
