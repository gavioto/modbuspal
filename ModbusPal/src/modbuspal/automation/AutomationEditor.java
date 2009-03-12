/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutomationEditor.java
 *
 * HISTORY:
 * - RandomGenerator has been added
 * - Do not implement GeneratorListener anymore.
 *
 * Created on 21 déc. 2008, 18:46:08
 */

package modbuspal.automation;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import modbuspal.automation.linear.LinearGenerator;
import modbuspal.automation.random.RandomGenerator;
import modbuspal.main.ListLayout;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author avincon
 */
public class AutomationEditor
extends javax.swing.JDialog
implements AutomationStateListener, AutomationValueListener
{
    private Automation automation = null;
    private ListLayout listLayout;

    /** Creates new form AutomationEditor */
    public AutomationEditor(java.awt.Frame frame, Automation parent)
    {
        super(frame, false);
        setTitle( "Automation:"+parent.getName() );
        automation = parent;
        listLayout = new ListLayout();
        initComponents();
        addAlreadyExistingGeneratorsToList();
        automation.addAutomationStateListener(this);
        automation.addAutomationValueListener(this);
        //automation.addGeneratorListener(this);
    }

    void down(GeneratorRenderer source)
    {
        Generator gen = source.getGenerator();
        automation.down(gen);
    }

    void remove(GeneratorRenderer renderer)
    {
        // get the generator
        Generator gen = renderer.getGenerator();

        // remove generator from the list
        automation.removeGenerator(gen);
    }

    void up(GeneratorRenderer source)
    {
        Generator gen = source.getGenerator();
        automation.up(gen);
    }

    private void addAlreadyExistingGeneratorsToList()
    {
        Generator generators[]=automation.getGenerators();
        if(generators==null)
        {
            return;
        }

        for(int i=0; i<generators.length; i++)
        {
            GeneratorRenderer renderer = new GeneratorRenderer(this, generators[i]);
            generators[i].addGeneratorListener(renderer);
            generatorsListPanel.add( renderer, new Integer(i) );
        }
        generatorsListScrollPane.validate();
    }

    private void exportAutomation(File target)
    throws FileNotFoundException, IOException
    {
        OutputStream out = new FileOutputStream(target);
        exportAutomation(out);
        out.close();
    }

    private void exportAutomation(OutputStream out)
    throws IOException
    {
        String openTag = "<modbuspal_automation>\r\n";
        out.write( openTag.getBytes() );
        
        automation.save(out);

        String closeTag = "</modbuspal_automation>\r\n";
        out.write( closeTag.getBytes() );
    }

    private GeneratorRenderer findComponent(Generator g1)
    {
        Component comps[] = generatorsListPanel.getComponents();
        for(int i=0; i<comps.length; i++)
        {
            if( comps[i] instanceof GeneratorRenderer )
            {
                GeneratorRenderer renderer = (GeneratorRenderer)comps[i];
                if( renderer.getGenerator() == g1 )
                {
                    return renderer;
                }
            }
        }
        return null;
    }

    private void importAutomation(Document doc)
    throws InvalidClassException
    {
        NodeList slaveNodes = doc.getElementsByTagName("automation");
        if( slaveNodes.getLength()==1 )
        {
            importAutomation(slaveNodes.item(0));
        }
        else
        {
            //TODO:
//            ImportSlaveDialog dialog = new ImportSlaveDialog(mainGui, slaveNodes);
//            dialog.setVisible(true);
//            Node data = dialog.getImport();
//            if( data != null )
//            {
//                importSlave(data);
//            }
        }
    }

    private void importAutomation(File importFile)
    throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(importFile);

        // normalize text representation
         doc.getDocumentElement().normalize();

         importAutomation(doc);
    }

    private void importAutomation(Node item)
    throws InvalidClassException
    {
        NamedNodeMap attributes = item.getAttributes();
        NodeList content = item.getChildNodes();
        automation.loadAttributes(attributes);
        automation.loadGenerators(content);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        generatorsListScrollPane = new javax.swing.JScrollPane();
        generatorsListPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        generatorsPanel = new javax.swing.JPanel();
        linearButton = new javax.swing.JButton();
        randomButton = new javax.swing.JButton();
        controlsPanel = new javax.swing.JPanel();
        playToggleButton = new javax.swing.JToggleButton();
        stopButton = new javax.swing.JButton();
        loopToggleButton = new javax.swing.JToggleButton();
        valueTextField = new javax.swing.JTextField();
        stepTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        initTextField = new javax.swing.JTextField();
        importExportPanel = new javax.swing.JPanel();
        importButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        generatorsListScrollPane.setPreferredSize(new java.awt.Dimension(300, 250));

        generatorsListPanel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        generatorsListPanel.setLayout(null);
        generatorsListPanel.setLayout(listLayout);
        generatorsListScrollPane.setViewportView(generatorsListPanel);

        getContentPane().add(generatorsListScrollPane, java.awt.BorderLayout.CENTER);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        generatorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add generators"));
        generatorsPanel.setLayout(new java.awt.GridBagLayout());

        linearButton.setText("Linear");
        linearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linearButtonActionPerformed(evt);
            }
        });
        generatorsPanel.add(linearButton, new java.awt.GridBagConstraints());

        randomButton.setText("Random");
        randomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });
        generatorsPanel.add(randomButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(generatorsPanel, gridBagConstraints);

        controlsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));
        controlsPanel.setLayout(new java.awt.GridBagLayout());

        playToggleButton.setText("Play");
        playToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playToggleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        controlsPanel.add(playToggleButton, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        controlsPanel.add(stopButton, gridBagConstraints);

        loopToggleButton.setSelected(automation.isLoopEnabled());
        loopToggleButton.setText("Loop");
        loopToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loopToggleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        controlsPanel.add(loopToggleButton, gridBagConstraints);

        valueTextField.setEditable(false);
        valueTextField.setText("000.0000");
        valueTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        controlsPanel.add(valueTextField, gridBagConstraints);

        stepTextField.setText(String.valueOf(automation.getStepDelay()));
        stepTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        stepTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stepTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        controlsPanel.add(stepTextField, gridBagConstraints);

        jLabel1.setText("Value:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        controlsPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Step:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        controlsPanel.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Init:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        controlsPanel.add(jLabel3, gridBagConstraints);

        initTextField.setText(String.valueOf( automation.getInitialValue() ));
        initTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                initTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        controlsPanel.add(initTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(controlsPanel, gridBagConstraints);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        importExportPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        importButton.setText("Import");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });
        importExportPanel.add(importButton);

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });
        importExportPanel.add(exportButton);

        getContentPane().add(importExportPanel, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void linearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linearButtonActionPerformed
        LinearGenerator linear = new LinearGenerator();
        automation.addGenerator(linear);
}//GEN-LAST:event_linearButtonActionPerformed

    private void playToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playToggleButtonActionPerformed

        if( playToggleButton.isSelected() )
        {
            playAutomation();
        }
        else
        {
            pauseAutomation();
        }
    }//GEN-LAST:event_playToggleButtonActionPerformed


    private void playAutomation()
    {
        if( automation.isSuspended() )
        {
            automation.resume();
        }
        else
        {
            automation.start();
        }
    }

    private void pauseAutomation()
    {
        playToggleButton.setText("Play");
        automation.suspend();
    }

    private void stopAutomation()
    {
        automation.stop();
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        stopAutomation();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void loopToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loopToggleButtonActionPerformed
        automation.setLoopEnabled( loopToggleButton.isSelected() );
    }//GEN-LAST:event_loopToggleButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed

        // create and setup dialog
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Automation export file (*.xmpa)", "xmpa");
        chooser.setFileFilter(filter);

        chooser.showDialog(this, "Export");
        File target = chooser.getSelectedFile();
        if( target != null )
        {
            try
            {
                exportAutomation(target);
            } 
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(AutomationEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(AutomationEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void stepTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stepTextFieldFocusLost
        String exp = stepTextField.getText();
        double val = Double.parseDouble(exp);
        automation.setStepDelay(val);
    }//GEN-LAST:event_stepTextFieldFocusLost

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed

        // create and setup dialog
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Automation export file (*.xmpa)", "xmpa");
        chooser.setFileFilter(filter);

        chooser.showDialog(this, "Import");
        File target = chooser.getSelectedFile();
        if( target == null )
        {
            return;
        }
        try {
            importAutomation(target);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AutomationEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AutomationEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AutomationEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_importButtonActionPerformed

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomButtonActionPerformed
        RandomGenerator random = new RandomGenerator();
        automation.addGenerator(random);
}//GEN-LAST:event_randomButtonActionPerformed

    private void initTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_initTextFieldFocusLost
        String val = initTextField.getText();
        double dval = Double.parseDouble(val);
        automation.setInitialValue( dval );
    }//GEN-LAST:event_initTextFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JButton exportButton;
    private javax.swing.JPanel generatorsListPanel;
    private javax.swing.JScrollPane generatorsListScrollPane;
    private javax.swing.JPanel generatorsPanel;
    private javax.swing.JButton importButton;
    private javax.swing.JPanel importExportPanel;
    private javax.swing.JTextField initTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton linearButton;
    private javax.swing.JToggleButton loopToggleButton;
    private javax.swing.JToggleButton playToggleButton;
    private javax.swing.JButton randomButton;
    private javax.swing.JTextField stepTextField;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables

    public void automationHasEnded(Automation source)
    {
        if( source == automation )
        {
            // disable any button in "Add Generators" panel
            Component buttons[] = generatorsPanel.getComponents();
            for(int i=0; i<buttons.length; i++)
            {
                buttons[i].setEnabled(true);
            }

            // enable any control panel in "Generators list"
            Component generators[] = generatorsListPanel.getComponents();
            for(int i=0; i<generators.length; i++)
            {
                generators[i].setEnabled(true);
            }

            playToggleButton.setText("Play");
            playToggleButton.setSelected(false);

            // enable any input in "Controls" list
            Component inputs[] = controlsPanel.getComponents();
            for(int i=0; i<inputs.length; i++)
            {
                if( inputs[i] instanceof JTextField )
                {
                    ((JTextField)inputs[i]).setEditable(true);
                }
            }
        }
    }

    public void automationHasStarted(Automation aThis)
    {
        // disable any button in "Add Generators" panel
        Component buttons[] = generatorsPanel.getComponents();
        for(int i=0; i<buttons.length; i++)
        {
            buttons[i].setEnabled(false);
        }

        // disable any control panel in "Generators list"
        Component generators[] = generatorsListPanel.getComponents();
        for(int i=0; i<generators.length; i++)
        {
            generators[i].setEnabled(false);
        }

        playToggleButton.setText("Pause");

        // disable any input in "Controls" list
        Component inputs[] = controlsPanel.getComponents();
        for(int i=0; i<inputs.length; i++)
        {
            if( inputs[i] instanceof JTextField )
            {
                ((JTextField)inputs[i]).setEditable(false);
            }
        }
    }

    public void automationValueHasChanged(Automation source, double value)
    {
        valueTextField.setText( String.valueOf(value) );
        valueTextField.validate();
    }

    public void automationNameHasChanged(Automation aThis, String newName)
    {
        setTitle( "Automation:"+newName );
    }

    public void generatorHasBeenAdded(Automation source, Generator generator, int index)
    {
        // add slave panel into the gui and refresh gui
        GeneratorRenderer renderer = new GeneratorRenderer(this, generator);
        generator.addGeneratorListener(renderer);
        generatorsListPanel.add( renderer, new Integer(index) );
        generatorsListScrollPane.validate();
    }

    public void automationLoopEnabled(Automation aThis, boolean enabled)
    {
        loopToggleButton.setSelected(enabled);
    }

    public void automationStepHasChanged(Automation aThis, double step)
    {
        stepTextField.setText( String.valueOf(step) );
    }

    public void generatorHasBeenRemoved(Automation source, Generator generator)
    {
        // remove generator's panel from the gui and refresh gui
        generatorsListPanel.remove( findComponent(generator) );
        generatorsListScrollPane.validate();
        generatorsListScrollPane.repaint();
    }

    public void generatorsHaveBeenSwapped(Automation source, Generator g1, Generator g2)
    {
        Component comp1 = findComponent(g1);
        Component comp2 = findComponent(g2);
        listLayout.swapComponents(comp1,comp2);
        //generatorsListPanel.validate();
        generatorsListPanel.doLayout();
        //generatorsListScrollPane.repaint();
    }

    public void automationInitialValueChanged(Automation aThis, double init)
    {
        initTextField.setText( String.valueOf(init) );
    }

//    public void generatorsHaveAllBeenRemoved(Automation aThis)
//    {
//        generatorsListPanel.removeAll();
//        generatorsListScrollPane.validate();
//    }
}
