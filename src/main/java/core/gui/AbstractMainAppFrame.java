package core.gui;

import core.config.AppConfig;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 * Class from which the application main frame must inherit in order to provide
 * the MDI functions and shared global configuration capabilities.
 * @author Rene Vera Apale
 */
public class AbstractMainAppFrame extends JFrame {

    protected AppConfig configuration;
    
    protected final HashMap<String, AbstractInternalFrame> FRAMES = new HashMap<>();
    /**
     * Sets the global configuration that can be shared to all the child frames.
     * @param cfg Global configuration object
     */
    public void setConfig(AppConfig cfg) {
        configuration = cfg;
    }
    /**
     * Returns the global configuration object that was previously set
     * @return the global configuration object
     */
    public AppConfig getConfig() {
        return configuration;
    }
}