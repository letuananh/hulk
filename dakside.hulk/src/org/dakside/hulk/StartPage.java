/*
 * Copyright (C) 2014 Le Tuan Anh <tuananh.ke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dakside.hulk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import org.dakside.dao.AbstractDAOFactory;
import org.dakside.dao.ConnectionInfo;
import org.dakside.dao.DAOException;
import org.dakside.duck.appui.GroupModulePanel;
import org.dakside.duck.helpers.SwingHelper;
import org.dakside.duck.plugins.AppCentral;
import org.dakside.duck.plugins.FunctionPool;
import org.dakside.duck.plugins.Message;
import org.dakside.exceptions.ArgumentException;
import org.dakside.hulk.core.Configuration;
import org.dakside.hulk.core.models.HulkMessages;
import org.dakside.hulk.dal.DAOFactory;
import org.dakside.utils.SystemHelper;

/**
 *
 * @author Le Tuan Anh <tuananh.ke@gmail.com>
 */
public class StartPage extends JPanel {

    private static final Logger logger = Logger.getLogger(StartPage.class.getName());

    protected EventListenerList activationListener = new EventListenerList();

    public void addActiveStateChangedListener(ActionListener l) {
        activationListener.add(ActionListener.class, l);
    }

    public void removeActiveStateChangedListener(ActionListener l) {
        activationListener.remove(ActionListener.class, l);
    }

