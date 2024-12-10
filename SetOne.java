import java.util.Arrays;
import java.util.Scanner;

public class SetOne{
    public static void main(String[] args) {
        //13
        setOne();
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
}
