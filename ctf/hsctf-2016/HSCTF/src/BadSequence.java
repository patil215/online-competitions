import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BadSequence {
    final static BigInteger TWO = new BigInteger("2");
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("bigbad.in"));
        int d = 0;
        while(scan.hasNextInt()) {
            scan.nextInt();
            d++;
        }
        System.out.println(d);
        /*Scanner scanner = new Scanner(new File("bigbad.in"));
        String n = scanner.next();
        int numInts = Integer.valueOf(n);
        BigInteger nn = new BigInteger(n);
        ArrayList<BigInteger> ints = new ArrayList<>();
        for(int i = 0; i < numInts; i++) {
            ints.add(new BigInteger(scanner.next()));
        }
        ArrayList<Boolean> values = new ArrayList<Boolean>();
        for(int i = 0; i < ints.size(); i++) {
            if(i % 100 == 0) {
                System.out.println("did 100");
            }
            boolean isEven = isEven(nCk(ints.get(i), nn));
            values.add(isEven);
        }
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        for(int i = 0; i < values.size(); i++) {
            writer.println(values.get(i));
        }
        writer.close();
        System.out.println("finished calculating");*/
        Scanner scanner = new Scanner(new File("output.txt"));
        ArrayList<Boolean> things = new ArrayList<>();
        while(scanner.hasNextBoolean()) {
            things.add(scanner.nextBoolean());
        }

        /*int start = 0;
        int maxind = -1;
        int maxlength = -1;
        for(int i = 0; i < things.size(); i++) {
            if(!things.get(i)) {
                if(i - start > maxlength) {
                    maxlength = i - start;
                    maxind = start;
                }
                start = i + 1;
            }
        }
        System.out.println(maxlength + " " + maxind);*/

        int maxlength = -1;
        int start = 0;
        int index = 0;
        for(int i = 0; i < things.size(); i++) {
            if(!things.get(i)) {
                if(i - start > maxlength) {
                    maxlength = i - start;
                    index = start;
                }
                start = i + 1;
            }
        }
        System.out.println(maxlength + " " + index);
    }

    public static boolean isEven(BigInteger val) {
        if(val.mod(new BigInteger("2")).equals(BigInteger.ZERO))
            return true;
        return false;
    }

    public static BigInteger pick(BigInteger n, BigInteger k) {
        if(k.compareTo(n) > 0) {
            System.out.println("fuck");
        }
        if(k.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        if(k.compareTo(n.divide(TWO)) > 0) {
            return pick(n, n.subtract(k));
        }
        return n.multiply(pick(n.subtract(BigInteger.ONE), k.subtract(BigInteger.ONE))).divide(k);
    }

    private static BigInteger ncr(BigInteger n, BigInteger r) {
        BigInteger top = BigInteger.ONE;
        BigInteger bot = BigInteger.ONE;

        for(BigInteger i = n; i.compareTo(r) > 0; i = i.subtract(BigInteger.ONE)){
            top = top.multiply(i);
        }

        for(BigInteger i = r; i.compareTo(BigInteger.ONE) > 0; i = i.subtract(BigInteger.ONE)){
            bot = bot.multiply(i);
        }

        return top.divide(bot);
    }

    private static BigInteger nCk(BigInteger n, BigInteger k) {
        if(n.subtract(k).compareTo(k) < 0) {
            k = n.subtract(k);
        }
        BigInteger ans = BigInteger.ONE;
        for(BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
            ans = (ans.multiply(n.subtract(i.add(BigInteger.ONE)))).divide(i);
        }
        return ans;
    }
}
