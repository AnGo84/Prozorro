package ua.prozorro.utils;


import java.io.*;
import java.util.Properties;

/**
 * Created by AnGo on 23.05.2017.
 */
public class PropertyUtils {

    public static Properties getPropertiesFromFile(File file) throws IOException {
        if (file == null) {
            throw new FileNotFoundException("Properties' file '" + file.getPath() + "'  not found!");
        }
        Properties properties = new Properties();
        if (!file.exists()) {
            //System.out.println("Create file: " +file.getPath());
            file.createNewFile();
        } else {
            try (InputStream inputStream = new FileInputStream(file)) {
                //System.out.println("Read file: " +file.getPath());
                properties.load(inputStream);
            }
        }
        return properties;
    }

    private static void checkProperties(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Properties cannot be null!!!");
        }
    }

    public static String getPropertyString(Properties properties, String propertyName) {
        return properties.getProperty(propertyName, "");
    }

    public static int getPropertyInt(Properties properties, String propertyName, int defaultValue) {
        int value = defaultValue;
        if (getPropertyString(properties, propertyName) != null && getPropertyString(properties, propertyName).length() > 0) {
            value = Integer.parseInt(getPropertyString(properties, propertyName));
        }
        return value;
    }

    public static String toString(Properties properties) {

        final StringBuilder sb = new StringBuilder("Properties{").append("\n");
        for (final String name : properties.stringPropertyNames()) {
            sb.append(name).append(" : ").append(properties.getProperty(name)).append("\n");
        }
        sb.append('}');
        return sb.toString();
    }
}
