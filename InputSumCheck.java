import java.util.Arrays;
import java.util.Scanner;

public class InputSumCheck{
    public static void main(String[] args) {
        //5
        boolean checkResult = inputSumCheck();
        System.out.println(checkResult);
    }
    //5
    public static boolean inputSumCheck() {
        Scanner inputFirst = new Scanner(System.in);
        System.out.println("Введите целое число");
        int a = inputFirst.nextInt();

        Scanner inputSecond = new Scanner(System.in);
        System.out.println("Введите еще одно целое число");
        int b = inputSecond.nextInt();

        boolean checking = a + b >= 10 && a + b <=20;
        return checking;
    }
}