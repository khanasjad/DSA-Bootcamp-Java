package Section1.Array;

public class RotationOfArray {
    public static void leftRotate(int[] arr, int k) {
        int n = arr.length;

        // Normalize rotations if k is greater than the array length
        k = k % n;

        // Temporary array to store the rotated elements
        int[] temp = new int[k];

        // Copy the first k elements to the temporary array
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Shift the remaining elements to the left
        for (int i = k; i < n; i++) {
            arr[i - k] = arr[i];
        }

        // Copy the elements from the temporary array to the end of the original array
        for (int i = 0; i < k; i++) {
            arr[n - k + i] = temp[i];
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int rotations = 2;

        System.out.println("Original Array:");
        printArray(arr);

        // Left rotate the array
        leftRotate(arr, rotations);

        System.out.println("Array after " + rotations + " left rotations:");
        printArray(arr);
    }
}
