import java.util.Arrays;
import java.util.Scanner;

public class ArrCycle{
    public static void main(String[] args) {
        //12
        arrCycle();
    }
    //12
    public static void arrCycle() {
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 6) {
                numbers[i] = numbers[i] * 2;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}