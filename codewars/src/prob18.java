import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class prob18 {

    public static class Place {
        int width;
        int height;
        int value;
        public Place(int width, int height, int value) {
            this.width = width;
            this.height = height;
            this.value = value;
        }

        public String toString() {
            return "(" + width + " " + height + ") " + value;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int[][] grid = new int[width][height];
        int startw = 0;
        int starth = 0;
        int endw = 0;
        int endh = 0;
        for(int i = 0; i < width; i++) {
            for(int d = 0; d < height; d++) {
                String thing = scanner.next();
                if(thing.equals("S")) {
                    startw = i;
                    starth = d;
                    grid[i][d] = 11;
                } else if(thing.equals("X")) {
                    endw = i;
                    endh = d;
                    grid[i][d] = -1;
                } else {
                    grid[i][d] = Integer.parseInt(thing);
                }
            }
        }

        ArrayList<Place> path = new ArrayList<Place>();
        Place start = new Place(startw, starth, 11);
        path.add(start);
        find(grid, 0, start, path);

    }

    public static void find(int[][] grid, int currentIndex, Place current, ArrayList<Place> path) {
        if(current.value == -1) {
            String[][] printGrid = new String[grid.length][grid[0].length];
            for(int i = 0; i < grid.length; i++) {
                for(int d = 0; d < grid[0].length; d++) {
                    printGrid[i][d] = "#";
                }
                for(Place place : path) {
                    printGrid[place.width][place.height] = ".";
                }
            }
            printGrid[path.get(0).width][path.get(0).height] = "S";
            printGrid[path.get(path.size() - 1).width][path.get(path.size() - 1).height] = "X";
            for(int i = 0; i < printGrid.length; i++) {
                for(int d = 0; d < printGrid[0].length; d++) {
                    System.out.print(printGrid[i][d]);
                    if(d != printGrid[0].length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } else if(currentIndex < grid.length * grid[0].length) {
            currentIndex++;
            if (current.width > 0) {
                if (grid[current.width - 1][current.height] <= current.value) {
                    Place next = new Place(current.width - 1, current.height, grid[current.width - 1][current.height]);
                    if(!checkPath(path, next)) {
                        ArrayList<Place> update = new ArrayList<Place>(path);
                        update.add(next);
                        find(grid, currentIndex, next, update);
                    }
                }
            }
            if (current.height > 0) {
                if (grid[current.width][current.height - 1] <= current.value) {
                    Place next = new Place(current.width, current.height - 1, grid[current.width][current.height - 1]);
                    if(!checkPath(path, next)) {
                        ArrayList<Place> update = new ArrayList<Place>(path);
                        update.add(next);
                        find(grid, currentIndex, next, update);
                    }
                }
            }
            if (current.width < grid.length - 1) {
                if (grid[current.width + 1][current.height] <= current.value) {
                    Place next = new Place(current.width + 1, current.height, grid[current.width + 1][current.height]);
                    if(!checkPath(path, next)) {
                        ArrayList<Place> update = new ArrayList<Place>(path);
                        update.add(next);
                        find(grid, currentIndex, next, update);
                    }
                }
            }
            if (current.height < grid[0].length - 1) {
                if (grid[current.width][current.height + 1] <= current.value) {
                    Place next = new Place(current.width, current.height + 1, grid[current.width][current.height + 1]);
                    if(!checkPath(path, next)) {
                        ArrayList<Place> update = new ArrayList<Place>(path);
                        update.add(next);
                        find(grid, currentIndex, next, update);
                    }
                }
            }
        }
    }

    public static boolean checkPath(ArrayList<Place> places, Place place) {
        for(int i = 0; i < places.size(); i++) {
            if(place.height == places.get(i).height && place.width == places.get(i).width) {
                return true;
            }
        }
        return false;
    }
}
