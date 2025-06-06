package Section1.Array;

public class MultidimensionalRotation {
    public static void main(String[] args) {
        // Creating a 2D array (matrix)
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Displaying the original matrix
        System.out.println("Original Matrix:");
        displayMatrix(matrix);

        // Rotating the matrix 90 degrees clockwise
        int[][] rotatedMatrix = rotate90DegreesClockwise(matrix);

        // Displaying the rotated matrix
        System.out.println("\nRotated Matrix:");
        displayMatrix(rotatedMatrix);
    }

    public static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static int[][] rotate90DegreesClockwise(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotatedMatrix = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[j][rows - 1 - i] = matrix[i][j];
            }
        }

        return rotatedMatrix;
    }
}

//First we transpose and reverse

// // Step 1: Transpose the matrix
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[j][i];
//                matrix[j][i] = temp;
//            }
//        }
//
//        // Step 2: Reverse each row
//        for (int i = 0; i < n; i++) {
//            int left = 0, right = n - 1;
//            while (left < right) {
//                int temp = matrix[i][left];
//                matrix[i][left] = matrix[i][right];
//                matrix[i][right] = temp;
//                left++;
//                right--;
//            }