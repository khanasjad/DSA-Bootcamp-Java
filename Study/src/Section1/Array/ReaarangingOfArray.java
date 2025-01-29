package Section1.Array;

public class ReaarangingOfArray {
    public static void rearrange(int[] arr) {
        int n = arr.length;

        // Separate positive and negative numbers
        int posIdx = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                // Swap negative number to the left
                int temp = arr[i];
                arr[i] = arr[posIdx];
                arr[posIdx] = temp;

                // Move the positive index to the next position
                posIdx++;
            }
        }

        // Rearrange in alternate positive and negative order
        int negIdx = 0;
        while (negIdx < posIdx && posIdx < n) {
            // Swap positive and negative numbers in alternate positions
            int temp = arr[negIdx];
            arr[negIdx] = arr[posIdx];
            arr[posIdx] = temp;

            // Move both indices to the next positions
            negIdx += 2;
            posIdx++;
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};

        System.out.println("Original Array:");
        printArray(arr);

        // Rearrange the array
        rearrange(arr);

        System.out.println("Rearranged Array:");
        printArray(arr);
    }
}
