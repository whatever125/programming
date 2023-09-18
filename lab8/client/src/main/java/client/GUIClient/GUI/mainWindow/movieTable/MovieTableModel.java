package client.GUIClient.GUI.mainWindow.movieTable;

import client.GUIClient.GUI.LocaleActionListener;
import common.models.Movie;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class MovieTableModel extends DefaultTableModel {
    private static final String dateTimeFormat = "dd.MM.yy HH:mm"; // todo
    private static final String birthdayTimeFormat = "dd.MM.yyyy";

    private final MovieTable table;
    private HashMap<Integer, Movie> tableData = new HashMap<>();

    public MovieTableModel(MovieTable table) {
        this.table = table;

        for (int i = 0; i < table.getColumnsHeader().length; i ++) {
            addColumn(table.getColumnsHeader()[i]);
        }
    }

    public void setTableData(HashMap<Integer, Movie> tableData) {
        this.tableData = tableData;
        setRowCount(0);

        for (var key: tableData.keySet()) {
            Movie movie = tableData.get(key);
            Object[] rowData = {
                    key,
                    movie.getID(),
                    movie.getName(),
                    movie.getCreationDate().format(DateTimeFormatter.ofPattern(dateTimeFormat)),
                    movie.getOscarsCount(),
                    movie.getGenre(),
                    movie.getMpaaRating(),
                    movie.getDirector().getName(),
                    movie.getDirector().getBirthday().format(DateTimeFormatter.ofPattern(birthdayTimeFormat)),
                    movie.getDirector().getWeight(),
                    movie.getDirector().getPassportID(),
                    movie.getCoordinates().getX(),
                    movie.getCoordinates().getY(),
                    movie.getOwner()
            };
            addRow(rowData);
        }
        fireTableDataChanged();
    }

    public HashMap<Integer, Movie> getTableData() {
        return tableData;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return table.getColumnsClasses()[col];
    }


    // todo
//    @Override
//    public void fireTableDataChanged() {
//        fireTableChanged(new TableModelEvent(this, //tableModel
//                0, //firstRow
//                getRowCount() - 1, //lastRow
//                TableModelEvent.ALL_COLUMNS, //column
//                TableModelEvent.UPDATE)
//        ); //changeType
//    }
}