package client.GUIClient.GUI.mainWindow.movieTable;

import client.GUIClient.GUI.LocaleActionListener;
import client.GUIClient.GUIClient;
import common.models.Movie;
import common.models.MovieGenre;
import common.models.MpaaRating;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MovieTable extends JTable implements LocaleActionListener {
    private String[] columnsHeader;
    private final Class[] columnsClasses  = new Class[]{
            Integer.class,
            Integer.class,
            String.class,
            ZonedDateTime.class,
            Long.class,
            MovieGenre.class,
            MpaaRating.class,
            String.class,
            LocalDateTime.class,
            Integer.class,
            String.class,
            Integer.class,
            Integer.class,
            String.class,
    };
//    private int selectedRow = -1;

    private final MovieTableModel model;
//    private final MovieTableCellRenderer renderer = new MovieTableCellRenderer();
    private final TableMouseListener mouseListener;

    private final GUIClient client;

    public MovieTable(GUIClient client, Window owner) {
        super();
        this.client = client;

        columnsHeader = new String[]{
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.key"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.id"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.movieName"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.creationDate"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.oscarsCount"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.genre"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.rating"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.directorName"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.birthday"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.weight"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.passportID"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.x"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.y"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.owner")
        };
        model = new MovieTableModel(this);

        setModel(model);
//        setCellRenderer();
        setAutoCreateRowSorter(true);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setOpaque(false);
        getTableHeader().setBackground(Color.decode("#EEEEEE"));

        mouseListener = new TableMouseListener(this, client.getLogin(), new TablePopUp(this, owner, client));
        addMouseListener(mouseListener);
    }

    @Override
    public MovieTableModel getModel() {
        return model;
    }

//    public MovieTableCellRenderer getRenderer() {
//        return renderer;
//    }

    public TableMouseListener getMouseListener() {
        return mouseListener;
    }

//    private void setCellRenderer() {
//        for (int i = 0; i < columnsHeader.length; i ++) {
//            getColumnModel().getColumn(i).setCellRenderer(renderer);
//        }
//    }

    public void setData(HashMap<Integer, Movie> movieHashMap) {
        model.setTableData(movieHashMap);
    }

    public String[] getColumnsHeader() {
        return columnsHeader;
    }

    public Class[] getColumnsClasses() {
        return columnsClasses;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component cell = super.prepareRenderer(renderer, row, column);

        // Add borders to the right of each cell except the last column and row
        int right = 0;
        int bottom = 0;
        if (column < getColumnCount() - 1) {
            right = 1;
        }
        if (row < getRowCount()) {
            bottom = 1;
        }
        ((JComponent) cell).setBorder(BorderFactory.createMatteBorder(0, 0, bottom, right, Color.decode("#E6E6E6")));

        return cell;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        columnsHeader = new String[]{
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.key"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.id"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.movieName"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.creationDate"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.oscarsCount"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.genre"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.rating"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.directorName"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.birthday"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.weight"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.passportID"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.x"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.y"),
                ResourceBundle.getBundle("mainWindow", client.getLocale()).getString("header.table.owner")
        };
    }

//    private class MovieTableCellRenderer extends DefaultTableCellRenderer {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//                                                       int row, int col) {
//
//            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
//
//            if (!isSelected) {
//                String owner = (String) table.getModel().getValueAt(row, 13);
//                Color background;
//                if (owner.equals(client.getLogin())) {
//                    background = Color.LIGHT_GRAY;
//                } else {
//                    background = table.getBackground();
//                }
//                setBackground(background);
//            } else {
//                setBackground(table.getSelectionBackground());
//            }
//            return this;
//        }
//    }

}
