import java.util.Scanner;

public class prob03 {
    public static void main(String args[]) {

        String[] vals = {"one","two","three","four","five","six","seven","eight","nine","ten"};
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();

        System.out.println("Number " + vals[a-1] + " is alive!");


    }
}
