import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        System.out.println();

     //Array
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

        //String
        String str1 = "Hello, World!";
        String str2 = new String("Hello, World!");
    String str = "Hello, World!";
    int length = str.length();
    char firstChar = str.charAt(0);
    String subStr = str.substring(7); // "World!"
    boolean isEqual = str1.equals(str2);
    boolean isEqualIgnoreCase = str1.equalsIgnoreCase(str2);
    String original = "Hello, World!";
    String upperCase = original.toUpperCase(); // "HELLO, WORLD!"
    String lowerCase = original.toLowerCase(); // "hello, world!"
    String str4 = "   Hello, World!   ";
    String trimmed = str4.trim(); // "Hello, World!"
    String str5 = "Hello, John!";
    String replaced = str5.replace("John", "Doe");
    String str6 = "apple,orange,banana";
    String[] fruits = str6.split(",");
// fruits = ["apple", "orange", "banana"]

    int number = 42;
    String strFromInt = String.valueOf(number); // "42"

    double decimal = 3.14;
    String strFromDouble = Double.toString(decimal); // "3.14"

    StringBuilder sb = new StringBuilder();

//sb.append("Hello");
//sb.append(", ");
//sb.append("World!");
    String result = sb.toString(); // "Hello, World!"

}