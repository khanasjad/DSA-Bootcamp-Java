package Section4.Sorting;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


class Bubble {

    public static void main(String args[]) {

        int array[] = {2,435,634,45,346,345,25,46,6534,6,643};



        for (int i =0; i<array.length;i++){
            for(int j= 1; j<array.length;j++){
                if(array[j-1]>array[j]) {
                    int temp = array[j];
                    array[j] =array[j-1];
                    array[j-1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        }
    }

