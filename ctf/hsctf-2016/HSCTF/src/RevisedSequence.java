import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class RevisedSequence {

    public static void main(String args[]) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("bigbad.in"));
        int n = Integer.valueOf(scanner.next());
        boolean[][] isEvens = new boolean[n][n];
        BigInteger[] nums = new BigInteger[n];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = new BigInteger(scanner.next());
        }
        for(int i = 0; i < isEvens.length; i++) {
            BigInteger iBigInteger = new BigInteger(String.valueOf(i));

            for(int j = 0; j < nums.length; j++) {
                if(isEven(nums[j], iBigInteger)) {
                    isEvens[i][j] = true;
                }
            }
        }


        boolean[] containsSequenceAtLength = new boolean[10000];

        for(int i = 0; i < 10000; i++) {
            int seqLength = 0;
            for(int d = 0; d < 10000; d++) {
                if(isEvens[i][d]) {
                    seqLength++;
                    if(seqLength >= i) {
                        containsSequenceAtLength[i] = true;
                        break;
                    }
                } else {
                    seqLength = 0;
                }
            }
        }

        int maxLength = -1;
        for(int i = 0; i < containsSequenceAtLength.length; i++) {
            if(containsSequenceAtLength[i]) {
                maxLength = i;
            }
        }

        System.out.println("Maximum sequence length: " + maxLength);

        int seqLength = 0;
        for(int d = 0; d < 10000; d++) {
            if(isEvens[maxLength][d]) {
                seqLength++;
                if(seqLength >= maxLength) {
                    System.out.println("First sequence index: " + (d - seqLength + 1));
                    break;
                }
            } else {
                seqLength = 0;
            }
        }
    }

    public static boolean isEven(BigInteger n, BigInteger p) {
        String astring = new StringBuilder(p.toString(2)).reverse().toString();
        String bstring = new StringBuilder((n.subtract(p)).toString(2)).reverse().toString();
        if(astring.length() < bstring.length()) {
            astring = rightPadZeros(astring, bstring.length());
        } else if(bstring.length() < astring.length()) {
            bstring = rightPadZeros(bstring, astring.length());
        }
        return (new BigInteger(astring, 2).and(new BigInteger(bstring, 2)).compareTo(BigInteger.ZERO) > 0);
    }

    public static String rightPadZeros(String str, int num) {
        return String.format("%1$-" + num + "s", str).replace(' ', '0');
    }
}
