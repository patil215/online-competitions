import java.util.HashMap;
import java.util.Scanner;

public class Peggie {

    private static int minPeg = Integer.MAX_VALUE;

    public static String buildGrid(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    public static int traverse(char[][] board, int numPeg, HashMap<String, Integer> hm) {

        if (hm.containsKey(buildGrid(board))) {
            return hm.get(buildGrid(board));
        }

        int pieces = 0;
        boolean didmove = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 'o') {
                    pieces += 1;

                    if (i >= 0 && i < 3 && board[i + 1][j] == 'o' && (board[i + 2][j] == '.')) {
                        board[i + 2][j] = 'o';
                        board[i + 1][j] = '.';
                        board[i][j] = '.';
                        didmove = true;
                        traverse(board, numPeg - 1, hm);
                        board[i][j] = 'o';
                        board[i + 1][j] = 'o';
                        board[i + 2][j] = '.';
                        numPeg += 1;
                    }
                    if (i > 1 && i < 5 && board[i - 1][j] == 'o' && board[i - 2][j] == '.') {
                        board[i - 2][j] = 'o';
                        board[i - 1][j] = '.';
                        board[i][j] = '.';
                        didmove = true;

                        traverse(board, numPeg - 1, hm);

                        board[i - 2][j] = '.';
                        board[i - 1][j] = 'o';
                        board[i][j] = 'o';
                        numPeg += 1;
                    }
                    if (j >= 0 && j < 3 && board[i][j + 1] == 'o' && board[i][j + 2] == '.') {
                        board[i][j + 2] = 'o';
                        board[i][j + 1] = '.';
                        board[i][j] = '.';
                        didmove = true;


                        traverse(board, numPeg - 1, hm);
                        board[i][j + 1] = 'o';
                        board[i][j] = 'o';
                        board[i][j + 2] = '.';
                        numPeg += 1;
                    }
                    if (j > 1 && j < 5 && board[i][j - 1] == 'o' && board[i][j - 2] == '.') {
                        board[i][j - 2] = 'o';
                        board[i][j - 1] = '.';
                        board[i][j] = '.';
                        didmove = true;

                        traverse(board, numPeg - 1, hm);
                        board[i][j - 1] = 'o';
                        board[i][j] = 'o';
                        board[i][j - 2] = '.';
                        numPeg += 1;
                    }
                }
            }
        }
        minPeg = Math.min(minPeg, pieces);
        if (didmove) {
            hm.put(buildGrid(board), minPeg);
            return minPeg;
        }
        hm.put(buildGrid(board), pieces);
        return pieces;
    }

    public static int countPeg(char[][] board) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 'o') {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = Integer.valueOf(sc.nextLine());
        char[][] board = new char[5][5];
        while (cases > 0) {

            cases--;

            int min;
            for (int i = 0; i < 5; i++) {
                String line = sc.nextLine();
                board[i] = line.toCharArray();
            }
            int numPeg = countPeg(board);

            HashMap<String, Integer> hm = new HashMap<>();
            minPeg = numPeg;
            min = traverse(board, numPeg, hm);
            System.out.println("The best case ends with " + min + " pegs.");
        }
    }
}