package client.GUIClient.GUI.mainWindow;

import client.GUIClient.GUI.mainWindow.LoadingDialog;
import client.GUIClient.GUIClient;
import client.commands.*;
import client.exceptions.ErrorResponseException;
import com.formdev.flatlaf.icons.FlatOptionPaneQuestionIcon;
import common.models.Movie;
import common.responses.ClearResponse;
import common.responses.InfoResponse;
import common.responses.RemoveGreaterResponse;
import common.responses.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class OptionsPopUp extends JPopupMenu {
    protected final Window window;
    protected final GUIClient client;

    protected JMenuItem infoMenuItem;
    protected JMenuItem executeMenuItem;
    protected JSeparator separator;
    protected JMenuItem clearMenuItem;

    public OptionsPopUp(Window window, GUIClient client) {
        this.window = window;
        this.client = client;
        initComponents();

        infoMenuItemSettings();
        executeMenuItemSettings();
        clearMenuItemSettings();
    }

    protected void infoMenuItemSettings() {
        infoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog loadingDialog = new LoadingDialog((Frame) window, client.getLocale());
                RetrieveMessageTask task = new RetrieveMessageTask(loadingDialog);
                task.execute();
                loadingDialog.setVisible(true);
            }
        });
    }

    private class RetrieveMessageTask extends SwingWorker<Response, Void> {
        protected final JDialog loadingDialog;

        public RetrieveMessageTask(JDialog loadingDialog) {
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Response doInBackground() throws Exception {
            Command command = new Info(client, client.getNetworkClient(), client.getLogin(), client.getPassword());
            Response abstractResponse = command.execute();
            return abstractResponse;
        }

        @Override
        protected void done() {
            try {
                loadingDialog.dispose();
                Response abstractResponse = get();
                client.handleErrorResponse(abstractResponse);
                InfoResponse response = (InfoResponse) abstractResponse;
                if (response.error == null) {
                    JOptionPane.showMessageDialog(
                            window,
                            response.info,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.info.info.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            window,
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.info.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (ErrorResponseException | InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(
                        window,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.info.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    protected void executeMenuItemSettings() {
        executeMenuItem.addActionListener(e -> {
            FileDialog fileChooser = new FileDialog((Frame) window, ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.execute.windowTitle"), FileDialog.LOAD);
            fileChooser.setVisible(true);
            String filename = fileChooser.getFile();
            String parent = fileChooser.getDirectory();
            if (filename != null) {
                ExecuteTask task = new ExecuteTask(parent + filename);
                task.execute();
            }
        });
    }

    private class ExecuteTask extends SwingWorker<Response, Void> {
        protected final String path;

        public ExecuteTask(String path) {
            this.path = path;
        }

        @Override
        protected Response doInBackground() throws Exception {
            AbstractCommand command = new ExecuteScript(client, client.getNetworkClient(), client.getLogin(), client.getPassword(), path);
            Response abstractResponse = client.getInvoker().execute(command);
            return abstractResponse;
        }
    }

    protected void clearMenuItemSettings() {
        clearMenuItem.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(window,
                    ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.confirm.message"),
                    ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.confirm.title"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new FlatOptionPaneQuestionIcon());
            if (input == YES_OPTION) {
                JDialog loadingDialog = new LoadingDialog((Frame) window, client.getLocale());
                ClearTask task = new ClearTask(loadingDialog);
                task.execute();
                loadingDialog.setVisible(true);
            }
        });
    }


    protected class ClearTask extends SwingWorker<Response, Void> {
        private final JDialog loadingDialog;

        public ClearTask(JDialog loadingDialog) {
            this.loadingDialog = loadingDialog;
        }

        @Override
        protected Response doInBackground() throws Exception {
            AbstractCommand command = new Clear(client, client.getNetworkClient(), client.getLogin(), client.getPassword());
            Response abstractResponse = client.getInvoker().execute(command);
            return abstractResponse;
        }

        @Override
        protected void done() {
            try {
                loadingDialog.dispose();

                Response abstractResponse = get();
                client.handleErrorResponse(abstractResponse);
                ClearResponse response = (ClearResponse) abstractResponse;
                if (response.error == null) {
                    JOptionPane.showMessageDialog(
                            window,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.ok.message"),
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.ok.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            window,
                            response.error,
                            ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.error.title"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (ErrorResponseException | InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(
                        window,
                        e.getMessage().split(": ")[1],
                        ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear.error.title"),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    protected void initComponents() {
        infoMenuItem = new JMenuItem();
        executeMenuItem = new JMenuItem();
        separator = new JSeparator();
        clearMenuItem = new JMenuItem();

        //======== this ========

        //---- infoMenuItem ----
        infoMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.info"));
        add(infoMenuItem);

        //---- executeMenuItem ----
        executeMenuItem.setText(ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.execute"));
        add(executeMenuItem);
        add(separator);

        //---- clearMenuItem ----
        clearMenuItem.setText((ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("menu.options.clear")));
        clearMenuItem.setForeground(UIManager.getColor("Actions.Red"));
        add(clearMenuItem);
    }
}
