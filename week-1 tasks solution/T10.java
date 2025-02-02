import java.util.concurrent.*;

public class T10 {

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        int[][] result = new int[rowsA][colsB];

        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible. Incompatible dimensions.");
            return null;
        }

        ExecutorService executor = Executors.newFixedThreadPool(rowsA);

        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            executor.submit(() -> {
                for (int j = 0; j < colsB; j++) {
                    result[row][j] = 0;
                    for (int k = 0; k < colsA; k++) {
                        result[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{2, 0}, {1, 2}};

        int[][] result = multiplyMatrices(matrixA, matrixB);

        System.out.println("Result of the multiplication:");
        if (result != null) {
            printMatrix(result);
        }
    }
}
