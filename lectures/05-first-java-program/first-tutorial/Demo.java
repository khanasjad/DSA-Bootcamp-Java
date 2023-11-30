import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        System.out.println();


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

        // reverse of string
    String value = "ABCDE";
    char[] charstr= value.toCharArray();

    for (int i = 0; i< charstr.length-1; i++){

            char temp = charstr[i];
            charstr[i] = charstr[charstr.length- i-1];
            charstr[charstr.length- i-1]= temp;

    }
       String newA =  new String(charstr);
        System.out.println(newA);
        int[] f =  new int[102];

        f[0] = 0;
        f[1] = 1;
        for (int i = 2;i<=100;i++){

         f[i] = f[i-1] + f[i-2];
            System.out.println(f[i]);
        }

        int count = 0;
        int n =1634;
        for(;n!=0;n = n/10){
            count++;
        }
        System.out.println(count);
    int i=1634;
    double Total =0;
    while(i>0){

        int remainder = i%10;
        //System.out.println(remainder);
        Total += Math.pow(remainder,count);
        i= i/10;

    }
        System.out.println(Total);
    }



}