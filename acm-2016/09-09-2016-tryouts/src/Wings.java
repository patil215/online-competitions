import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by patil215 on 9/9/16.
 */
public class Wings {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for(int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int b = scanner.nextInt();
            int m = scanner.nextInt();
            int[] wingVals = new int[m];
            for(int d = 0; d < m; d++) {
                wingVals[d] = scanner.nextInt();
            }

            bfs(n, b, wingVals);

        }
    }

    public static int countWings(ArrayList<Integer> wings) {
        int sum = 0;
        for(int i = 1; i < wings.size(); i++) {
            if(wings.get(i) - wings.get(i - 1) > 0) {
                sum++;
            }
        }
        return sum;
    }

    public static void bfs(int n, int b, int[] wingVals) {
        Queue queue = new LinkedList<>();
        ArrayList<Integer> start = new ArrayList<>();
        start.add(0);
        queue.add(start);
        while(!queue.isEmpty()) {
            ArrayList<Integer> current = (ArrayList<Integer>) queue.poll();
            if(current.get(current.size() - 1) == n) {
                System.out.println(countWings(current));
                break;
            }
            for(int i = wingVals.length - 1; i >= 0; i--) {
                if(current.get(current.size() - 1) + wingVals[i] < (n + wingVals[i])) {
                    ArrayList<Integer> next = new ArrayList<>(current);
                    next.add(current.get(current.size() - 1) + wingVals[i]);
                    queue.add(next);
                    System.out.println("adding");
                }
            }
            ArrayList<Integer> next = new ArrayList<>(current);
            next.add(current.get(current.size() - 1)  - b);
            queue.add(next);
        }
    }
}
