import java.util.Arrays;
import java.util.Scanner;

public class TrueFalseCheck{
    public static void main(String[] args) {
        //7
        boolean result = trueFalseCheck(10);
        System.out.println(result);
    }
    //7
    public static boolean trueFalseCheck(int number) {
        return number < 0;
    }
}
