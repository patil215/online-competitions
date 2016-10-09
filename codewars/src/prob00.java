
public class prob00 {
    public static void main(String args[]) {
        System.out.println("Welcome, Barcelona and Newcastle!");
        String thing = "aweowe";
        String unique = "";
        for(int i = 0; i < thing.length(); i++) {
            if(!unique.contains(thing.charAt(i) + "")) {
                unique+=thing.charAt(i);
            }
        }
        permute(0, "", unique);
    }

    public static void permute(int currentIndex, String currentString, String thing) {
        if (currentIndex < thing.length()) {
            for (int i = 0; i < thing.length(); i++) {
                if (!currentString.contains(thing.charAt(i) + "")) {
                    permute(currentIndex + 1, currentString + thing.charAt(i), thing);
                }
            }
        } else {
            System.out.println(currentString);
        }
    }
}