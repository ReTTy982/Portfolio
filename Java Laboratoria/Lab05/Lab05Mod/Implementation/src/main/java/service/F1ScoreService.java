package service;

import ex.api.ClusterAnalysisService;
import ex.api.ClusteringException;
import ex.api.DataSet;

public class F1ScoreService implements ClusterAnalysisService {
    private double result;
    @Override
    public void setOptions(String[] options) throws ClusteringException {

    }

    @Override
    public String getName() {
        return "F1 Score";
    }

    @Override
    public void submit(DataSet ds) throws ClusteringException {
        int truePositives = 0;
        int falsePositives = 0;
        int falseNegatives = 0;
        String[][] matrix = ds.getData();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    truePositives += Integer.valueOf(matrix[i][j]);
                } else {
                    falsePositives += Integer.valueOf(matrix[j][i]);
                    falseNegatives += Integer.valueOf(matrix[i][j]);
                }
            }
        }

        double precision = (double) truePositives / (truePositives + falsePositives);
        double recall = (double) truePositives / (truePositives + falseNegatives);


        result = 2 * precision * recall / (precision + recall);


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
