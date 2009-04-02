/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ErrorMessage.java
 *
 * Created on 3 janv. 2009, 21:54:50
 */

package modbuspal.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author nnovic
 */
public class ErrorMessage
extends javax.swing.JDialog
implements ActionListener
{
    private JButton buttons[];
    private int selectedButton = -1;


    /** Creates new form ErrorMessage */
    public ErrorMessage(int nbButtons)
    {
        super(ModbusPalGui.getFrame(), true);
        create(nbButtons);
    }

    /** Creates new form ErrorMessage */
    public ErrorMessage(java.awt.Window parent, int nbButtons)
    {
        super(parent, ModalityType.DOCUMENT_MODAL);
        create(nbButtons);
    }

    public ErrorMessage(java.awt.Window parent, String text)
    {
        super(parent, ModalityType.DOCUMENT_MODAL);
        create(1);
        setButton(0, text);
    }


    private void create(int nbButtons)
    {
        initComponents();

        buttons = new JButton[nbButtons];
        for(int i=0; i<nbButtons; i++)
        {
            buttons[i] = new JButton("Button "+ String.valueOf(i+1) );
            buttons[i].addActionListener(this);
            buttonsPanel.add(buttons[i]);
        }
        validate();
        pack();
    }

    public void append(String text)
    {
        messageTextArea.append(text);
    }

    public void setButton(int i, String text)
    {
        buttons[i].setText(text);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        buttonsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        messageTextArea.setColumns(50);
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setRows(5);
        messageTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(messageTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageTextArea;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        for(int i=0; i<buttons.length; i++)
        {
            if( buttons[i] == source )
            {
                selectedButton = i;
                setVisible(false);
            }
        }
    }

    public int getButton()
    {
        return selectedButton;
    }
}
