import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Feast {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("feast.in"));

        int t = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int max = -1;
        for(int i = 0; i < (t/a) + 1; i++) {
            for(int d = 0; d < (t/b) + 1; d++) {
                for(int e = 0; e < (t/a) + 1; e++) {
                    for(int f = 0; f < (t/b) + 1; f++) {
                        int check = (((i * a) + (d * b))/2) + (e * a) + (f * b);
                        if(check > max && check <= t) {
                            max = check;
                        }
                    }
                }
            }
        }
        PrintWriter writer = new PrintWriter(new File("feast.out"));
        writer.println(max);
        writer.close();
    }
}
