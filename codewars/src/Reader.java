
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Reader {


        public static void main(String[] args) {
            int length = 100;
            BufferedReader br = null;
            char[] current = new char[length * 2];
            char[] previous = new char[length];

            try {
                br = new BufferedReader(new FileReader("haystackpt4.txt"));
                br.read(current);
                String s = "";
                while (!new String(current).equals(new String(previous))) {
                    BufferedReader br1 = new BufferedReader(new FileReader("words.txt"));
                    int counter = 0;
                    while ((s = br1.readLine()) != null) {

                        if (s.length() > 3 && new String(current).contains(s))
                            counter++;
                    }
                    if (counter > 2)
                        System.out.println(new String(current));
                    previous = current.clone();
                    br.read(current);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

}
