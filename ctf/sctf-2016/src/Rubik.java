
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Rubik {

    static HashMap<String, String> f = new HashMap<String, String>();
    static HashMap<String, String> r = new HashMap<String, String>();
    static HashMap<String, String> n = new HashMap<String, String>();


    public static void main(String args[]) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("perm.txt"));

        Scanner scanner = new Scanner(new File("perms.dat"));

        f.put("u", "U E'");
        f.put("u'", "U' E");
        f.put("u2", "U2 E'2");
        f.put("u'2", "U'2 E2");
        f.put("l", "L M");
        f.put("l'", "L' M'");
        f.put("l2", "L2 M2");
        f.put("l'2", "L'2 M'2");
        f.put("f", "F S");
        f.put("f'", "F' S'");
        f.put("f2", "F2 S2");
        f.put("f'2", "F'2 S'2");
        f.put("r", "R M'");
        f.put("r'", "R' M");
        f.put("r2", "R2 M'2");
        f.put("r'2", "R'2 M2");
        f.put("b", "B S'");
        f.put("b'", "B' S");
        f.put("b2", "B2 S'2");
        f.put("b'2", "B'2 S2");
        f.put("d", "D E");
        f.put("d'", "D' E'");
        f.put("d2", "D2 E2");
        f.put("d'2", "D'2 E'2");

        r.put("M", "L' R");
        r.put("M'", "L R'");
        r.put("M2", "L'2 R2");
        r.put("M'2", "L2 R'2");
        r.put("E", "U D'");
        r.put("E'", "U' D");
        r.put("E2", "U2 D'2");
        r.put("E'2", "U'2 D2");
        r.put("S", "F' B");
        r.put("S'", "F B'");
        r.put("S2", "F'2 B2");
        r.put("S'2", "F2 B'2");

        n.put("U'2", "U2");
        n.put("L'2", "L2");
        n.put("F'2", "F2");
        n.put("R'2", "R2");
        n.put("B'2", "B2");
        n.put("D'2", "D2");

        while(scanner.hasNextLine()) {
            String[] moves = scanner.nextLine().split(" ");
            String newline = "";
            for (String move : moves) {
                if(f.containsKey(move)) {
                    newline += f.get(move) + " ";
                } else {
                    newline += move + " ";
                }
            }

            String[] newmoves = newline.split(" ");
            String finalline = "";

            for (String move : newmoves) {
                if(r.containsKey(move)) {
                    finalline += r.get(move) + " ";
                } else {
                    finalline += move + " ";
                }
            }

            String[] newermoves = finalline.split(" ");
            String lastline = "";

            for (String move : newermoves) {
                if(n.containsKey(move)) {
                    lastline += n.get(move) + " ";
                } else {
                    lastline += move + " ";
                }
            }

            lastline += "|";

            writer.print(lastline);
        }
        writer.close();
    }
}
