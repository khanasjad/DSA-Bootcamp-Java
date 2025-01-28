package Generic;

import java.util.Scanner;

public class FibonnaciSeries {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();


        int zero = 0;
        int one = 1;
        int[]arr =  new int[n];
        arr[0]= 0;
        arr[1]= 1;

        for(int i= 2; i<n;i++){
            arr[i] = arr[i-1] +arr[i-2];
        }

        System.out.println("arr" + arr.length);

        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }
    }

