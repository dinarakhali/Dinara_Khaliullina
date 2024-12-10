import java.util.Arrays;
import java.util.Scanner;

public class LeapYearCheck{
    public static void main(String[] args) {
        //9
        boolean leapYear = leapYearCheck(800);
        System.out.println(leapYear);
    }
    //9
    public static boolean leapYearCheck(int yearNumber) {
        boolean leap = (yearNumber % 4 == 0 && yearNumber % 100 !=0) || (yearNumber % 400 == 0);
        return leap;
    }
}