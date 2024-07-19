package com.dm.ui.automation.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReadConfig {

    Properties pro;
    public static Map<String, Object> envConfigMap = new HashMap<>();

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Error in loading configuration file " + e.getMessage());
        }

        envConfigMap = getEnvConfigMap();
    }

    //method to get website url either from config property file or environment variables retrieved.
    public String getApplicationURL() {
        if (String.valueOf(envConfigMap.get("baseURL")).equalsIgnoreCase("") || String.valueOf(envConfigMap.get("baseURL")) == "null") {
            return pro.getProperty("baseURL");
        }
        return String.valueOf(envConfigMap.get("baseURL"));
    }

    //method to get browser name either from config property file or environment variables retrieved.
    public String getBrowserName() {
        if (String.valueOf(envConfigMap.get("browserName")).equalsIgnoreCase("") || String.valueOf(envConfigMap.get("browserName")) == "null") {
            return pro.getProperty("browserName");
        }

        return String.valueOf(envConfigMap.get("browserName"));
    }

    //method to retrieve environment variables from maven command line arguments and store them in hashmap.
    private void setEnvironmentVariables(String args) {
        String[] keys = args.split("-D");
        for (String key : keys) {
            if (key.contains("=")) {
                String[] pair = key.trim().split("=");
                envConfigMap.put(pair[0].trim(), pair[1].trim());
            }
        }

    }

    //method to read and store all environment variables retrieved from command-line/jenkins/github
    private Map<String, Object> getEnvConfigMap() {
        Map<String, String> configMap = System.getenv();
        Set<String> keys = configMap.keySet();
        if (configMap.containsKey(ConfigConstants.MAVEN_ARGS)) {
            setEnvironmentVariables(configMap.get(ConfigConstants.MAVEN_ARGS));
        } else {
            for (String key : keys) {
                envConfigMap.put(key, configMap.get(key));
            }
        }

        //System.out.println("updated Environment Variables : " + envConfigMap);
        return envConfigMap;
    }


}





