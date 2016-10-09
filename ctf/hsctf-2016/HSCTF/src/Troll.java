

public class Troll {
    public static void main(String[] args){

        int d = 0;
        for(long i = 1; i < 100000000000L; i++) {

            if (1338557220 / i * i != 1338557220 && i > 0) {
                if(d < 100) {
                    System.out.println("Login succesful. The flag is the smallest key which will let you log in.");
                    System.out.println(i);
                    d++;
                }
            } else {
                System.out.println("Login rejected.");
            }
        }
    }
}
