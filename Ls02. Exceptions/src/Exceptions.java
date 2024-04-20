import utils.LocalDataException;
import utils.LocalSizeException;
import utils.ParseArrayToInts;

import java.util.Arrays;

public class Exceptions {
    public static void main(String[] args) {
        String[][] strings = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "0", "01", "1"},
                {"1", "5", "123", "-1"}
        };
        String[][] strings2 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8", "1"},
                {"9", "0", "01", "1A", "2"},
                {"1", "5", "123", "-1", "5"}
        };
        doDemo(strings);
        doDemo(strings2);
    }

    public static void doDemo(String[][] strings) {
        ParseArrayToInts newArray = new ParseArrayToInts();
        try {
            int[][] ints = newArray.toInt(strings);
            System.out.println("Success!");
            printArray(ints);
        } catch (LocalSizeException | LocalDataException ex) {
            System.out.println("Something went wrong!");
            ex.printStackTrace();
        }
    }

    public static void printArray(int[][] ints) {
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}