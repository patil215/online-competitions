import java.util.Scanner;

public class prob04 {
    public static void main(String args[]) {

        Scanner file = new Scanner(System.in);

        while(file.hasNextLine()) {
            int a = file.nextInt();
            int b = file.nextInt();
            file.nextLine();
            if(a==0 && b == 0) {

            }else {
                System.out.println(a*b);
            }
        }
    }
}
