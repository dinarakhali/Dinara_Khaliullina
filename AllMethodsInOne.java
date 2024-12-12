import java.util.Arrays;
import java.util.Scanner;

public class AllMethodsInOne{
    public static void main(String[] args) {
        //1
        printThreeWords();
        System.out.println();
        //2
        checkSumSign();
        System.out.println();
        //3
        printColor();
        System.out.println();
        //4
        compareNumbers();
        System.out.println();
        //5
        boolean checkResult = inputSumCheck();
        System.out.println(checkResult);
        System.out.println();
        //6
        positiveOrNegativeNumber(-8);
        System.out.println();
        //7
        boolean result = trueFalseCheck(10);
        System.out.println(result);
        System.out.println();
        //8
        writeSeveralTimes("Это предложение, которое будет печатьтся.", 3);
        System.out.println();
        //9
        boolean leapYear = leapYearCheck(800);
        System.out.println(leapYear + "\n");
        //10
        arrayInv();
        System.out.println();
        //11
        arrayFil();
        System.out.println();
        //12
        arrCycle();
        System.out.println();
        //13
        setOne();
        System.out.println();
        //14
        int[] resultArr = arrCreate(5, 3);
        System.out.println(Arrays.toString(resultArr));
    }
    //1
    public static void printThreeWords () {
        String text = "Orange \nBanana \nApple";
        System.out.println(text);
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
    //4
    public static void compareNumbers() {
        int a = 5, b = 5;
        if (a >= b) {
            System.out.println("a>=b");
        }
        else {
            System.out.println("a<b");
        }
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
    //7
    public static boolean trueFalseCheck(int number) {
        boolean checking = number < 0;
        return checking;
    }
    //8
    public static void writeSeveralTimes(String sentence, int number) {
        for (int i = 0; i < number; i++) {
            System.out.println(sentence);
        }
    }
    //9
    public static boolean leapYearCheck(int yearNumber) {
        boolean leap = (yearNumber % 4 == 0 && yearNumber % 100 != 0) || (yearNumber % 400 == 0);
        return leap;
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
    //11
    public static void arrayFil() {
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        System.out.println(Arrays.toString(numbers));
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
    //13
    public static void setOne() {
        int[][] table = new int[5][5];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(i == j) {
                    table[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < table.length; i++) {
            int j = table.length - 1 - i;
            table[i][j] = 1;
        }
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
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