package service;

import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import ex.api.DataSet;

public class AccuracyService implements ClusterAnalysisService {
    double result;
    @Override
    public void setOptions(String[] options) throws ClusteringException {

    }

    @Override
    public String getName() {
        return "Accuracy";
    }

    @Override
    public void submit(DataSet ds) throws ClusteringException {
        double correct = 0;
        double total = 0;

        String[][] matrix = ds.getData();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                total += Integer.valueOf(matrix[i][j]);
                if (i == j) {
                    correct += Integer.valueOf(matrix[i][j]);
                }
            }
        }
        result = correct / total;
    }




    @Override
    public DataSet retrieve(boolean clear) throws ClusteringException {
        return null;
    }

    @Override
    public double getResult() {
        return result;
    }
}
