package edu.pwr.convolution;

public class ConvolutionSolver {
    public static int[][] convolution(int[][] signal, int[][] kernel){
        int signalHeight = signal.length;
        int signalWidth = signal[0].length;
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;
        int resultHeight = signalHeight + kernelHeight -1;
        int resultWidth = signalWidth + kernelWidth -1;
        int[][] result = new int[resultHeight][resultWidth];



        for (int i = 0; i < resultHeight; i++) {
            for (int j = 0; j < resultWidth; j++) {
                for (int k = Math.max(0, i - kernelHeight + 1); k < Math.min(signalHeight, i + 1); k++) {
                    for (int l = Math.max(0, j - kernelWidth + 1); l < Math.min(signalWidth, j + 1); l++) {
                        result[i][j] += signal[k][l] * kernel[i - k][j - l];
                    }
                }
            }
        }

        return result;




    }

}
