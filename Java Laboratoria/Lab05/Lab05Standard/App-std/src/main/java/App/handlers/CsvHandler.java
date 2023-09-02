package App.handlers;

import ex.api.DataSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CsvHandler {

    private Path path;
    private DataSet dataSet;

    public CsvHandler(Path path) {
        this.path = path;
        this.dataSet = new DataSet();
    }

    public void readFile() throws IOException {
        List<String> lines = Files.readAllLines(path);
        long rowSize = lines.size();
        String[][] data = new String[(int)rowSize][];
        for(int i = 0; i <rowSize;i++){
            String[] values = lines.get(i).split(",");
            data[i] = values;

        }
        dataSet.setHeader(data[0]);
        dataSet.setData(Arrays.copyOfRange(data,1,data.length));

    }

    public DataSet getDataSet(){
        return dataSet;
    }

}
