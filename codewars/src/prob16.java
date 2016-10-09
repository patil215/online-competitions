import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class prob16 {
    public static void main(String args[]) throws FileNotFoundException{
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        file.nextLine();

        HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
        for (int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(file.nextLine());
            String start = st.nextToken();
            String name = st.nextToken();
            String end = st.nextToken();
            if(map.get(name) == null){
                map.put(name, new HashMap<String, String>());
            }
            map.get(name).put(start,end);
        }
        //System.out.println(map.get("m").get("#"));
        String seq = file.nextLine();
        String cur = "#";
        for(int i = 0; i<seq.length(); i++){
            System.out.print(map.get(""+seq.charAt(i)).get(cur));
            cur = map.get(""+seq.charAt(i)).get(cur);
        }
     }
}
