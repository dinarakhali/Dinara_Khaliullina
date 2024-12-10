import java.util.Arrays;
import java.util.Scanner;

public class ArrayInv{
    public static void main(String[] args) {
        //10
        arrayInv();
    }
    //10
    public static void arrayInv() {
        int[] numbers = {1, 1, 0, 0, 1, 0, 1, 1, 0, 1};
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] == 0) {
                numbers[i] = 1;
            }
            else {
                numbers[i] = 0;
            }

        }
        System.out.println(Arrays.toString(numbers));
    }
}
