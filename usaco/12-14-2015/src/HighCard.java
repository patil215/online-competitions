import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class HighCard {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner =  new Scanner(new File("highcard.in"));
        int n = scanner.nextInt();

        int max = n * 2;

        ArrayList<Integer> elsie = new ArrayList<Integer>();
        ArrayList<Integer> bessie = new ArrayList<Integer>();

        for(int i = 0; i < n; i++) {
            int card = scanner.nextInt();
            elsie.add(card);
        }

        HashSet<Integer> elsiesCards = new HashSet<Integer>();
        for(int i = 0; i < elsie.size(); i++) {
            elsiesCards.add(elsie.get(i));
        }

        for(int i = 1; i <= max; i++) {
            if(!elsiesCards.contains(i)) {
                bessie.add(i);
            }
        }

        Collections.sort(bessie);
        Collections.sort(elsie);

        int sum = 0;

        int incrementer = 0;
        for(int i = 0; i < elsie.size(); i++) {
            int card = elsie.get(i);
            while(incrementer < bessie.size()) {
                if(bessie.get(incrementer) > card) {
                    incrementer++;
                    sum++;
                    // bessie.remove(incrementer);
                    break;
                }
                incrementer++;
            }
            //System.out.println();
        }

        PrintWriter writer = new PrintWriter(new File("highcard.out"));
        writer.println(sum);
        writer.close();
    }
}
