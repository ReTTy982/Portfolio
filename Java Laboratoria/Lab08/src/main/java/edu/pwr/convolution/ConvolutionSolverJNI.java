package edu.pwr.convolution;

public class ConvolutionSolverJNI {
    public native int[][] convolution(int[][] signal, int[][] kernel);
}
