package application_queue;

import Sorting.StdRandom;

public class spacevector {

    public void makeArrays(){
        StdRandom ran = new StdRandom();
        int size = 1000;
        //2 matrixes and 2 vectors med normale arrays
        double[][] arrayMatrix0 = new double[size][size];
        double[][] arrayMatrixNumb = new double[size][size];

        double[] arrayVector0 = new double[size];
        double[] arrayVectorNumb = new double[size];
        //2 matrixes and 2 vectors lavet med meget inspiration fra bogen.
        SparseMatrix sparseMatrix0 = new SparseMatrix(size, size);
        SparseMatrix sparseMatrixNumb = new SparseMatrix(size, size);

        SparseVector sparseVector0 = new SparseVector(size);
        SparseVector sparseVectorNumb = new SparseVector(size);


        //Most values are nonzero
        for(int row = 0; row < size; row++) {
            for(int column = 0; column < size; column++) {
                //95% chance of being nonzero
                double value = 0;
                if (StdRandom.uniform(100) <= 94) {
                    value = StdRandom.uniform();
                }
                arrayMatrixNumb[row][column] = value;
                sparseMatrixNumb.put(row, column, value);
            }
        }

        //Most values are zero
        for(int row = 0; row < size; row++) {
            for(int column = 0; column < size; column++) {

                //5% chance of being nonzero
                double value = 0;
                if ( StdRandom.uniform(100) >= 95) {
                    value = StdRandom.uniform();
                }
                arrayMatrix0[row][column] = value;
                sparseMatrix0.put(row, column, value);
            }
        }

        //Most values are nonzero
        for(int column = 0; column < size; column++) {
            //95% chance of being nonzero
            double value = 0;
            if (StdRandom.uniform(100)<= 94) {
                value = StdRandom.uniform();
            }
            arrayVectorNumb[column] = value;
            sparseVectorNumb.put(column, value);
        }

        //Most values are zero
        for(int column = 0; column < size; column++) {
            //5% chance of being nonzero
            double value = 0;
            if (StdRandom.uniform(100)>= 95) {
                value = StdRandom.uniform();
            }
            arrayVector0[column] = value;
            sparseVector0.put(column, value);
        }


        System.out.printf("%17s %18s %20s %10s\n", "Method | ", "Matrix type | ", "Vector type | ", "Time spent");
        String[] methods = {"Arrays", "Sparse Vectors"};
        String[] types = {"Many non-zeroes", "Many zeroes"};
        //Array "Number" matrix X array "Number" vector
        long startTime = System.nanoTime();
        dot(arrayMatrixNumb, arrayVectorNumb);
        printResults(methods[0], types[0], types[0], (System.nanoTime()-startTime)/1000000);

        //Array "Number" matrix X array "Zero" vector
        startTime = System.nanoTime();
        dot(arrayMatrixNumb, arrayVector0);
        printResults(methods[0], types[0], types[1], (System.nanoTime()-startTime)/1000000);

        //Array "Zero" matrix X array "Number" vector
        startTime = System.nanoTime();
        dot(arrayMatrix0, arrayVectorNumb);
        printResults(methods[0], types[1], types[0], (System.nanoTime()-startTime)/1000000);

        //Array "Zero" matrix X array "Zero" vector
        startTime = System.nanoTime();
        dot(arrayMatrix0, arrayVector0);
        printResults(methods[0], types[1], types[1], (System.nanoTime()-startTime)/1000000);

        //Sparse "Number" matrix X sparse "Number" vector
        startTime = System.nanoTime();
        sparseMatrixNumb.dot(sparseVectorNumb);
        printResults(methods[1], types[0], types[0], (System.nanoTime()-startTime)/1000000);

        //Sparse "Number" matrix X sparse "Zero" vector
        startTime = System.nanoTime();
        sparseMatrixNumb.dot(sparseVector0);
        printResults(methods[1], types[0], types[1], (System.nanoTime()-startTime)/1000000);

        //Sparse "Zero" matrix X sparse "Number" vector
        startTime = System.nanoTime();
        sparseMatrix0.dot(sparseVectorNumb);
        printResults(methods[1], types[1], types[0], (System.nanoTime()-startTime)/1000000);

        //Sparse "Zero" matrix X sparse "Zero" vector
        startTime = System.nanoTime();
        sparseMatrix0.dot(sparseVector0);
        printResults(methods[1], types[1], types[1], (System.nanoTime()-startTime)/1000000);


    }
    private double[] dot(double[][] matrix, double[] array) {
        if (matrix[0].length != array.length) {
            throw new IllegalArgumentException("Matrix columns number and vector dimension must match");
        }

        double[] result = new double[matrix.length];

        for(int row = 0; row < matrix.length; row++) {
            double dot = 0;

            for(int column = 0; column < matrix[0].length; column++) {
                dot += matrix[row][column] * array[column];
            }

            result[row] = dot;
        }

        return result;
    }

    private void printResults(String method, String matrixType, String vectorType, double timeSpent) {
        System.out.printf("%14s %18s %20s %13.2fms\n", method, matrixType, vectorType, timeSpent);
    }

    public static void main(String[] args) {
        new spacevector().makeArrays();
    }

}
