import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SquareTriangle {

    public static void main(String args[]) throws NoSuchAlgorithmException, IOException {
        /*Scanner scanner = new Scanner(new File("sqrt2.txt"));
        String sqrt2 = scanner.next();
        PrintWriter writer = new PrintWriter("out.txt");
        int n = 15;

        BigDecimal topleft = new BigDecimal(sqrt2).multiply(new BigDecimal(2)).add(new BigDecimal(3));
        BigDecimal topright = new BigDecimal(sqrt2).multiply(new BigDecimal(-2)).add(new BigDecimal(3));
        BigDecimal bottom = new BigDecimal(sqrt2).multiply(new BigDecimal(4));
        System.out.println("Got original dubs");
        BigDecimal dub1 = topleft.pow(n);
        System.out.println("Got dub1");
        BigDecimal dub2 = topright.pow(n);
        System.out.println("Got dub2");
        BigDecimal dub3 = dub1.subtract(dub2).divide(bottom, 2000000, RoundingMode.HALF_UP);
        System.out.println("Got dub3");
        BigDecimal answer = dub3.pow(2);
        System.out.println("Got answer");
        BigInteger integer = roundBigDecimal(answer).toBigInteger();
        System.out.println("Got integer");
        String integerString = integer.toString();
        System.out.println(integerString.length());

        System.out.println(integerString);

        writer.println(integerString);
        writer.close(); // note writes a newline*/

        /*File file = new File("out.txt");
        Scanner scanner =new Scanner(file);
        byte[] ints = scanner.next().getBytes("UTF-8");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(ints);
        System.out.println(new BigInteger(1, digest).toString(16).toUpperCase());*/

        Scanner scanner = new Scanner(new File("out.txt"));
        String intstring = scanner.next();
        System.out.println(intstring.length());
        BigInteger integer = new BigInteger(intstring);
        System.out.println(sqrt(integer).pow(2).equals(integer));
        System.out.println(intstring.length());
       /* BigDecimal decimal = new BigDecimal(integer);
        decimal.pow

        System.out.println(integer.multiply(new BigInteger(8)).add(1))*/
    }

    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while(b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }

    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
}
