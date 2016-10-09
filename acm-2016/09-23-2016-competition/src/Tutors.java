import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by patil215 on 9/23/16.
 */
public class Tutors {

    public static void main(String args[]) {
        HashMap<Integer, Integer> times = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for(int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            for(int d = 0; d < n; d++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                for(int k = start; k < end; k++) {
                    if(times.containsKey(k)) {
                        times.put(k, times.get(k) + 1);
                    } else {
                        times.put(k, 1);
                    }
                }
            }

            for(int d = 0; d < m; d++) {
                int time = (int) scanner.nextDouble();
                if(times.containsKey(time)) {
                    System.out.println(times.get(time));
                } else {
                    System.out.println(0);
                }
            }
        }
    }

}
