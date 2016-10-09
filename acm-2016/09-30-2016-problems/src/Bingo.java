import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by patil215 on 9/30/16.
 */
public class Bingo {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for(int i = 0; i < numCases; i++) {
            execute(scanner);
        }
    }

    public static void execute(Scanner scanner) {
        int[] conditions = new int[5];
        for(int i = 0; i < 5; i++) {
            conditions[i] = scanner.nextInt();
        }

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();

        boolean[][][] inputBoards = new boolean[x][5][5];

        for(int i = 0; i < 5; i++) {
            for(int d = 0; d < x; d++) {
                String boardString = scanner.next();
                for(int k = 0; k < boardString.length(); k++) {
                    if(boardString.charAt(k) == 'X') {
                        inputBoards[d][i][k] = true;
                    } else {
                        inputBoards[d][i][k] = false;
                    }
                }
            }
        }

        ArrayList<boolean[][]> boards = new ArrayList<>();
        for(int i = 0; i < inputBoards.length; i++) {
            boards.add(inputBoards[i]);
        }

        //printBoards(boards);
        ArrayList<boolean[][]> set = computeBoardPerms(boards, new ArrayList<>(), y, 0);
        //printBoards(set);

        int minMovesNeeded = Integer.MAX_VALUE;

        for(boolean[][] board : set) {
            int moveAmount = 0;

            int[] numInColumn = new int[5];
            for(int i = 0; i < 5; i++) {
                for(int d = 0; d < 5; d++) {
                    if(i != 2 || d != 2) {
                        if (board[i][d]) {
                            numInColumn[d]++;
                        }
                    }
                }
            }

            //System.out.println(Arrays.toString(conditions));
            //System.out.println(Arrays.toString(numInColumn));

            for(int i = 0; i < numInColumn.length; i++) {
                if(conditions[i] < numInColumn[i]) {
                    moveAmount += (numInColumn[i] - conditions[i]);
                }
            }

            if(moveAmount < minMovesNeeded) {
                minMovesNeeded = moveAmount;
            }
        }
        System.out.println(minMovesNeeded);


    }

    public static ArrayList<boolean[][]> computeBoardPerms(ArrayList<boolean[][]> boards, ArrayList<boolean[][]> current, int y, int curIndex) {
        ArrayList<boolean[][]> set = new ArrayList<>();
        if(current.size() == y) {
            boolean[][] combined = combine(current);
            set.add(combined);
            return set;
        }

        for(int i = curIndex; i < boards.size(); i++) {
            boolean[][] board = boards.get(i);
            current.add(board);
            set.addAll(computeBoardPerms(boards, current, y, i + 1));
            current.remove(board);
        }
        return set;
    }

    public static boolean[][] combine(ArrayList<boolean[][]> boards) {
        boolean[][] finalBoard = new boolean[5][5];
        for(int i = 0; i < boards.size(); i++) {
            boolean[][] board = boards.get(i);
            for(int d = 0; d < 5; d++) {
                for(int k = 0; k < 5; k++) {
                    if(board[d][k]) {
                        finalBoard[d][k] = true;
                    }
                }
            }
        }
        return finalBoard;
    }

    public static void printBoards(ArrayList<boolean[][]> inputBoards) {
        for(int i = 0; i < inputBoards.size(); i++) {
            for(int d = 0; d < inputBoards.get(i).length; d++) {
                for(int k = 0; k < inputBoards.get(i)[d].length; k++) {
                    if(inputBoards.get(i)[d][k]) {
                        System.out.print('X');
                    } else {
                        System.out.print('0');
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void printBoards(HashSet<boolean[][]> inputBoards) {
        for(boolean[][] board : inputBoards) {
            for(int d = 0; d < board.length; d++) {
                for(int k = 0; k < board[d].length; k++) {
                    if(board[d][k]) {
                        System.out.print('X');
                    } else {
                        System.out.print('0');
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

}
