import java.util.Arrays;
import java.util.Scanner;

public class CheckSumSign{
    public static void main(String[] args) {
        //2
        checkSumSign();
    }
    //2
    public static void checkSumSign() {
        int a = -2;
        int b = 10;

        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    }
}