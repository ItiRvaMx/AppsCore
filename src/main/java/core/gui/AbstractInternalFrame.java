package core.gui;

import javax.swing.JInternalFrame;

/**
 * Enforces the creation of views for MDI application to use a single constructor,
 * which references the main parent.
 * @author Rene Vera Apale
 */
public abstract class AbstractInternalFrame extends JInternalFrame {

    protected final AbstractMainAppFrame mainFrame;
    /**
     * Initializes an internal frame with a reference to its main container
     * @param mainFrame The main container for the application.
     */
    public AbstractInternalFrame(AbstractMainAppFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    /**
     * Method executed just before the frame is displayed to the user.
     */
    public abstract void prepareForDisplay();
}