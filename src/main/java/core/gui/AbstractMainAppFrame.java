package core.gui;

import core.config.AppConfig;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author Rene Vera Apale
 */
public class AbstractMainAppFrame extends JFrame {

    protected AppConfig configuration;
    
    protected final HashMap<String, AbstractInternalFrame> FRAMES = new HashMap<>();
    
    public void setConfig(AppConfig cfg) {
        configuration = cfg;
    }
    
    public AppConfig getConfig() {
        return configuration;
    }
}