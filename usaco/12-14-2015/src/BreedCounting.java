import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BreedCounting {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("bcount.in"));

        int n = scanner.nextInt();
        int q = scanner.nextInt();

        int[] hs = new int[n + 1];
        hs[0] = 0;
        int[] gs = new int[n + 1];
        gs[0] = 0;
        int[] js = new int[n + 1];
        js[0] = 0;

        int toths = 0;
        int totgs = 0;
        int totjs = 0;

        for(int i = 1; i < n + 1; i++) {
            int cow = scanner.nextInt();

            if(cow == 1) {
                toths++;
            } else if(cow == 2) {
                totgs++;
            } else if(cow == 3) {
                totjs++;
            }

            hs[i] = toths;
            gs[i] = totgs;
            js[i] = totjs;
            /*System.out.println(Arrays.toString(hs));
            System.out.println(Arrays.toString(gs));
            System.out.println(Arrays.toString(js));
            System.out.println();*/
        }

        PrintWriter writer = new PrintWriter(new File("bcount.out"));

        // 2 1 1 3 2 1

        for(int i = 0; i < q; i++) {
            int start = scanner.nextInt() - 1;
            int end = scanner.nextInt();
            writer.println((hs[end] - hs[start]) + " " + (gs[end] - gs[start]) + " " + (js[end] - js[start]));
        }
        writer.close();

    }
}
