package Section1.Array;

import java.util.Arrays;

public class Basics {
 public static void mani(String[] args){
    int[] abc = new int[3];
    int[] abc1 = new int[]{1,23,543};
    int[] abc2 = {1,23,543};
    abc[0] = 0;
    abc[1] = 1;
    abc[2] = 2767;
    int last = abc[abc.length -1];
    int abc3[] = Arrays.copyOf(abc,abc.length -1);

        for (int i = 0; i<abc.length;i++){
        System.out.println(abc[i]);
    }

        for (int number:abc){
        System.out.println(number);
    }
        System.out.println(abc[2]);
}
}
