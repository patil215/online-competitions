import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dream {

    static ArrayList<Integer> moves = new ArrayList<Integer>();
    static int[][] grid;
    static int goalX;
    static int goalY;

    static int red = 0;
    static int pink = 1;
    static int orange = 2;
    static int blue = 3;
    static int purple = 4;

    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("dream.in"));

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        grid = new int[x][y];

        for(int i = 0; i < x; i++) {
            for(int d = 0; d < y; d++) {
                grid[i][d] = scanner.nextInt();
            }
        }

        boolean[][] visited = new boolean[x][y];


    }

    public void traverse(int curX, int curY, boolean[][] visited, boolean smellsLikeOranges) {

    }

    public static boolean checkRight(int x, int y, boolean oranges) {
        if(grid[x+1][y] != red && grid[x+1][y] < grid.length) {
            // Blue
            if(grid[x+1][y] == pink) {
                return true;
            } else if(grid[x+1][y] == blue && oranges) {
                return true;
            } else if(grid[x+1][y] == purple) {return true};
        }
    }

    public static boolean validColorSquare(int x, int y) {

    }


}
