
public class GCD {
    public static void main(String args[]) {
        System.out.println(findGCD(15, 35));
        System.out.println(findGCD(0, 111));
        System.out.println(findGCD(-12, 18));
        System.out.println(findGCD(99, 100));
        System.out.println(findGCD(11, 121));
        System.out.println(findGCD(100, 102));

        System.out.println();

        System.out.println(findGCD(15, 5));
        System.out.println(findGCD(0, 100));
        System.out.println(findGCD(-27, -45));
        System.out.println(findGCD(-90, 100));
        System.out.println(findGCD(100, 121));
        System.out.println(findGCD(1001, 289));
    }

    private static int findGCD(int a, int b) {
        int gcd = 1;
        for(int i = 0; i < Math.min(a, b); i++) {
            if(a % gcd == 0 && b % gcd == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
}
