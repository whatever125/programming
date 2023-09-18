package client.GUIClient.GUI.mainWindow.movieTable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Objects;

public class TableMouseListener implements MouseListener {
    private final JPopupMenu popupMenu;
    private final MovieTable table;
    private final String login;

    public TableMouseListener(MovieTable table, String login, TablePopUp tablePopUp) {
        this.table = table;
        this.login = login;

        popupMenu = tablePopUp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = table.rowAtPoint(e.getPoint());
            String owner = (String) table.getValueAt(row, Arrays.asList(table.getColumnsHeader()).indexOf("Owner"));
            if (Objects.equals(owner, login)) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = table.rowAtPoint(e.getPoint());
            if (row >= 0 && row < table.getRowCount()) {
                table.setRowSelectionInterval(row, row);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}