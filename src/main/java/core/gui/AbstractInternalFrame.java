package core.gui;

import javax.swing.JInternalFrame;

/**
 *
 * @author Rene Vera Apale
 */
public abstract class AbstractInternalFrame extends JInternalFrame {

    private final AbstractMainAppFrame mainFrame;
    
    public AbstractInternalFrame(AbstractMainAppFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    public abstract void prepareForDisplay();
}