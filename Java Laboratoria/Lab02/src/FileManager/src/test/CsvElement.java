package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvElement {
    private int rowSize;
    private int columnSize;
    private List<List<String>> records;
    private File file;

    public CsvElement(File file) {
        this.records = new ArrayList<>();
        this.file = file;
    }


    public void readFile() throws FileNotFoundException {
        Scanner scanner  = new Scanner(new File(file.toPath().toString()));
        while (scanner.hasNextLine()){
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        this.columnSize = records.get(0).size();
        this.rowSize = records.size() - 1;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();

        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while(rowScanner.hasNext()){
            values.add(rowScanner.next());
        }

        return values;
    }

    public List<List<String>> getRecords() {
        return records;
    }

    public int getColumnSize(){
        return columnSize;
    }

    public int getRowSize(){
        return rowSize;
    }

    public List<String> getRowData(int index) {
        List<String> objects = new ArrayList<>();
        for (int i = 0; i < columnSize; i++){
            objects.add(records.get(index+1).get(i)); // Column names at index 0
        }
        return objects;
    }

    public List<String> getColumnNames(){
        List<String> objects = new ArrayList<>();
        for (int i = 0; i < columnSize; i++){
            objects.add(records.get(0).get(i));
        }
        return objects;
    }
}
