import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpaceFriends {

    public static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int edgeweight;

        @Override
        public int compareTo(Edge edge) {
            if(edgeweight > edge.edgeweight) {
                return -1;
            } else if(edgeweight < edge.edgeweight) {
                return 1;
            }
            return 0;
        }

        public String toString() {
            return String.valueOf(edgeweight);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        for (int d = 1; d < 5; d++) {
            Scanner scanner = new Scanner(new File("network" + d + ".txt"));
            int numNodes = scanner.nextInt();
            int numEdges = scanner.nextInt();
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < numEdges; i++) {
                Edge edge = new Edge();
                edge.a = scanner.nextInt();
                edge.b = scanner.nextInt();
                edge.edgeweight = scanner.nextInt();
                edges.add(edge);
            }

            Collections.sort(edges);

            HashMap<Integer, HashSet<Integer>> adjacencies = new HashMap<Integer, HashSet<Integer>>();
            for (Edge edge : edges) {
                if (adjacencies.containsKey(edge.a)) {
                    adjacencies.get(edge.a).add(edge.b);
                } else {
                    adjacencies.put(edge.a, new HashSet<Integer>(edge.b));
                }
                if(adjacencies.containsKey(edge.b)) {
                    adjacencies.get(edge.b).add(edge.a);
                } else {
                    adjacencies.put(edge.b, new HashSet<Integer>(edge.b));
                }
            }

            int targetSize = numNodes / 2;
            System.out.println("Target: " + targetSize);

            HashSet<Integer> left = new HashSet<Integer>();
            HashSet<Integer> right = new HashSet<Integer>();

            int max = -1;

            for (int i = 0; i < edges.size(); i++) {
                int a = edges.get(i).a;
                int b = edges.get(i).b;
                if (((left.contains(a) || left.contains(b)) && (right.contains(a) || right.contains(b)))) {

                    HashSet<Integer> moveListLeft = generateListToMove(adjacencies, a, new HashSet<Integer>());
                    HashSet<Integer> moveListRight = new HashSet<Integer>(moveListLeft);

                    // Strip all not in right from left
                    moveListLeft.retainAll(right);

                    // Strip all not in left from right
                    moveListRight.retainAll(left);

                    if (moveListLeft.size() <= moveListRight.size() && (left.size() + moveListLeft.size() <= targetSize)) {
                        left.addAll(moveListLeft);
                        right.removeAll(moveListLeft);
                    } else if (right.size() + moveListRight.size() <= targetSize) {
                        right.addAll(moveListRight);
                        left.removeAll(moveListRight);
                    } else {
                        max = edges.get(i).edgeweight;
                        break;
                    }
                } else {
                    if (left.contains(a) && left.contains(b)) {

                    } else if(left.contains(a)) {
                        if(left.size() < targetSize) {
                            left.add(b);
                        } else {
                            HashSet<Integer> toMove = generateListToMove(adjacencies, a, new HashSet<Integer>());
                            toMove.retainAll(left);
                            if(right.size() + toMove.size() <= targetSize) {
                                right.addAll(toMove);
                                left.removeAll(toMove);
                            }
                            else {
                                max = edges.get(i).edgeweight;
                                break;
                            }
                        }
                    } else if(left.contains(b)) {
                        if(left.size() < targetSize) {
                            left.add(a);
                        } else {
                            HashSet<Integer> toMove = generateListToMove(adjacencies, b, new HashSet<Integer>());
                            toMove.retainAll(left);
                            if(right.size() + toMove.size() <= targetSize) {
                                right.addAll(toMove);
                                left.removeAll(toMove);
                            }
                            else {
                                max = edges.get(i).edgeweight;
                                break;
                            }
                        }
                    } else if (right.contains(a) && right.contains(b)) {

                    } else if(right.contains(a)) {
                        if(right.size() < targetSize) {
                            right.add(b);
                        }else {
                            HashSet<Integer> toMove = generateListToMove(adjacencies, a, new HashSet<Integer>());
                            toMove.retainAll(left);
                            if(left.size() + toMove.size() <= targetSize) {
                                left.addAll(toMove);
                                right.removeAll(toMove);
                            }
                            else {
                                max = edges.get(i).edgeweight;
                                break;
                            }
                        }
                    } else if(right.contains(b)) {
                        if(right.size() < targetSize) {
                            right.add(a);
                        } else {
                            HashSet<Integer> toMove = generateListToMove(adjacencies, b, new HashSet<Integer>());
                            toMove.retainAll(left);
                            if(left.size() + toMove.size() <= targetSize) {
                                left.addAll(toMove);
                                right.removeAll(toMove);
                            }
                            else {
                                max = edges.get(i).edgeweight;
                                break;
                            }
                        }
                    } else {
                        if (left.size() <= right.size()) {
                            if(left.size() < targetSize) {
                                // System.out.println("fu");
                                left.add(a);
                                left.add(b);
                            }
                        } else {
                            if(right.size() < targetSize) {
                                // System.out.println("fuc");
                                right.add(a);
                                right.add(b);
                            }
                        }
                    }
                }
            }
            System.out.println(left.size() + " " + right.size());
            System.out.println(max);
        }
    }

    public static HashSet<Integer> generateListToMove(HashMap<Integer, HashSet<Integer>> adjacencies, int cur, HashSet<Integer> visited) {
        HashSet<Integer> list = new HashSet<Integer>();
        HashSet<Integer> conns = adjacencies.get(cur);
        list.add(cur);
        visited.add(cur);
        for(int i : conns) {
            if(!visited.contains(i)) {
                list.addAll(generateListToMove(adjacencies, i, visited));
            }
        }
        return list;
    }
}
