import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Implements very fast dictionary storage and retrieval.
 * Only depends upon the core String class.
 *
 * // Credit:
 * @author Melinda Green - © 2010 Superliminal Software.
 * Free for all uses with attribution.
 */
public class TrieMap {

    private Object[] mChars = new Object[256];
    private Object mPrefixVal; // Used only for values of prefix keys.

    // Simple container for a string-value pair.
    private static class Leaf {
        public String mStr;
        public Object mVal;
        public Leaf(String str, Object val) {
            mStr = str;
            mVal = val;
        }
    }

    public TrieMap() {
    }

    public boolean isEmpty() {
        if(mPrefixVal != null) {
            return false;
        }
        for(Object o : mChars) {
            if(o != null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Inserts a key/value pair.
     *
     * @param key may be empty or contain low-order chars 0..255 but must not be null.
     * @param val Your data. Any data class except another TrieMap. Null values erase entries.
     */
    public void put(String key, Object val) {
        assert key != null;
        assert !(val instanceof TrieMap); // Only we get to store TrieMap nodes. TODO: Allow it.
        if(key.length() == 0) {
            // All of the original key's chars have been nibbled away 
            // which means this node will store this key as a prefix of other keys.
            mPrefixVal = val; // Note: possibly removes or updates an item.
            return;
        }
        char c = key.charAt(0);
        Object cObj = mChars[c];
        if(cObj == null) { // Unused slot means no collision so just store and return;
            if(val == null) {
                return; // Don't create a leaf to store a null value.
            }
            mChars[c] = new Leaf(key, val);
            return;
        }
        if(cObj instanceof TrieMap) {
            // Collided with an existing sub-branch so nibble a char and recurse.
            TrieMap childTrie = (TrieMap)cObj;
            childTrie.put(key.substring(1), val);
            if(val == null && childTrie.isEmpty()) {
                mChars[c] = null; // put() must have erased final entry so prune branch.
            }
            return;
        }
        // Collided with a leaf 
        if(val == null) {
            mChars[c] = null; // Null value means to remove any previously stored value.
            return;
        }
        assert cObj instanceof Leaf;
        // Sprout a new branch to hold the colliding items.
        Leaf cLeaf = (Leaf)cObj;
        TrieMap branch = new TrieMap();
        branch.put(key.substring(1), val); // Store new value in new subtree.
        branch.put(cLeaf.mStr.substring(1), cLeaf.mVal); // Plus the one we collided with.
        mChars[c] = branch;
    }


    /**
     * Retrieve a value for a given key or null if not found.
     */
    public Object get(String key) {
        assert key != null;
        if(key.length() == 0) {
            // All of the original key's chars have been nibbled away 
            // which means this key is a prefix of another.
            return mPrefixVal;
        }
        char c = key.charAt(0);
        Object cVal = mChars[c];
        if(cVal == null) {
            return null; // Not found.
        }
        assert cVal instanceof Leaf || cVal instanceof TrieMap;
        if(cVal instanceof TrieMap) { // Hash collision. Nibble first char, and recurse.
            return ((TrieMap)cVal).get(key.substring(1));
        }
        if(cVal instanceof Leaf) {
            // cVal contains a user datum, but does the key match its substring?
            Leaf cPair = (Leaf)cVal;
            if(key.equals(cPair.mStr)) {
                return cPair.mVal; // Return user's data value.
            }
        }
        return null; // Not found.
    }

    private static ArrayList<String> returnPaths(ArrayList<String> cur, char[][] grid, int x, int y, String startingString, boolean[][] lettersUsed) {
        startingString += grid[x][y];
        lettersUsed[x][y] = true;
        cur.add(startingString);

        // Check right
        if(x < grid.length - 1 && !lettersUsed[x + 1][y]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x+1, y, startingString, newLettersUsed);
        }

        // Check left
        if(x > 0 && !lettersUsed[x - 1][y]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x - 1, y, startingString, newLettersUsed);
        }

        // Check down
        if(y < grid[0].length - 1 && !lettersUsed[x][y + 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x, y + 1, startingString, newLettersUsed);
        }


        // Check up
        if(y > 0 && !lettersUsed[x][y - 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x, y - 1, startingString, newLettersUsed);
        }

        // Check bottom right
        if(x < grid.length - 1 && y < grid[0].length - 1 && !lettersUsed[x + 1][y + 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x + 1, y + 1, startingString, newLettersUsed);

        }

        // Check bottom left
        if(x > 0 && y < grid[0].length - 1 && !lettersUsed[x - 1][y + 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x - 1, y + 1, startingString, newLettersUsed);

        }

        // Check top right
        if(x < grid.length - 1 && y > 0 && !lettersUsed[x + 1][y - 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x + 1, y - 1, startingString, lettersUsed);

        }

        // Check top left
        if(x > 0 && y > 0 && !lettersUsed[x - 1][y - 1]) {
            boolean[][] newLettersUsed = new boolean[4][4];
            for(int i = 0; i < 4; i++) {
                for(int d = 0; d < 4; d++) {
                    newLettersUsed[i][d] = lettersUsed[i][d];
                }
            }
            returnPaths(cur, grid, x - 1, y - 1, startingString, lettersUsed);
        }
        return cur;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final Scanner scan = new Scanner(new File("words.txt"));
        final PrintWriter writer = new PrintWriter(new File("condensedwords.txt"));
        final char[] gridChars = new char[] {'e', 'c', 'a', 'l', 'p', 'h', 'n', 'b', 'o', 'q', 't', 'y'};
        while(scan.hasNextLine()) {
            final String word = scan.nextLine().toLowerCase();

            boolean allValidChars = true;
            for(int i = 0; i < word.length(); i++) {
                boolean validChar = false;
                for(int d = 0; d < gridChars.length; d++) {
                    if(word.charAt(i) == gridChars[d]) {
                        validChar = true;
                    }
                }
                if(!validChar) {
                    allValidChars = false;
                }
            }
            if(allValidChars) {
                writer.println(word);
            }
        }
        writer.close();

        Scanner scanner = new Scanner(new File("condensedwords.txt"));

        HashSet<String> matchingWords = new HashSet<String>();

        TrieMap trieMap = new TrieMap();

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine().toLowerCase();
            trieMap.put(word, word);
        }

        // Compare the words to the paths
        char[][] grid = new char[][] {{'e', 'e', 'c', 'a'}, {'a', 'l', 'e', 'p'}, {'h', 'n', 'b', 'o'}, {'q', 't', 't', 'y'}};

        // Iterate through starting characters on grid
        for(int i = 0; i < 4; i++) {
            for(int d = 0; d < 4; d++) {
                boolean[][] lettersUsed = new boolean[grid.length][grid[0].length];
                ArrayList<String> paths = returnPaths(new ArrayList<String>(), grid, i, d, "", lettersUsed);
                for(int r = 0; r < paths.size(); r++) {
                    if(trieMap.get(paths.get(r)) != null) {
                        matchingWords.add(paths.get(r));
                    }
                }
            }
        }

        System.out.println(matchingWords.size()); // The flag
    }
}