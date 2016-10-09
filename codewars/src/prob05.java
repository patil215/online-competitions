import java.util.Scanner;

public class prob05 {
    public static void main(String args[]) {


        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = a*(b+c);

        System.out.println(a + " x (" +b + " + " + c + ") = " + a + " x " + b + " + " + a + " x " + c);
        System.out.println(a + " x " + (b+c) + " = " + (a*b) + " + " + (a*c));
        System.out.println(d + " = " + d);
    }
}
