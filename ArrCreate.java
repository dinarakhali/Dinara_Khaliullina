import java.util.Arrays;
import java.util.Scanner;

public class ArrCreate{
    public static void main(String[] args) {
        //14
        int[] resultArr = arrCreate(5, 3);
        System.out.println(Arrays.toString(resultArr));
    }
    //14
    public static int[] arrCreate(int len, int initialValue) {
        int[] arr = new int[len];
        for(int i = 0; i < arr.length; i++) {
           arr[i] = initialValue;
        }
        return arr;
    }
}
