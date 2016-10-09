import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sequence {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("inputfile.txt"));
        int n = 100000000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("finished reading file");

        for(int i = 0; i < n; i++) {

        }

    }

    public static class Seq {
        int start;
        int end;
        int length;
    }
}
