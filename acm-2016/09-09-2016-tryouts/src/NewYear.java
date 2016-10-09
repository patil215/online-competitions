import java.util.*;

/**
 * Created by patil215 on 9/9/16.
 */
public class NewYear {

    public static class Edge {
        int start;
        int end;
        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return start + " " + end;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for(int i = 0; i < t; i++) {

            int numCities = scanner.nextInt();
            int numFlights = scanner.nextInt();

            Edge[] flights = new Edge[numFlights];

            for(int d = 0; d < numFlights; d++) {
                flights[d] = new Edge(scanner.nextInt(), scanner.nextInt());
            }
            int ans = findMinN(flights, numCities);

        }
    }

    public static int findMinN(Edge[] edges, int numCities) {

        HashMap<String, Integer> citiesToWeights = new HashMap<>();

        for(int p = 0; p < numCities; p++) {
            for (int k = 0; k < numCities; k++) {

                Queue queue = new LinkedList<>();
                ArrayList<Integer> start = new ArrayList<>();
                start.add(k);
                queue.add(start);

                int answer = -1;
                while (!queue.isEmpty()) {
                    ArrayList<Integer> soFar = (ArrayList<Integer>) queue.poll();
                    int last = soFar.get(soFar.size() - 1);
                    if (last == p) {
                        answer = soFar.size() - 1;
                        citiesToWeights.put(new Edge(k, p).toString(), answer);
                        break;
                    }

                    for (int i = 0; i < edges.length; i++) {
                        Edge edge = edges[i];
                        if (edge.start == last && !soFar.contains(edge.end)) {
                            ArrayList<Integer> newList = new ArrayList<>(soFar);
                            newList.add(edge.end);
                            queue.add(newList);
                        }
                    }
                }
                if (answer == -1) {
                    citiesToWeights.put(new Edge(k, p).toString(), answer);
                }
            }
        }


        int maxMax = -1;

        // For each starting city combo make sure there's a mutual
        for(int i = 0; i < numCities; i++) {
            for(int d = 0; d < numCities; d++) {
                int maxDist = -1;
                for(int k = 0; k < numCities; k++) {
                    Edge edgeOne = new Edge(i, k);
                    Edge edgeTwo = new Edge(d, k);
                    if(citiesToWeights.containsKey(edgeOne.toString()) && citiesToWeights.containsKey(edgeTwo.toString()) && citiesToWeights.get(edgeOne.toString()) > -1 && citiesToWeights.get(edgeTwo.toString()) > -1) {
                        maxDist = Math.max(citiesToWeights.get(edgeOne.toString()), citiesToWeights.get(edgeTwo.toString()));
                    }
                }
                if(maxDist == -1) {
                    System.out.println("IMPOSSIBLE");
                    return 0;
                }
                if(maxDist > maxMax) {
                    maxMax = maxDist;
                }
            }
        }

        if(maxMax == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(maxMax);
        }

        return 0;
    }


}
