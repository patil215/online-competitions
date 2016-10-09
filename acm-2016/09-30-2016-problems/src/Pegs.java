import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by patil215 on 9/26/16.
 */
public class Pegs {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < testCases; i++) {
            execute(scanner);
        }
    }

    static final int AVAILABLE = 0;
    static final int PEG = 1;
    static final int UNPLAYABLE = 2;

    public static void execute(Scanner scanner) {

        int[][] board = new int[5][5];

        int pegCount = 0;
        LinkedHashMap<Integer, int[]> pegLocations = new LinkedHashMap<>();
        for(int i = 0; i < 5; i++) {
            String line = scanner.nextLine();
            for(int d = 0; d < 5; d++) {
                char character = line.charAt(d);

                if(character == '#') {
                    board[i][d] = UNPLAYABLE;
                } else if(character == '.') {
                    board[i][d] = AVAILABLE;
                } else if(character == 'o') {
                    board[i][d] = PEG;
                    pegLocations.put(i * 5 + d, new int[] {i, d});
                    pegCount++;
                }
            }
        }

        System.out.println("The best case ends with " + getMin(board, pegCount, pegLocations) + " pegs.");

    }

    public static int getMin(int[][] board, int numPegs, LinkedHashMap<Integer, int[]> pegLocations) {
        ArrayList<int[][]> validMoves = getValidMoves(board, pegLocations);

        if(validMoves.size() == 0 || numPegs == 1) {
            return numPegs;
        }

        int min = Integer.MAX_VALUE;
        for(int[][] move : validMoves) {
            board = applyMove(board, move);
            pegLocations = applyMoveToPegLocations(pegLocations, move);
            int result = getMin(board, numPegs - 1, pegLocations);
            if(result == 1) {
                return 1;
            }
            if(result < min) {
                min = result;
            }
            board = unApplyMove(board, move);
            pegLocations = unapplyMoveToPegLocations(pegLocations, move);
        }
        return min;
    }

    public static LinkedHashMap<Integer, int[]> applyMoveToPegLocations(LinkedHashMap<Integer, int[]> pegLocations, int[][] move) {
        int[] pegA = move[0];
        int[] pegB = move[1];
        int[] result = move[2];
        pegLocations.remove(pegA[0] * 5 + pegA[1]);
        pegLocations.remove(pegB[0] * 5 + pegB[1]);
        pegLocations.put(result[0] * 5 + result[1], new int[] {result[0], result[1]});
        return pegLocations;
    }

    public static LinkedHashMap<Integer, int[]> unapplyMoveToPegLocations(LinkedHashMap<Integer, int[]> pegLocations, int[][] move) {
        int[] pegA = move[0];
        int[] pegB = move[1];
        int[] result = move[2];

        pegLocations.remove(result[0] * 5 + result[1]);
        pegLocations.put(pegA[0] * 5 + pegA[1], new int[] {pegA[0], pegA[1]});
        pegLocations.put(pegB[0] * 5 + pegB[1], new int[] {pegB[0], pegB[1]});
        return pegLocations;
    }

    public static int[][] applyMove(int[][] board, int[][] move) {
        int[] pegA = move[0];
        int[] pegB = move[1];
        int[] result = move[2];
        board[pegA[0]][pegA[1]] = AVAILABLE;
        board[pegB[0]][pegB[1]] = AVAILABLE;
        board[result[0]][result[1]] = PEG;
        return board;
    }

    public static int[][] unApplyMove(int[][] board, int[][] move) {
        int[] pegA = move[0];
        int[] pegB = move[1];
        int[] result = move[2];
        board[result[0]][result[1]] = AVAILABLE;
        board[pegA[0]][pegA[1]] = PEG;
        board[pegB[0]][pegB[1]] = PEG;
        return board;
    }

    // 2D array. Index 0 is the first peg location, Index 1 is second peg location. Index 2 is the final peg location
    public static ArrayList<int[][]> getValidMoves(int[][] board, LinkedHashMap<Integer, int[]> pegLocations) {
        ArrayList<int[][]> arrayList = new ArrayList<>();

        for(Map.Entry<Integer, int[]> peg : pegLocations.entrySet()) {
            int i = peg.getValue()[0];
            int d = peg.getValue()[1];

            if (i < board.length - 2 && board[i][d] == PEG && board[i + 1][d] == PEG && board[i + 2][d] == AVAILABLE) {
                arrayList.add(new int[][]{
                        {i, d},
                        {i + 1, d},
                        {i + 2, d}
                });
            }

            if (d < board[0].length - 2 && board[i][d] == PEG && board[i][d + 1] == PEG && board[i][d + 2] == AVAILABLE) {
                arrayList.add(new int[][] {
                        {i, d},
                        {i, d + 1},
                        {i, d + 2}
                });
            }

            if (i > 2 && board[i][d] == PEG && board[i - 1][d] == PEG && board[i - 2][d] == AVAILABLE) {
                arrayList.add(new int[][]{
                        {i, d},
                        {i - 1, d},
                        {i - 2, d}
                });
            }

            if (d > 2 && board[i][d] == PEG && board[i][d - 1] == PEG && board[i][d - 2] == AVAILABLE) {
                arrayList.add(new int[][] {
                        {i, d},
                        {i, d - 1},
                        {i, d - 2}
                });
            }

        }

        return arrayList;
    }

}
