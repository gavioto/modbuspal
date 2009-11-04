/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ScriptRunnerPanel.java
 *
 * Created on 30 mars 2009, 13:51:06
 */

package modbuspal.script;

import modbuspal.instanciator.Instanciator;
import modbuspal.generator.GeneratorFactory;
import modbuspal.main.ModbusPal;

/**
 *
 * @author nnovic
 */
public class ScriptRunnerPanel
extends javax.swing.JPanel
{
    private ScriptRunner runner;

    /** Creates new form ScriptRunnerPanel */
    public ScriptRunnerPanel(ScriptRunner def, boolean canExecute)
    {
        runner = def;
        initComponents();
        if( !canExecute )
        {
            remove(executeButton);
        }
    }

    boolean contains(ScriptRunner sr)
    {
        return (sr==runner);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classnameLabel = new javax.swing.JLabel();
        executeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        classnameLabel.setText(runner.getClassName());
        classnameLabel.setToolTipText(runner.getPath());
        add(classnameLabel);

        executeButton.setText("Execute");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        add(executeButton);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // add the handler to the factory:
        ModbusPal.removeAllGenerators(runner.getClassName());
        GeneratorFactory.getFactory().remove(runner);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        runner.execute();
    }//GEN-LAST:event_executeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel classnameLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton executeButton;
    // End of variables declaration//GEN-END:variables

}
