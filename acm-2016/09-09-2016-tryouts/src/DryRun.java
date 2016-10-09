import java.util.Scanner;

/**
 * Created by patil215 on 9/9/16.
 */
public class DryRun {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            System.out.println("I like " + line + ".");
        }

    }
}
