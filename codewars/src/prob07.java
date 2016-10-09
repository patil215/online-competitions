import java.util.Scanner;

public class prob07 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            double a = in.nextFloat();
            double b = Math.pow(a,2.0/3.0);
            System.out.println(b);
        }

    }
}
