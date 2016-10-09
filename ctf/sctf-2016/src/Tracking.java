
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tracking {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("tracking.in.txt"));

        double p = 2000;
        double q = 2000;
        double r = 2000;
        double s = 3000;
        double t = 1500;
        double u = 1700;

        scanner.nextLine();
        scanner.nextLine();

        ArrayList<Double> xs = new ArrayList<Double>();
        ArrayList<Double> ys = new ArrayList<Double>();
        ArrayList<Double> zs = new ArrayList<Double>();

        while(scanner.hasNextLine()) {
            String[] nums = scanner.nextLine().split(" ");
            if(nums.length > 3) {

                double a = Double.valueOf(nums[0]);
                double b = Double.valueOf(nums[1]);
                double c = Double.valueOf(nums[2]);
                double d = Double.valueOf(nums[3]);

                double x = (Math.pow(a, 2) - Math.pow(b, 2) + Math.pow(p, 2)) / (2 * p);
                double y = (Math.pow(a, 2) - Math.pow(c, 2) - Math.pow(x, 2) + Math.pow(x - q, 2) + Math.pow(r, 2)) / (2 * r);
                double z = Math.sqrt(Math.pow(a, 2) - Math.pow(x, 2) - Math.pow(y, 2));

                xs.add(x);
                ys.add(y);
                zs.add(z);

                System.out.println(x + " " + y + " " + z);
            } else {
                break;
            }
        }

        System.out.println(calculateAverage(xs) + " " + calculateAverage(ys) + " " + calculateAverage(zs));
    }

    public static double calculateAverage(ArrayList<Double> nums) {
        double sum = 0;
        for(Double num : nums) {
            sum += num;
        }
        return Math.ceil(sum / nums.size());
    }

}
