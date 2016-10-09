import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class prob09 {
    public static void main(String args[]) throws FileNotFoundException {

        Scanner in;
        in = new Scanner(System.in);

        ArrayList<Score> scores = new ArrayList<Score>();


        while(in.hasNextLine()) {
            int tName = in.nextInt();
            int tPts = in.nextInt();
            in.nextLine();
            if(tName != 0 || tPts != 0) {
                Score temp = new Score(tName,tPts);
                boolean found = false;
                for(Score a : scores) {
                    if(temp.ID == a.ID) {
                        a.pts += temp.pts;
                        found = true;
                    }
                }
                if(!found) scores.add(temp);
            }
        }

        Collections.sort(scores);

        for(int i=1;i<=5;i++) {
            System.out.println(i + " " + scores.get(i-1).toString());
        }
    }
}

class Score implements Comparable<Object> {

    public int ID;
    public int pts;

    public Score(int name, int p) {
        ID = name;
        pts = p;
    }



    @Override
    public int compareTo(Object o) {
        Score ob = ((Score) o);

        return ob.pts - pts;
    }

    public String toString() {
        return ID + " " + pts;
    }
}
