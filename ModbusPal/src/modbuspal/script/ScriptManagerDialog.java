/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ScriptManagerDialog.java
 *
 * Created on 24 mars 2009, 18:25:32
 */

package modbuspal.script;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modbuspal.generator.Instanciator;
import modbuspal.generator.GeneratorFactory;
import modbuspal.generator.InstanciatorListener;
import modbuspal.main.ErrorMessage;
import modbuspal.main.ListLayout;
import modbuspal.main.ModbusPal;


/**
 *
 * @author nnovic
 */
public class ScriptManagerDialog
extends javax.swing.JDialog
implements InstanciatorListener, ScriptListener
{
    private static final String REGISTRY_KEY = ModbusPal.BASE_REGISTRY_KEY + "/instanciators";
    public static final int TAB_GENERATORS = 2;
    
    /** Creates new form ScriptManagerDialog */
    public ScriptManagerDialog(java.awt.Frame parent)
    {
        super(parent, false);
        initComponents();
    }

    public void setSelectedTab(int tabIndex)
    {
        jTabbedPane1.setSelectedIndex(tabIndex);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        startupScriptsTab = new javax.swing.JPanel();
        startupScriptsScrollPane = new javax.swing.JScrollPane();
        startupScriptsList = new javax.swing.JPanel();
        startupScriptsButtons = new javax.swing.JPanel();
        addStartupScriptButton = new javax.swing.JButton();
        ondemandScriptsTab = new javax.swing.JPanel();
        ondemandScriptsScrollPane = new javax.swing.JScrollPane();
        ondemandScriptsList = new javax.swing.JPanel();
        ondemandScriptsButtons = new javax.swing.JPanel();
        addOndemandScriptButton = new javax.swing.JButton();
        generatorInstanciatorsTab = new javax.swing.JPanel();
        generatorInstanciatorsScrollPane = new javax.swing.JScrollPane();
        generatorInstanciatorsList = new javax.swing.JPanel();
        generatorInstanciatorButtons = new javax.swing.JPanel();
        addGeneratorInstanciatorButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Script Manager");
        setMinimumSize(new java.awt.Dimension(350, 250));

        startupScriptsTab.setLayout(new java.awt.BorderLayout());

        startupScriptsList.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        startupScriptsList.setLayout(null);
        startupScriptsList.setLayout( new ListLayout() );
        startupScriptsScrollPane.setViewportView(startupScriptsList);

        startupScriptsTab.add(startupScriptsScrollPane, java.awt.BorderLayout.CENTER);

        startupScriptsButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addStartupScriptButton.setText("Add");
        addStartupScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStartupScriptButtonActionPerformed(evt);
            }
        });
        startupScriptsButtons.add(addStartupScriptButton);

        startupScriptsTab.add(startupScriptsButtons, java.awt.BorderLayout.NORTH);

        jTabbedPane1.addTab("Startup scripts", startupScriptsTab);

        ondemandScriptsTab.setLayout(new java.awt.BorderLayout());

        ondemandScriptsList.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        ondemandScriptsList.setLayout(null);
        ondemandScriptsList.setLayout( new ListLayout() );
        ondemandScriptsScrollPane.setViewportView(ondemandScriptsList);

        ondemandScriptsTab.add(ondemandScriptsScrollPane, java.awt.BorderLayout.CENTER);

        ondemandScriptsButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addOndemandScriptButton.setText("Add");
        addOndemandScriptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOndemandScriptButtonActionPerformed(evt);
            }
        });
        ondemandScriptsButtons.add(addOndemandScriptButton);

        ondemandScriptsTab.add(ondemandScriptsButtons, java.awt.BorderLayout.NORTH);

        jTabbedPane1.addTab("On demand scripts", ondemandScriptsTab);

        generatorInstanciatorsTab.setLayout(new java.awt.BorderLayout());

        generatorInstanciatorsList.setBackground(javax.swing.UIManager.getDefaults().getColor("List.background"));
        generatorInstanciatorsList.setLayout(null);
        generatorInstanciatorsList.setLayout( new ListLayout() );
        generatorInstanciatorsScrollPane.setViewportView(generatorInstanciatorsList);

        generatorInstanciatorsTab.add(generatorInstanciatorsScrollPane, java.awt.BorderLayout.CENTER);

        generatorInstanciatorButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addGeneratorInstanciatorButton.setText("Add");
        addGeneratorInstanciatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGeneratorInstanciatorButtonActionPerformed(evt);
            }
        });
        generatorInstanciatorButtons.add(addGeneratorInstanciatorButton);

        generatorInstanciatorsTab.add(generatorInstanciatorButtons, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Generator scripts", generatorInstanciatorsTab);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        statusPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        statusLabel.setText(".");
        statusPanel.add(statusLabel);

        getContentPane().add(statusPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStartupScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStartupScriptButtonActionPerformed

        // get the selected file, in any.
        File scriptFile = chooseScriptFile(this);
        if( scriptFile==null )
        {
            setStatus("Cancelled by user.");
            return;
        }


        // add the handler to the factory:
        ModbusPal.addStartupScript(scriptFile);


    }//GEN-LAST:event_addStartupScriptButtonActionPerformed


    public static File chooseScriptFile(Component parent)
    {
        // get last used directory
        Preferences prefs = Preferences.userRoot();
        Preferences appPrefs = prefs.node(REGISTRY_KEY);
        String prev_dir = appPrefs.get("prev_dir", null);

        // newInstance the dialog
        JFileChooser fileChooser = new JFileChooser();

        // setup current directory if available
        if( prev_dir != null )
        {
            File cwd = new File(prev_dir);
            if( (cwd.isDirectory()==true) && (cwd.exists()==true) )
            {
                fileChooser.setCurrentDirectory(cwd);
            }
        }

        // newInstance a Python/Jython extension filter
        FileNameExtensionFilter pythonFilter = new FileNameExtensionFilter("Python file", "py");
        fileChooser.setFileFilter(pythonFilter);

        // display file chooser
        int choice = fileChooser.showDialog(parent, "Add");
        if( choice == JFileChooser.APPROVE_OPTION)
        {
            // get the directory that has been chosen
            File chosen = fileChooser.getCurrentDirectory();
            appPrefs.put("prev_dir", chosen.getPath());
            try {
                appPrefs.flush();
            } catch (BackingStoreException ex) {
                Logger.getLogger(GeneratorFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return fileChooser.getSelectedFile();
        }

        return null;
    }



    private void addGeneratorInstanciatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGeneratorInstanciatorButtonActionPerformed

        // get the selected file, in any.
        File scriptFile = chooseScriptFile(this);
        if( scriptFile==null )
        {
            setStatus("Cancelled by user.");
            return;
        }

        // newInstance a scripted generator handler
        ScriptRunner gen = ScriptRunner.create(scriptFile);

        // test if newInstance would work:
        if( gen.newInstance() != null )
        {
            // add the handler to the factory:
            GeneratorFactory.add(gen);
        }
        else
        {
            ErrorMessage dialog = new ErrorMessage(this,"Close");
            dialog.setTitle("Script error");
            dialog.append("The script probably contains errors and cannot be executed properly.");
            dialog.setVisible(true);
        }

    }//GEN-LAST:event_addGeneratorInstanciatorButtonActionPerformed

    private void addOndemandScriptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOndemandScriptButtonActionPerformed

        // get the selected file, in any.
        File scriptFile = chooseScriptFile(this);
        if( scriptFile==null )
        {
            setStatus("Cancelled by user.");
            return;
        }


        // add script to project
        ModbusPal.addScript(scriptFile);

}//GEN-LAST:event_addOndemandScriptButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGeneratorInstanciatorButton;
    private javax.swing.JButton addOndemandScriptButton;
    private javax.swing.JButton addStartupScriptButton;
    private javax.swing.JPanel generatorInstanciatorButtons;
    private javax.swing.JPanel generatorInstanciatorsList;
    private javax.swing.JScrollPane generatorInstanciatorsScrollPane;
    private javax.swing.JPanel generatorInstanciatorsTab;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel ondemandScriptsButtons;
    private javax.swing.JPanel ondemandScriptsList;
    private javax.swing.JScrollPane ondemandScriptsScrollPane;
    private javax.swing.JPanel ondemandScriptsTab;
    private javax.swing.JPanel startupScriptsButtons;
    private javax.swing.JPanel startupScriptsList;
    private javax.swing.JScrollPane startupScriptsScrollPane;
    private javax.swing.JPanel startupScriptsTab;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void instanciatorAdded(Instanciator def)
    {
        if( def instanceof ScriptRunner )
        {
            ScriptRunner si = (ScriptRunner)def;
            // create a new panel and add it
            ScriptRunnerPanel panel = new ScriptRunnerPanel(si,false);
            generatorInstanciatorsList.add(panel);
            generatorInstanciatorsScrollPane.validate();
        }
    }

    @Override
    public void instanciatorRemoved(Instanciator def)
    {
        if( def instanceof ScriptRunner )
        {
            ScriptRunner si = (ScriptRunner)def;
            scriptInstanciatorRemoved(si);
            return;
        }
    }

    private void scriptInstanciatorRemoved(ScriptRunner si)
    {
        int max = generatorInstanciatorsList.getComponentCount();
        for(int i=0; i<max; i++ )
        {
            Component comp = generatorInstanciatorsList.getComponent(i);
            if( comp instanceof ScriptRunnerPanel )
            {
                ScriptRunnerPanel sip = (ScriptRunnerPanel)comp;
                if( sip.contains(si)==true )
                {
                    generatorInstanciatorsList.remove(sip);
                }
            }
        }
        generatorInstanciatorsScrollPane.validate();
        repaint();
    }

    private void setStatus(String status)
    {
        statusLabel.setText(status);
    }

    @Override
    public void startupScriptAdded(ScriptRunner runner)
    {
        // create a new panel and add it
        ScriptRunnerPanel panel = new ScriptRunnerPanel(runner,true);
        startupScriptsList.add(panel);
        startupScriptsScrollPane.validate();
    }

    @Override
    public void scriptAdded(ScriptRunner runner)
    {
        // create a new panel and add it
        ScriptRunnerPanel panel = new ScriptRunnerPanel(runner,true);
        ondemandScriptsList.add(panel);
        ondemandScriptsScrollPane.validate();
    }

}
