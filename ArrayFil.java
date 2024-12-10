import java.util.Arrays;
import java.util.Scanner;

public class ArrayFil{
    public static void main(String[] args) {
        //11
        arrayFil();
    }
    //11
    public static void arrayFil() {
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
