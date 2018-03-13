package com.tenmaker.backupwd.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File openFile(String fullName) throws IOException {
        File f = new File(fullName);
        if (!f.exists())
            f.createNewFile();
        return f;
    }

    public static void writeTextFile(File f, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(f, false);
        fos.write(content.getBytes("UTF-8"));
        fos.close();
    }
}
