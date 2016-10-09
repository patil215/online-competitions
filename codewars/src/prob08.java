import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class prob08 {
    public static void main(String args[]) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);

        int emptyCubes = 0;
        int employeesOut = 0;
        int dupes = 0;
        ArrayList<Integer> cubeIDs = new ArrayList<Integer>();
        ArrayList<Integer> uniques = new ArrayList<Integer>();


        int times = in.nextInt();
        for (int t = 0; t < times; t++) {
            String name = in.next();
            int cube = in.nextInt();
            if (cube == 0) {
                employeesOut++;
            }
            else if (name.equals("NA")) {
                emptyCubes++;
            }
            cubeIDs.add(cube);
            if(!uniques.contains(cube)) {
                uniques.add(cube);
            }
        }

        for(Integer i : uniques) {
            if(i != 0) {
                if (cubeIDs.indexOf(i) != cubeIDs.lastIndexOf(i)) {
                    dupes++;

                }
            }
        }

        System.out.println("Empty Cubes: " + emptyCubes);
        System.out.println("Duplicate Cube Assignments: " + dupes);
        System.out.println("Employees without Cube: " + employeesOut);
    }
}
