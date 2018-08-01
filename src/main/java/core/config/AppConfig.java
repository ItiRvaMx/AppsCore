package core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Rene Vera Apale
 */
public abstract class AppConfig {

    private static final Logger LOGGER = LogManager.getLogger("appLogger");
    
    private Properties props;
    private String appName, file;
    
    public AppConfig(String appName, String fileName, String filePath) {
        this(appName, fileName, filePath, null);
    }
    
    public AppConfig(String appName, String fileName, String filePath, Properties props) {
        this.appName = appName;
        if (fileName == null)
            fileName = "defaultConfig.properties";
        if (filePath == null)
            filePath = System.getProperty("user.home");
        this.file = filePath + File.separator + fileName;
        this.props = props;
    }
    
    public void load() throws FileNotFoundException, IOException {
        try {
            FileInputStream stream = new FileInputStream(file);
            this.props = new Properties();
            this.props.load(stream);
        } catch(Exception fex) {
            LOGGER.info("Configuration file not found. Loading default properties.");
            if (this.props == null)
                this.props = getDefault();
        }
        props.store(new FileOutputStream(file), "# Archivo de configuración para " + appName);
        LOGGER.info(String.format("Configuration file loaded from \"%s\"", file));
    }
    
    public String getProperty(String prop) {
        return props.getProperty(prop);
    }
    
    public void setProperty(String prop, String value) {
        props.setProperty(prop, value);
    }
    
    public abstract Properties getDefault();
}