import java.util.Arrays;
import java.util.Scanner;

public class WriteSeveralTimes{
    public static void main(String[] args) {
        //8
        writeSeveralTimes("Это предложение, которое будет печатьтся.", 3);
    }
    //8
    public static void writeSeveralTimes(String sentence, int number) {
        for (int i = 0; i < number; i++) {
            System.out.println(sentence);
        }
    }
}