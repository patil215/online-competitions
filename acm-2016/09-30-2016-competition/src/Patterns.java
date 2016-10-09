import java.util.Scanner;

/**
 * Created by patil215 on 9/30/16.
 */
public class Patterns {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();

        for(int i = 0;i < cases; i++) {
            execute(scanner);
        }
    }

    public static void execute(Scanner scanner) {
        int numImgs = scanner.nextInt();
        int[] sqInches = new int[numImgs];
        int[] stCount = new int[numImgs];
        for(int i = 0; i < numImgs; i++) {
            sqInches[i] = scanner.nextInt();
            stCount[i] = scanner.nextInt();
        }

        int sum = 0;
        for(int i = 0; i < numImgs; i++) {
            sum += (sqInches[i] * stCount[i]);
        }

        String toPrint = "";
        for(int i = 1; i <= 3; i++) {
            int numSquareIns = i * 36 * 36;
            toPrint += (numSquareIns / sum) + " ";
        }
        System.out.println(toPrint.substring(0, toPrint.length() - 1));
    }
}
