package com.automation.practice.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            try {
                FileInputStream fis = new FileInputStream(
                    "src/test/resources/config.properties"
                );
                properties = new Properties();
                properties.load(fis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }
}
