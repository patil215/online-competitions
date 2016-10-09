import java.util.Scanner;

/**
 * Created by patil215 on 9/9/16.
 */
public class Party {

    public static class Coordinate {
        public int x;
        public int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;

        }

        public String toString() {
            return x + " " + y;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for(int i = 0; i < t; i++) {
            int f = scanner.nextInt();
            int partyX = scanner.nextInt();
            int partyY = scanner.nextInt();
            //System.out.println(f + " " + partyX + " " + partyY);
            Coordinate[] coordinates = new Coordinate[f + 1];

            int maxX = partyX;
            int minX = partyX;
            int maxY = partyY;
            int minY = partyY;

            for(int d = 0; d < f; d++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(x < minX) {
                    minX = x;
                }
                if(x > maxX) {
                    maxX = x;
                }
                if(y < minY) {
                    minY = y;
                }
                if(y > maxY) {
                    maxY = y;
                }
                coordinates[d] = new Coordinate(x, y);
            }
            coordinates[coordinates.length - 1] = new Coordinate(partyX, partyY);

            //System.out.println(Arrays.toString(coordinates));
            compute(coordinates, minX, maxX, minY, maxY);
        }
    }

    public static int computeDistance(Coordinate coordinate, int x, int y) {
        return Math.abs(x - coordinate.x) + Math.abs(y - coordinate.y);
    }

    public static int binarySearchX(int min, int max, Coordinate[] coordinates) {
        int cur = (min + max) / 2;
        while(true) {
            int curCost = 0;
            for(int k = 0; k < coordinates.length; k++) {
                curCost += computeDistance(coordinates[k], cur, 0);
            }

            int costLeft = 0;
            for(int k = 0; k < coordinates.length; k++) {
                costLeft += computeDistance(coordinates[k], cur - 1, 0);
            }

            int costRight = 0;
            for(int k = 0; k < coordinates.length; k++) {
                costRight += computeDistance(coordinates[k], cur + 1, 0);
            }

            if(curCost > costLeft) {
                max = cur;
                cur = (min + max) / 2;
            } else if(curCost > costRight) {
                min = cur;
                cur = (min + max) / 2;
            } else {
                return cur;
            }
        }
    }

    public static int binarySearchY(int min, int max, Coordinate[] coordinates) {
        int cur = (min + max) / 2;
        while(true) {
            int curCost = 0;
            for(int k = 0; k < coordinates.length; k++) {
                curCost += computeDistance(coordinates[k], 0, cur);
            }

            int costLeft = 0;
            for(int k = 0; k < coordinates.length; k++) {
                costLeft += computeDistance(coordinates[k], 0, cur - 1);
            }

            int costRight = 0;
            for(int k = 0; k < coordinates.length; k++) {
                costRight += computeDistance(coordinates[k], 0, cur + 1);
            }

            if(curCost > costLeft) {
                max = cur;
                cur = (min + max) / 2;
            } else if(curCost > costRight) {
                min = cur;
                cur = (min + max) / 2;
            } else {
                return cur;
            }
        }
    }

    public static void compute(Coordinate[] coordinates, int minX, int maxX, int minY, int maxY) {
        int xx = binarySearchX(minX, maxX, coordinates);
        int yy = binarySearchY(minY, maxY, coordinates);
        System.out.println(xx + " " + yy);

    }

}
