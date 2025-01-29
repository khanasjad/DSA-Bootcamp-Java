package Section1.Array;

public class MultidimensionalTraversal {
    public static void main(String[] args) {
        // Declare and initialize a 3D array
        int[][][] threeDArray = {
                {
                        {1, 2, 3},
                        {4, 5, 6},
                }
                , {
                        {7, 8, 9},
                        {10, 11, 12},
                }
        };

        // Access elements in the 3D array
        int element = threeDArray[1][0][2]; // Accessing the element at layer 1, row 0, column 2
        System.out.println("Element at (1, 0, 2): " + element);

        // Iterate through the 3D array
        for (int i = 0; i < threeDArray.length; i++) {
            for (int j = 0; j < threeDArray[i].length; j++) {
                for (int k = 0; k < threeDArray[i][j].length; k++) {
                    System.out.print(threeDArray[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}

//public class MultiDimensionalTraversal {
//    public static void main(String[] args) {
//        // Creating a 2D array (matrix)
//        int[][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//
//        // Getting the number of rows and columns
//        int rows = matrix.length;
//        int cols = matrix[0].length;
//
//        // Traversing the 2D array using nested loops
//        System.out.println("Traversing the 2D array:");
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();  // Move to the next row
//        }
//    }
//}