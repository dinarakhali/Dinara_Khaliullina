import java.util.Arrays;
import java.util.Scanner;

public class PrintColor{
    public static void main(String[] args) {
        //3
        printColor();
    }
    //3
    public static void printColor() {
        int value = 101;
        if(value <= 0) {
            System.out.println("Красный");
        }
        else if(value > 0 && value <= 100) {
            System.out.println("Желтый");
        }
        else {
            System.out.println("Зеленый");
        }
    }
}
