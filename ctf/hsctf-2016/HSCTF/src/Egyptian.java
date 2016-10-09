import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Egyptian {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("egyptoutput.txt"));

        long[][] multiplies = new long[5000][5000];
        for(int i = 0; i < 5000; i++) {
            for(int d = 0; d < 5000; d++) {
                multiplies[i][d] = scanner.nextLong();
            }
        }

        System.out.println("read output");

        BigInteger sum = new BigInteger("0");
        scanner = new Scanner(new File("egypt.in"));
        for(int i = 0; i < 10000; i++) {
            for(int d = 0; d < 10000; d++) {
                int x = i;
                int y = d;
                if(i > 4999) {
                    x = 10000 - i - 1;
                }
                if(d > 4999) {
                    y = 10000 - d - 1;
                }
                BigInteger num = new BigInteger(scanner.next());
                BigInteger multiplier = new BigInteger(String.valueOf(multiplies[x][y]));
                sum = sum.add(num.multiply(multiplier));
            }
            if(i % 1000 == 0) {
                System.out.println(i);
            }
        }

        System.out.println("read input");

        System.out.println(sum);

        /*int n = 10000;
        //int[][] board = new int[n][n];

        // SIZE, POSITION mapping to number of hits - note that this applies for both horizontal and vertical X and Y position
        int[][] sizeAndPositionToNumberHits = new int[n][n];

        for (int size = 1; size <= ((n + 1) / 2); size++) {
            // Keep in mind that whatever we assign at size - 1, we need to reflectively assign at n - size
            int[] arrayAtSize = sizeAndPositionToNumberHits[size - 1];
            for(int position = 0; position <= (n / 2); position++) {
                // Keep in mind that whatever we assign at position, we need to reflectively assign at n - position - 1
                if(position < size) {
                    // Assign position + 1 as number of movement permutations
                    arrayAtSize[position] = position + 1;
                    arrayAtSize[n - position - 1] = position + 1;
                } else {
                    // Assign the size
                    arrayAtSize[position] = size;
                    arrayAtSize[n - position - 1] = size;
                }
            }
            sizeAndPositionToNumberHits[n - size] = arrayAtSize;
        }
        System.out.println("created array map");

        long[][] board = new long[(n + 1) / 2][(n + 1) / 2];
        for(int i = 0; i < board.length; i++) {
            for (int d = 0; d < board.length; d++) {
                for (int size = 1; size <= n; size++) {
                    board[i][d] += sizeAndPositionToNumberHits[size - 1][i] * sizeAndPositionToNumberHits[size - 1][d];
                }
            }
        }
        System.out.println("assigned overlap values");

        PrintWriter writer = new PrintWriter(new File("egyptoutput.txt"));
        for(int i = 0; i < board.length; i++) {
            for(int d = 0; d < board.length; d++) {
                writer.print(board[i][d] + " ");
            }
            writer.println();
        }
        writer.close();
        System.out.println("done");

        // SIZE, Y POSITION mapping to number of hits - vertically



       /* int[] board = new int[n];
        for(int i = 1; i <= n / 2 + 1; i++) { // Iterating by size
            System.out.println(i);
            for(int d = 1; d <= n/2 + 1; d++) {
                if(d <= i) {
                    board[d - 1] += d;
                    System.out.print(d + " ");
                } else {
                    board[d - 1] += i;
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(board));*/
    }
}
