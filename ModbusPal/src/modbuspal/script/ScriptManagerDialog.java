/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModbusMasterDialog.java
 *
 * Created on 4 janv. 2009, 12:47:46
 */

package modbuspal.script;

import java.awt.Component;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modbuspal.main.ListLayout;
import modbuspal.main.ModbusPalGui;

/**
 *
 * @author avincon
 */
public class ScriptManagerDialog
extends javax.swing.JDialog
{
    private ModbusPalGui mainGui;
    private Vector<ScriptManager> scriptManagers;

    /** Creates new form ModbusMasterDialog */
    public ScriptManagerDialog(ModbusPalGui parent, Vector<ScriptManager>managers)
    {
        super(parent, false);
        mainGui = parent;
        scriptManagers = managers;
        initComponents();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        executeAllButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        scriptsListScrollPane = new javax.swing.JScrollPane();
        scriptsListPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Scripts manager");
        setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton);

        executeAllButton.setText("Execute all");
        executeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeAllButtonActionPerformed(evt);
            }
        });
        jPanel1.add(executeAllButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        statusLabel.setText(".");
        jPanel2.add(statusLabel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        scriptsListPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        scriptsListPanel.setLayout(null);
        scriptsListPanel.setLayout( new ListLayout() );
        scriptsListScrollPane.setViewportView(scriptsListPanel);

        getContentPane().add(scriptsListScrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * this function is triggered when the user clicks on the "add"
     * button. it will dispay a dialog so that the user can add a
     * new modbus request.
     * @param evt
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        // open a file chooser dialog in order to select a python file
        JFileChooser openDialog = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Python file", "py");
        openDialog.setFileFilter(filter);
        openDialog.showOpenDialog(this);

        File pythonFile = openDialog.getSelectedFile();
        if( pythonFile==null )
        {
            setStatus("Cancelled by user.");
            return;
        }

        // Create a new Script manager for this script:
        ScriptManager manager = new ScriptManager(pythonFile);

        // Create a new panel to display in the list
        addScriptManager(manager);
    }//GEN-LAST:event_addButtonActionPerformed

    private void executeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeAllButtonActionPerformed

        Component panels[] = scriptsListPanel.getComponents();
        for(int i=0; i<panels.length; i++)
        {
            if( panels[i] instanceof ScriptManagerPanel )
            {
                ScriptManagerPanel man = (ScriptManagerPanel)panels[i];
                man.execute();
            }
        }

    }//GEN-LAST:event_executeAllButtonActionPerformed


    private void addScriptManager(ScriptManager manager)
    {
        // add manager to list
        scriptManagers.add(manager);

        // create a panel for this manager
        ScriptManagerPanel panel = new ScriptManagerPanel(manager);
        manager.addScriptListener(panel);
        scriptsListPanel.add(panel);

        // refresh list
        scriptsListScrollPane.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton executeAllButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel scriptsListPanel;
    private javax.swing.JScrollPane scriptsListScrollPane;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    private void setStatus(String text)
    {
        statusLabel.setText(text);
    }

}
