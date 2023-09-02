package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.*;

public class DataHandler {
    private List<String> avg;
    private WeakHashMap<Path, CsvElement > filesWeakHashMap = new WeakHashMap<>();

    public WeakHashMap<Path, CsvElement> getFilesWeakHashMap() {
        return filesWeakHashMap;
    }


    public void populateTable(CsvElement records, JTable table, JTable table2){
        DefaultTableModel tableModel2 = (DefaultTableModel) table.getModel();
        DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel2.setRowCount(0);
        tableModel2.setColumnCount(0);
        int columnSize = records.getColumnSize();
        List<String> columnNames = records.getColumnNames();
        for (int i = 0 ; i<columnSize;i++){
            tableModel.addColumn(columnNames.get(i));
        }
        for (int i = 1; i<columnSize;i++){
            String string = "Avg " + columnNames.get(i);
            tableModel2.addColumn( string);
        }
        int rowSize = records.getRowSize();
        for (int i=0;i<rowSize;i++){
            tableModel.addRow(records.getRowData(i).toArray());

        }
        List<String> x = calculateAvg(records,rowSize,columnSize);
        x.add(x.get(0));
        x.add(x.get(1));
        x.add(x.get(2));
        tableModel2.addRow(x.toArray());
        table.repaint();
        table2.repaint();



    }

    private List<String> calculateAvg(CsvElement records, Integer rowSize,Integer columnSize){
        List<String> list = new ArrayList<>();
        for (int i  = 1; i<columnSize;i++){
            float temp = 0;
            for(int j = 0; j<rowSize;j++){
                temp = temp + Float.parseFloat(records.getRowData(j).get(i));
            }
            list.add(Float.toString(temp/rowSize));

        }
        return list;
    }
}