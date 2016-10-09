import sun.security.util.BigInt;

import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class program {
    public static void main(String args[]) throws FileNotFoundException {

        //System.out.println(g(30) - w(30));




        for(int x = 4; x < 10; x++) {

            ArrayList<BigInteger> v = new ArrayList<BigInteger>();
            v.add(BigInteger.ZERO);
            v.add(BigInteger.ONE);
            for (int i = 0; i < x - 2; i++) {
                v.add(v.get(i).add(v.get(i + 1)).pow(2));
            }

            ArrayList<BigInteger> vals = new ArrayList<BigInteger>();
            vals.add(BigInteger.ZERO);
            vals.add(BigInteger.ONE);
            for (int i = 0; i < x - 2; i++) {
                vals.add(vals.get(i).pow(2).add(vals.get(i + 1).pow(2)));
            }
            System.out.println((v.get(x - 1).subtract(vals.get(x - 1)).toString()));
        }
    }


    public static double g(double cur) {
        if(cur == 0) {
            return cur;
        } else if (cur == 1) {
            return cur;
        } else {

            return Math.pow(g(cur - 1)+g(cur-2), 2);
        }
    }


    public static int w(int cur) {
        System.out.println(cur);
    if(cur == 0) {
        return 0;
    }
        else if(cur == 1){
            return 1;
        } else {
        int newThing = w(cur-1);
        int newerThing = w(cur-2);
        return (newThing * newThing) + (newerThing * newerThing);
    }
    }
}