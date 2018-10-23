package ua.prozorro.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AnGo on 01.03.2017.
 */
public class Common {
    public static void SaveToFile(String content, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();

    }
}