    private void fireActiveStateChanged(ActionEvent e) {
        if (e == null) {
            e = new ActionEvent(this, 0, "");
        }
        Object[] listeners = activationListener.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                ((ActionListener) listeners[i + 1]).actionPerformed(e);
            }
        }
    }

    public boolean isActivated() {
        return this.btnUnload.isEnabled();
    }

    /**
     * Creates new form StartPage
     */
    public StartPage() {
        initComponents();

        try {
            this.groupModulePanel.load();
        } catch (Exception ex) {
        }

        // Deactivate all module by default
        // Only activate them when we load the database
        groupModulePanel.deactivate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupModulePanel = new org.dakside.duck.appui.GroupModulePanel();
        panelDatabase = new javax.swing.JPanel();
        lblLocation = new javax.swing.JLabel();
        txtDBPath = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnUnload = new javax.swing.JButton();

        panelDatabase.setBorder(javax.swing.BorderFactory.createTitledBorder("Project File"));

        lblLocation.setText("Location");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnOpen.setText("Open Project");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnUnload.setText("Unload");
        btnUnload.setEnabled(false);
        btnUnload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatabaseLayout = new javax.swing.GroupLayout(panelDatabase);
        panelDatabase.setLayout(panelDatabaseLayout);
        panelDatabaseLayout.setHorizontalGroup(
            panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatabaseLayout.createSequentialGroup()
                        .addComponent(lblLocation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDBPath, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatabaseLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUnload, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelDatabaseLayout.setVerticalGroup(
            panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreate))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLocation)
                        .addComponent(txtDBPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(btnUnload))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupModulePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupModulePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        selectDBFile();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        createDBFile();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        loadDB();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnUnloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnloadActionPerformed
        // TODO add your handling code here:
        unloadDB();
    }//GEN-LAST:event_btnUnloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnUnload;
    private org.dakside.duck.appui.GroupModulePanel groupModulePanel;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JPanel panelDatabase;
    private javax.swing.JTextField txtDBPath;
    // End of variables declaration//GEN-END:variables

    public GroupModulePanel getGroupModulePanel() {
        return groupModulePanel;
    }

    // <editor-fold defaultstate="collapsed" desc="Database file management">
    /**
     * Get default database file location
     *
     * @return
     */
    private String getDefaultDBFile() {
        return SystemHelper.getHomeFolder() + SystemHelper.getPathSeparator()
                + "HULK_PROJECT.db";
    }

    /**
     * Get selected file
     *
     * @return
     */
    public File getCurrentFile() {
        try {
            File f = new File(txtDBPath.getText());
        } catch (Exception ex) {
        }
        return new File(getDefaultDBFile());
    }

    public void selectDBFile() {
        JFileChooser chooser = new JFileChooser(getCurrentFile());
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePath = chooser.getSelectedFile();
            if (filePath.exists()) {
                txtDBPath.setText(filePath.getAbsolutePath());
            }
        }
    }

    private DAOFactory getDAO(String filepath) throws ArgumentException, DAOException {
        return DAOFactory.getDAOFactory(getConnectionInfo(filepath));
    }

    private ConnectionInfo getConnectionInfo(String filepath) throws ArgumentException {
        return new ConnectionInfo(filepath, AbstractDAOFactory.SQLITE_DATABASE);
    }

    private ConnectionInfo getConnectionInfo() throws ArgumentException {
        String dbPath = txtDBPath.getText();
        int dbType = AbstractDAOFactory.SQLITE_DATABASE;
        return new ConnectionInfo(dbPath, dbType);
    }

    public void createDBFile() {
        JFileChooser chooser = new JFileChooser(getCurrentFile());
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePath = chooser.getSelectedFile();
            if (filePath.exists() && filePath.canWrite()) {
                //delete if exist
                //TODO enable localization via properties file
                int status = JOptionPane.showConfirmDialog(chooser,
                        "<html> Do you want to override this database file?<br/>This action cannot be undone.",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                    try {
                        filePath.delete();
                        String dbPath = filePath.getAbsolutePath();
                        DAOFactory dao = getDAO(dbPath);
                        dao.getProjectDAO().setupProject();
                        dao.shutdown();
                    } catch (DAOException | ArgumentException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }
            }
            txtDBPath.setText(filePath.getAbsolutePath());
        }
    }

    public void loadDB() {
        try {
            String dbPath = txtDBPath.getText();
            int dbType = AbstractDAOFactory.SQLITE_DATABASE;
            Configuration.getInstance().setDbInfo(new ConnectionInfo(dbPath, dbType));

            // Try to connect to database
            DAOFactory dao = Configuration.getInstance().getDb();
            boolean isValidDB = dao.getProjectDAO().validateProject();
            dao.shutdown();
            
            if (isValidDB) {
                //XXX can get DAO factory -> info correct
                groupModulePanel.activate();
                //TODO can we do this smarter?
                btnLoad.setEnabled(false);
                btnOpen.setEnabled(false);
                btnCreate.setEnabled(false);
                txtDBPath.setEnabled(false);
                btnUnload.setEnabled(true);

                //TODO save last database file
                ///Configuration.getInstance().saveLastDatabaseFile(dbPath);
                AppCentral.getAPIDelegate().setAppTitle(dbPath);
                fireActiveStateChanged(null);
            }
            else{
                System.out.println("INVALID PROJECT FILE");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            //XXX Localization
            Throwable t = ex.getCause();
            if (t != null && "com.db4o.ext.DatabaseFileLockedException".equals(t.getClass().getName())) {
                SwingHelper.alert("<html>Database file is locked<br/>"
                        + "Are you opening this database file in another session?",
                        "Database file is locked");
            }
        }
    }

    public void unloadDB() {
        //XXX notify user first
        int answer = JOptionPane.showConfirmDialog(null,
                "<html>Are you sure you want to close this working session?<br/>"
                + "All work haven't been saved will be discarded.",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (answer == JOptionPane.NO_OPTION) {
            return;
        }

        //Try to shutdown database
        try {
            //TODO: shut down the database here
            Configuration.getInstance().clearDb();
            Configuration.getInstance().setDbInfo(null);
        } catch (NullPointerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        groupModulePanel.deactivate();
        //close all tab
        AppCentral.getAPIDelegate().sendMessage(new Message(HulkMessages.CLOSE_ALL_BUT_STARTPAGE));

        //unload all modules
        FunctionPool.getInstance().unload();
        AppCentral.getAPIDelegate().setAppTitle("");

        //reset UI state
        btnLoad.setEnabled(true);
        btnOpen.setEnabled(true);
        btnCreate.setEnabled(true);
        txtDBPath.setEnabled(true);
        btnUnload.setEnabled(false);
        fireActiveStateChanged(null);
    }

// </editor-fold>
}
