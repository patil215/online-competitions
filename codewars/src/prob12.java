import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class prob12 {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int grid = scan.nextInt();
        double[][] heights = new double[grid][grid];
        for(int i = 0; i < grid; i++) {
            for(int d = 0; d < grid; d++) {
                double height = scan.nextDouble();
                heights[i][d] = height;
            }
        }
        double max = -1;
        for(int i = 0; i < grid - 1; i++) {
            for(int d = 0; d < grid - 1; d++) {
                double first = heights[i][d];
                double next = heights[i + 1][d];
                double delta = Math.abs(first - next);
                double c = Math.sqrt(Math.pow(delta, 2) + 1);
                double deg = Math.atan((delta));
                if(deg > max) {
                    max = deg;
                }


                double first1 = heights[i][d];
                double next1 = heights[i][d + 1];
                double delta1 = Math.abs(first1 - next1);
                double c1 = Math.sqrt(Math.pow(delta1, 2) + 1);
                double deg1 = Math.atan(delta1);
                if(deg1 > max) {
                    max = deg1;
                }

                double first2 = heights[i][d];
                double next2 = heights[i + 1][d + 1];
                double delta2 = Math.abs(first2 - next2);
                double c2 = Math.sqrt(Math.pow(delta2, 2) + 1);
                double deg2 = Math.atan(delta2 / Math.sqrt(2));
                if(deg2 > max) {
                    max = deg2;
                }

                if(d >= 1) {
                    double first3 = heights[i][d];
                    double next3 = heights[i + 1][d - 1];
                    double delta3 = Math.abs(first3 - next3);
                    double c3 = Math.sqrt(Math.pow(delta2, 2) + 1);
                    double deg3 = Math.atan(delta3 / Math.sqrt(2));
                    if (deg3 > max) {
                        max = deg3;
                    }
                }
            }
        }
        System.out.printf("Max angle is " + "%.2f" + " degrees", Math.toDegrees(max));
    }
}
