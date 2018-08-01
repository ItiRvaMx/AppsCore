package core.gui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  Rene Vera Apale
 */
public class MessageDialog extends JDialog {

    public enum Type {
        INFO,
        WARN,
        ERROR
    }

    private MessageDialog(Component parent) {
        super(getWindow(parent), ModalityType.APPLICATION_MODAL);
    }
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }
    /**
     * Asks the user to either accept, deny or cancel an action.
     * @param parent Component over which the dialog is displayed
     * @param title Text to display as dialog title
     * @param message Content to be displayed in the body of the dialog
     * @return An integer that represents the users choice and matches either one of
     * {@link JOptionPane#YES_OPTION YES_OPTION}, {@link JOptionPane#NO_OPTION NO OPTION}
     * or {@link JOptionPane#CANCEL_OPTION CANCEL_OPTION}
     */
    public static int requestConfirmation(Component parent, String title, String message) {
        return JOptionPane.showConfirmDialog(getWindow(parent), message, title, JOptionPane.YES_NO_CANCEL_OPTION);
    }
    /**
     * Displays a simple message to screen
     * @param parent Component over which the dialog is displayed
     * @param msgType Determines the icon that the dialog will show
     * @param title Text to display as dialog title
     * @param shortMessage Content to be displayed in the body of the dialog
     */
    public static void showMessage(Component parent, Type msgType, String title, String shortMessage) {
        int type;
        switch (msgType) {
            case ERROR:
                type = JOptionPane.ERROR_MESSAGE;
                break;
            case INFO:
                type = JOptionPane.INFORMATION_MESSAGE;
                break;
            default:
                type = JOptionPane.WARNING_MESSAGE;
        }
        JOptionPane.showMessageDialog(getWindow(parent), shortMessage, title, type);
    }
    /**
     * Displays a message to screen with an area dedicated to present detailed content.
     * Such area is collapsed by default, and can be expanded via a button.
     * @param parent Component over which the dialog is displayed
     * @param msgType Determines the icon that the dialog will show
     * @param title Text to display as dialog title
     * @param shortMessage Text displayed as a manner of header to the message
     * @param detail <p>Object that will be presented in the detail area. The process to determine
     * what is printed is related to the value passed in this parameter:<br/>
     * <ul><li>{@code null} will effectively display a simple dialog message.</li>
     * <li>An instance of {@link Throwable} will print a paragraph consisting of the class fully qualified name,
     * a carriage return and the value returned by the {@link Throwable#getMessage() getMessage()} method.
     * Such steps will be repeated until the {@link Throwable#getCause() getCause()} method returns {@code null}</li>
     * Passing an instance of {@link Throwable} wi</p>
     */
    public static void showMessage(Component parent, Type msgType, String title, String shortMessage, Object detail) {
        if (detail == null) { // A null detail effectively becomes a simple dialog message
            MessageDialog.showMessage(parent, msgType, title, shortMessage);
            return;
        }
        MessageDialog myMsg = new MessageDialog(parent);
        myMsg.initComponents();
        myMsg.setTitle(title);
        Icon msgIcon;
        switch (msgType) {
            case ERROR:
                msgIcon = UIManager.getIcon("OptionPane.errorIcon");
                break;
            case INFO:
                msgIcon = UIManager.getIcon("OptionPane.informationIcon");
                break;
            default:
                msgIcon = UIManager.getIcon("OptionPane.warningIcon");
        }
        myMsg.lblIcon.setIcon(msgIcon);
        if (parent != null)
            myMsg.applyComponentOrientation(parent.getComponentOrientation());

        myMsg.jscrException.setVisible(false);
        myMsg.getRootPane().setDefaultButton(myMsg.btnOk);
        myMsg.lblMessage.setText("<html>" + shortMessage + "</html>");
        
        StringBuilder sb = new StringBuilder();
        
        if (detail instanceof Throwable) {
            Throwable t = (Throwable) detail;
            while (t != null) {
                assemblyExceptionMessage(sb, t);
                t = t.getCause();
            }
        } else if (detail instanceof Throwable[]) {
            Throwable[] allExceptions = (Throwable[]) detail;
            for (Throwable except : allExceptions)
                assemblyExceptionMessage(sb, except);
        } else if (detail instanceof Object[]) {
            Object[] allObjects = (Object[]) detail;
            for (Object target : allObjects)
                assemblyObjectMessage(sb, target);
        } else if (detail instanceof String)
            sb.append(detail.toString());
        else
            assemblyObjectMessage(sb, detail);
        
        myMsg.txtContent.setText(sb.toString());
        myMsg.txtContent.setCaretPosition(0);
        myMsg.pack();
        
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        myMsg.setBounds((screenSize.width - 455) / 2, (screenSize.height - 250) / 2, 455, myMsg.getHeight());

        myMsg.setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        jscrException = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new java.awt.BorderLayout());

        lblMessage.setText("sfsd fsdd ");
        lblMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblMessage.setMaximumSize(new java.awt.Dimension(200, 100));
        jPanel4.add(lblMessage, java.awt.BorderLayout.NORTH);

        jscrException.setAlignmentX(0.0F);

        txtContent.setEditable(false);
        jscrException.setViewportView(txtContent);

        jPanel4.add(jscrException, java.awt.BorderLayout.CENTER);
        jPanel4.add(jPanel1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        lblIcon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(lblIcon, java.awt.BorderLayout.LINE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        btnOk.setText("Aceptar");
        btnOk.setMaximumSize(new java.awt.Dimension(100, 35));
        btnOk.setMinimumSize(new java.awt.Dimension(100, 35));
        btnOk.setPreferredSize(new java.awt.Dimension(100, 35));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel2.add(btnOk);

        btnDetail.setText("Detalle");
        btnDetail.setMaximumSize(new java.awt.Dimension(100, 35));
        btnDetail.setMinimumSize(new java.awt.Dimension(100, 35));
        btnDetail.setPreferredSize(new java.awt.Dimension(110, 35));
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jPanel2.add(btnDetail);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(455, 250));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        btnDetail.setEnabled(false);
        jscrException.setVisible(true);
        setSize(getWidth(), 310);
        validate();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jscrException;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTextArea txtContent;
    // End of variables declaration//GEN-END:variables

    private static void assemblyExceptionMessage(StringBuilder builder, Throwable except) {
        if (builder.length() > 0)
            builder.append("\n\n");
        builder.append(except.getClass())
                .append(": \n")
                .append(except.getMessage());
    }
    
    private static void assemblyObjectMessage(StringBuilder builder, Object target) {
        if (builder.length() > 0)
            builder.append("\n\n");
        builder.append(target.getClass())
                .append(": \n")
                .append(target);
    }
    
}