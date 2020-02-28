package ua.prozorro.properties;

import ua.prozorro.language.Messages;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;


public class AppResources {
    private static final String CONFIG_FILE_NAME = "Prozorro.properties";

    private Properties properties;
    private ResourceBundle messages;


    public void initResources(final Class<?> c) throws IOException {
        initProperties(c);
        initResourceBundle();
    }

    private void initProperties(final Class<?> c) throws IOException {
        File propertiesFile = FileUtils.getFileWithName(this.getClass(), CONFIG_FILE_NAME);

        properties = PropertiesUtils.getPropertiesFromFile(propertiesFile);

        PropertiesUtils.toString(properties);

    }

    private void initResourceBundle() {
        Messages messages = new Messages();
        this.messages = messages.getResourceBundle(PropertiesUtils.getPropertyString(properties, "app.language"));
    }

    public Properties getProperties() {
        return properties;
    }

    public ResourceBundle getMessages() {
        return messages;
    }
}
