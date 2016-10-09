import java.util.*;

/**
 * Created by patil215 on 9/23/16.
 */
public class Statistics {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for(int i = 0; i < numCases; i++) {
            List<Set> friendSets = new ArrayList<>();
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            int c = scanner.nextInt();

            boolean impossible = false;
            if(n > p) {
                impossible = true;
            }

            HashSet<Integer> alreadySet = new HashSet<>();
            for(int d = 0; d < c; d++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                /*
                 Handle these cases:
                 1. Element a exists in set, b does not -> add b to a's set
                 2. b exists in set, a does not -> add a to b's set
                 3. a, b don't exist in a set -> add both to a new set
                 4. a, b exist in different sets -> merge the two sets
                  */

                int aLoc = locationInSetList(a, friendSets);
                int bLoc = locationInSetList(b, friendSets);

                if(aLoc > -1 && bLoc == -1) {
                    friendSets.get(aLoc).add(b);
                } else if(aLoc == -1 && bLoc > -1) {
                    friendSets.get(bLoc).add(a);
                } else if(aLoc == -1 && bLoc == -1) {
                    HashSet<Integer> newSet = new HashSet<>();
                    newSet.add(a);
                    newSet.add(b);
                    friendSets.add(newSet);
                } else if(aLoc > -1 && bLoc > -1) {
                    if(aLoc != bLoc) {
                        Set aSet = friendSets.get(aLoc);
                        Set bSet = friendSets.get(bLoc);
                        aSet.addAll(bSet);
                        friendSets.remove(bLoc);
                    }
                }
                alreadySet.add(a);
                alreadySet.add(b);
            }

            if(impossible) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            Collections.sort(friendSets, (o1, o2) -> {
                if(o1.size() > o2.size()) {
                    return -1;
                } else if(o2.size() > o1.size()) {
                    return 1;
                }
                return 0;
            });

            int amt = 0;
            int numFriends = 0;
            int index = 0;
            while(amt < n && index < friendSets.size()) {
                amt += friendSets.get(index).size();
                numFriends++;
                index++;
            }
            HashSet notAlreadySet = new HashSet<>();
            for(int d = 0; d < p; d++) {
                if(!alreadySet.contains(d)) {
                    notAlreadySet.add(d);
                }
            }
            Iterator<Integer> iterat = notAlreadySet.iterator();
            while(amt < n && iterat.hasNext()) {
                amt += 1;
                numFriends++;
                iterat.next();
            }
            System.out.println(numFriends);
        }
    }

    public static int locationInSetList(int element, List<Set> friendSets) {
        for(int i = 0; i < friendSets.size(); i++) {
            Set set = friendSets.get(i);
            if(set.contains(element)) {
                return i;
            }
        }
        return -1;
    }

}
