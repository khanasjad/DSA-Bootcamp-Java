package Section4.Sorting;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


class Main {

    public static void main(String args[]) {

        int array[] = {2,435,634,45,346,345,25,46,6534,6,643};



        for (int i =0; i<array.length;i++) {
        int  temp = array[i];
        int j = i-1;

            while(j>=0 && array[j]>temp){
                array[j+1] = array[j];
                j--;
            }
            array[j+1]= temp;
        }
        System.out.println(Arrays.toString(array));

        }
    }


