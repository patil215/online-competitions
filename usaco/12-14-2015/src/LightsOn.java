import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class LightsOn {
    static Room[][] rooms;

    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("lightson.in"));

        int n = scanner.nextInt(); // Grid size
        int m = scanner.nextInt(); // List of switches size

        rooms = new Room[n][n];

        for(int i = 0; i < n; i++) {
            for(int d = 0; d < n; d++) {
                rooms[i][d] = new Room();
            }
        }
        rooms[0][0].lit = true;

        for(int i = 0; i < m; i++) {
            rooms[scanner.nextInt() - 1][scanner.nextInt() - 1].switches.add(new Switch(scanner.nextInt() - 1, scanner.nextInt() - 1));
        }

        for(int i = 0; i < (n * n); i++) {
            expand(0, 0);
            light();
            resetVisited();
        }

        int sumLit = 0;
        for(int i = 0; i < n; i++) {
            for(int d = 0; d < n; d++) {
                if(rooms[i][d].lit) {
                    sumLit++;
                }
            }
        }
        //System.out.println(sumLit);
        PrintWriter writer = new PrintWriter(new File("lightson.out"));
        writer.println(sumLit);
        writer.close();
    }

    public static void resetVisited() {
        for(int i = 0; i < rooms.length; i++) {
            for(int d = 0; d < rooms.length; d++) {
                rooms[i][d].visited = false;
            }
        }
    }

    public static void light() {
        for(int i = 0; i < rooms.length; i++) {
            for(int d = 0; d < rooms.length; d++) {
                if(rooms[i][d].visited) {
                    ArrayList<Switch> switches = rooms[i][d].switches;
                    while(switches.size() > 0) {
                        Switch s = switches.get(0);
                        switches.remove(0);
                        rooms[s.x][s.y].lit = true;
                    }
                }
            }
        }
    }

    public static void expand(int x, int y) {
        rooms[x][y].visited = true;
        if (x < rooms.length - 1 && rooms[x + 1][y].lit && !rooms[x + 1][y].visited) {
            expand(x + 1, y);
        }
        if (x > 0 && rooms[x - 1][y].lit && !rooms[x - 1][y].visited) {
            expand(x - 1, y);
        }
        if (y < rooms.length - 1 && rooms[x][y + 1].lit && !rooms[x][y + 1].visited) {
            expand(x, y + 1);
        }
        if (y > 0 && rooms[x][y - 1].lit && !rooms[x][y - 1].visited) {
            expand(x, y - 1);
        }
    }

    public static class Room {
        boolean lit;
        boolean visited;
        ArrayList<Switch> switches = new ArrayList<Switch>();
    }

    public static class Switch {
        int x;
        int y;

        public Switch(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
