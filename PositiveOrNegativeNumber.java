import java.util.Arrays;
import java.util.Scanner;

public class PositiveOrNegativeNumber{
    public static void main(String[] args) {
        //6
        positiveOrNegativeNumber(-8);
    }
    //6
    public static void positiveOrNegativeNumber(int number) {
        boolean posOrNeg = number >= 0;
        if (posOrNeg) {
            System.out.println("Число положительное");
        }
        else {
            System.out.println("Число отрицательное");
        }
    }
}