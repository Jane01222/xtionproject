package com.xuanwu.xtion.utils;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guan.jianming
 * @description 配置文件工具类，配置文件起始路径为/xtionframe-web/src/main/resources/，具有缓存配置文件的功能
 */
public class PropertiesUtil {
    // 缓存配置文件列表的集合对象
    private static Map<String, Properties> properties = null;

    /**
     * @author guan.jianming
     * @description 获取配置文件，并将其放入缓存内
     * @param fileName
     * @return
     */
    private static Properties getProperties(String fileName) {
        if (properties == null) {
            properties = new ConcurrentHashMap<String, Properties>();
        }
        if (properties.containsKey(fileName)) {
            return properties.get(fileName);
        } else {
            try {
                Properties property = new Properties();
                InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
                property.load(new BufferedReader(new InputStreamReader(is, "utf-8")));
                is.close();
                properties.put(fileName, property);
                return property;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @author guan.jianming
     * @description 根据配置文件名和属性名称获取值
     * @param fileName
     * @param propertyName
     * @return
     */
    public static String getPropertyValue(String fileName, String propertyName, String string) {
        String value = getPropertyValue(fileName, propertyName);
        return value == null ? string : value;
    }

    /**
     * @author guan.jianming
     * @description 根据配置文件名和属性名称获取值
     * @param fileName
     * @param propertyName
     * @return
     */
    public static String getPropertyValue(String fileName, String propertyName) {
        return getProperties(fileName).getProperty(propertyName);
    }

    /**
     *
     * @param fileName
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String fileName, String propertyName, boolean defaultValue) {
        boolean result = defaultValue;
        try {
            String value = getPropertyValue(fileName, propertyName);
            return value == null ? defaultValue : Boolean.parseBoolean(value);
        } catch (Exception e) {}

        return result;
    }

}
