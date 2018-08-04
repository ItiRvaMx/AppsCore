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
 * Allows for simplified access to application global configuration through a properties
 * file on the local file system.
 * @author Rene Vera Apale
 */
public abstract class AppConfig {

    private static final Logger LOGGER = LogManager.getLogger("appLogger");
    
    private Properties props;
    private String propsDescrip, file;
    /**
     * Initializes the configuration using the specified application name, properties file
     * name, and path.
     * @param appName Brief description for the application
     * @param fileName Name of the properties file. If {@code null} is passed <i>defaultConfig.properties</i>
     * will be used
     * @param filePath Path to the properties file. If {@code null} is passed the value of system property
     * <i>user.home</i> will be used
     */
    public AppConfig(String appName, String fileName, String filePath) {
        this(appName, fileName, filePath, null);
    }
    /**
     * Initializes the configuration using the specified application name, properties file
     * name, and path, as well as the specified {@link Properties} object
     * @param appName Brief description for the application
     * @param fileName Name of the properties file. If {@code null} is passed <i>defaultConfig.properties</i>
     * will be used
     * @param filePath Path to the properties file. If {@code null} is passed the value of system property
     * <i>user.home</i> will be used
     * @param props Default set of key-value properties that will be loaded into the file.
     */
    public AppConfig(String appName, String fileName, String filePath, Properties props) {
        this.propsDescrip = "# Global configuration file for " + appName;
        if (fileName == null)
            fileName = "defaultConfig.properties";
        if (filePath == null)
            filePath = System.getProperty("user.home");
        this.file = filePath + File.separator + fileName;
        this.props = props;
    }
    /**
     * Performs the loading of the configured properties, using the file described by the name
     * and path pass as parameters in the constructor. First it attempts to read the file contents
     * as-is and then load the key-value properties. 
     * If that should fail the default properties are obtained via {@link AppConfig#getDefault() getDefault()} method
     * and then stored to the file system using the same file
     * @throws FileNotFoundException When the file is inaccessible somehow
     * @throws IOException When the there's an reading/writing error
     */
    public void load() throws FileNotFoundException, IOException {
        try {
            FileInputStream stream = new FileInputStream(file);
            if (this.props == null)
                this.props = new Properties();
            this.props.load(stream);
        } catch(Exception fex) {
            LOGGER.info("Configuration file not found. Loading default properties.", fex);
            if (this.props == null)
                this.props = getDefault();
        }
        props.store(new FileOutputStream(file), propsDescrip);
        LOGGER.info(String.format("Configuration file loaded from \"%s\"", file));
    }
    /**
     * Returns the value of the specified key
     * @param prop The key for which to fetch the value
     * @return The value of the specified key
     */
    public String getProperty(String prop) {
        return props.getProperty(prop);
    }
    /**
     * Sets the value for a key. If the key is not present, it's added; otherwise the
     * current value is replaced with the new one
     * @param prop
     * @param value 
     */
    public void setProperty(String prop, String value) {
        props.setProperty(prop, value);
    }
    /**
     * Saves the current key-value pairs into the file described by the path and name
     * passed as parameters in the constructor.
     */
    public void update() {
        LOGGER.info("Updating current properties with new values");
        if (this.props == null)
            this.props = getDefault();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            props.store(outputStream, propsDescrip);
            LOGGER.info("Configuration file was succesfully updated");
        } catch(Exception ex) {
            LOGGER.error("Configuration file could not be saved", ex);
        }
    }
    
    public abstract Properties getDefault();
}