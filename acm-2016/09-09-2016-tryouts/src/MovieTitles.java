import java.util.Scanner;

/**
 * Created by patil215 on 9/9/16.
 */
public class MovieTitles {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < t; i++) {
            String movie = scanner.nextLine();
            if(movie.split(" ").length > 1) {
                System.out.println(movie + ": Age of Darkness");
            } else {
                System.out.println(movie + " Returns");
            }
        }
    }
}
