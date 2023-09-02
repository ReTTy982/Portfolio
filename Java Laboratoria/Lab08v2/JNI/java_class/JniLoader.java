package java_class;

import javax.sound.midi.Soundbank;
import java.io.*;

public class JniLoader {

    static {
        System.loadLibrary("native");

    }


    public double[][] loadData(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int size = Integer.parseInt(reader.readLine());

        double[][] matrix = new double[size][size];

        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = Double.parseDouble(values[col]);
            }
            row++;
        }
        reader.close();
        return matrix;
    }


    public void writeResultToFile(String fileName, double[][] matrix){

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    printWriter.print(matrix[i][j] + ",");
                }
                printWriter.println();
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] calculateSplot(double[][] data, double[][] kernel) {

        int size1 = data.length;
        int size2 = kernel.length;

        // Check if arrays have the same size
        if (size1 != size2) {
            throw new IllegalArgumentException("Array sizes do not match");
        }

        // Get row size
        int rowSize = data[0].length;

        // Create result array
        double[][] resultArray = new double[size1][rowSize];

        // Perform convolution for each row
        for (int i = 0; i < size1; i++) {
            double[] inputRow = data[i];
            double[] kernelRow = kernel[i];
            double[] resultRow = new double[rowSize];

            // Calculate convolution for row
            for (int j = 0; j < rowSize; j++) {
                resultRow[j] = 0.0;

                for (int k = 0; k < rowSize; k++) {
                    // Calculate convolution for element (i, j)
                    resultRow[j] += inputRow[k] * kernelRow[rowSize - k - 1];
                }
            }

            // Set row to result array
            resultArray[i] = resultRow;
        }

        return resultArray;
    }



    public static void main(String[] args) throws IOException {
        JniLoader jniLoader = new JniLoader();

        double[][] data;
        double[][] kernel;
        double[][] resultNative;
        double[][] resultJava;


        long nativeTimeSum = 0;
        long normalTimeSum = 0;
        long startTime;
        long estimatedTime;

        data = jniLoader.loadData("/Users/paul/Desktop/studia/java/paupac307_javatz_2023/Lab08v2/data/1.txt");
        kernel = jniLoader.loadData("/Users/paul/Desktop/studia/java/paupac307_javatz_2023/Lab08v2/data/2.txt");

        startTime = System.currentTimeMillis();
        resultJava = jniLoader.calculateSplot(data, kernel);
        estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime);
        normalTimeSum = estimatedTime;
        jniLoader.writeResultToFile("resultJava.txt", resultJava);


        System.out.println(normalTimeSum);

    }

    private native double[][] calculate(double[][] data, double[][] kernel);
}