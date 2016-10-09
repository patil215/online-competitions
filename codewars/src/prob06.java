import java.util.Scanner;

public class prob06 {
    public static void main(String args[]) {

        Scanner file = new Scanner(System.in);

        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();

        int area = a*b*c;
        int fin = area / 27 + ((area % 27 == 0) ? 0 : 1);
        System.out.println(fin);
    }
}
