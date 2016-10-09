import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class SubSquares {

    public static ArrayList<BigInteger> getSubArraySum(int[][] a, int m, int n, int X, int Y){
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        for(int i = 0 ; i < m ; i++){
            if((i+X-1) == m) break;

            for(int j = 0 ; j < n ; j++){
                if(( j + Y-1 ) == n) break;;

                BigInteger sum = new BigInteger("0");

                for(int p = i ; p < i + X ; p++){
                    for(int q = j ; q < j + Y ; q++){
                        sum = sum.add(new BigInteger(String.valueOf(a[p][q])));
                    }
                }

                list.add(sum);

            }
        }
        return list;
    }


    public static void main(String[] args) throws FileNotFoundException {
        int n = 1000;
        int[][] file = new int[n][n];
        Scanner scanner = new Scanner(new File("egypt.in"));
        for(int i = 0; i < n; i++) {
            for(int d = 0; d < n; d++) {
                file[i][d] = Integer.valueOf(scanner.next());
            }
            scanner.nextLine();
        }

        BigInteger tot = new BigInteger("0");
        for(int k = 1; k <= n; k++) {
            ArrayList<BigInteger> list = getSubArraySum(file, n, n, k, k);
            for(BigInteger num : list) {
                tot = tot.add(num);
            }
        }
        System.out.println(tot);
    }

}
