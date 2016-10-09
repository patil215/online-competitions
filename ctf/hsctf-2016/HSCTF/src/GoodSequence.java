import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GoodSequence {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("inputfile.txt"));
        long[] nums = new long[100000000];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextLong();
        }
        System.out.println("finished reading file");

        /*ArrayList<Sequence> sequences = new ArrayList<Sequence>();
        int subsequenceStart = 0;
        int subsequenceLength = 1;
        for(int i = 1; i < nums.length; i++) {
            if(!(Math.abs(nums[i] - nums[i-1]) <= 1)) {
                if(subsequenceLength > 10) {
                    Sequence seq = new Sequence();
                    seq.length = subsequenceLength;
                    seq.start = subsequenceStart;
                    sequences.add(seq);
                }
                subsequenceStart = i;
                subsequenceLength = 1;
            } else {
                subsequenceLength++;
            }
        }

        Collections.sort(sequences);
        PrintWriter writer = new PrintWriter(new File("sequenceout.txt"));
        for(Sequence seq : sequences) {
            writer.println(seq.length + " " + seq.start);
        }
        writer.close();*/

        long start = System.currentTimeMillis();
        int sumImportantNums = 0;
        for(int i = 62183330; i < 62183400; i++) {
            System.out.println(i + " " + nums[i]);
        }
        for(int i = 1; i < nums.length - 1; i++) {
            if(Math.abs(nums[i] - nums[i-1]) <= 1) {

            } else if(Math.abs(nums[i + 1] - nums[i - 1]) <= 1) {
                sumImportantNums++;
                System.out.println("num index: " + i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("important nums: " + sumImportantNums);
        System.out.println("time taken: " + (end - start));

        /*
        int maxlength = -1;
        int maxindexskipped = -1;
        int seqlength = 1;
        int indexskipped = -1;
        int chances = 0;

        for(int i = 1; i < nums.length; i++) {
            if(i % 1000000 == 0) {
                System.out.println((i / 1000000) + " percent done");
            }
            //System.out.println(indexskipped);
            if(Math.abs(nums[i] - nums[i-1]) <= 1) {
                seqlength++;
            } else if(chances < 1) {
                indexskipped = i;
                chances++;
            } else {
                if(seqlength > maxlength) {
                    maxlength = seqlength;
                    maxindexskipped = indexskipped;
                }
                chances = 0;
                indexskipped = i;
                seqlength = i - (indexskipped);
            }
        }
        /*if(seqlength > maxlength) {
            maxlength = seqlength;
        }*/
        // System.out.println(nums[maxindexskipped]);
        // System.out.println(maxlength + " " + maxindexskipped);
    }

    public static class Sequence implements Comparable<Sequence> {
        int start;
        int length;


        @Override
        public int compareTo(Sequence o) {
            if(o.length < length) {
                return 1;
            } else if(length < o.length) {
                return -1;
            }
            return 0;
        }
    }
}
