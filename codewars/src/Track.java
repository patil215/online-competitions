import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Track {
    public static class Hurdle {
        public int row;
        public int lane;
        public Hurdle(int row, int lane) {
            this.row = row;
            this.lane = lane;
        }
    }

    public static class Blank {
        public int row;
        public int lane;
        public Blank(int row, int lane) {
            this.row = row;
            this.lane = lane;
        }
        public String toString() {
            return row + " " + lane;
        }
    }




    public static void main(String args[]) throws FileNotFoundException {

        // Load in input file
        Scanner scanner = new Scanner(new File("case6.txt"));
        int rows = scanner.nextInt();
        int lanes = scanner.nextInt();
        ArrayList<Hurdle> hurdles = new ArrayList<Hurdle>();
        int numHurdles = scanner.nextInt();
        for(int i = 0; i < numHurdles; i++) {
            int row = scanner.nextInt();
            int lane = scanner.nextInt();
            hurdles.add(new Hurdle(row, lane));
        }

        System.out.println("next");

        int endLength = 0;
        int startLength = 0;

        // Generate the list of blanks (places where there is no hurdle). This is the vertices of the graph
        ArrayList<Blank> blanks = new ArrayList<Blank>();
        int hurdleIndex = 0;
        for(int i = 0; i < rows - 1; i++) {
            for(int d = 0; d < lanes; d++) {
                Hurdle hurdle = hurdles.get(hurdleIndex);
                if(hurdle.lane != d || hurdle.row != i) {
                    blanks.add(new Blank(i, d));
                    if(i == 0) {
                        startLength++;
                    }
                    if(i == rows - 2) {
                        endLength++;
                    }
                } else {
                    if(hurdleIndex < numHurdles - 1) {
                        hurdleIndex++;
                    }
                }
            }
        }

        System.out.println(endLength);


        // Generate a list of vertices
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for(int i = 0; i < blanks.size(); i++) {
            Blank blank = blanks.get(i);
            vertices.add(new Vertex(String.valueOf(blank.row) + "," + String.valueOf(blank.lane)));
        }

        // Compute adjacencies
        for(int i = 0; i < vertices.size(); i++) {
            if(i % 1000 == 0) {
                System.out.println(i);
            }
            Blank blank = blanks.get(i);
            Vertex vertex = vertices.get(i);
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for(int d = i; d < i+102; d++) {
                if(d < blanks.size()) {
                    Blank newBlank = blanks.get(d);
                    Vertex newVertex = vertices.get(d);
                    if (newBlank.row == (blank.row + 1) && newBlank.lane == blank.lane) {
                        edges.add(new Edge(newVertex, 0));
                    } else if ((newBlank.row == (blank.row + 1) && newBlank.lane == blank.lane - 1) || (newBlank.row == (blank.row + 1) && newBlank.lane == blank.lane + 1)) {
                        edges.add(new Edge(newVertex, 1));
                    }
                }
            }

            vertex.adjacencies = edges.toArray(new Edge[edges.size()]);
        }

        double shortest = Integer.MAX_VALUE;

        // For each starting position, compute the path
        for(int i = 0; i < startLength; i++) {
            computePaths(vertices.get(i));
            for(int d = blanks.size() - 1 - endLength; d < blanks.size(); d++) {
                if(vertices.get(d).minDistance < shortest) {
                    shortest = vertices.get(d).minDistance;
                }
            }
        }

        System.out.println((int)shortest);








/*

        Vertex n0 = new Vertex("n0");
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for(int i = 0; i < hurdles.size(); i++) {
            vertices.add(new Vertex(String.valueOf(hurdles.get(i).row) + "," + String.valueOf(hurdles.get(i).lane)));
        }

        // Compute adjacencies
        for(int i = 0; i < hurdles.size(); i++) {
            Vertex vertice = vertices.get(i);
            Hurdle hurdle = hurdles.get(i);

            // Find hurdles next to


            // Find hurdles in front of
        }


        // mark all the vertices
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex D = new Vertex("D");
        Vertex F = new Vertex("F");
        Vertex K = new Vertex("K");
        Vertex J = new Vertex("J");
        Vertex M = new Vertex("M");
        Vertex O = new Vertex("O");
        Vertex P = new Vertex("P");
        Vertex R = new Vertex("R");
        Vertex Z = new Vertex("Z");

        // set the edges and weight
        A.adjacencies = new Edge[]{ new Edge(M, 8) };
        B.adjacencies = new Edge[]{ new Edge(D, 11) };
        D.adjacencies = new Edge[]{ new Edge(B, 11) };
        F.adjacencies = new Edge[]{ new Edge(K, 23) };
        K.adjacencies = new Edge[]{ new Edge(O, 40) };
        J.adjacencies = new Edge[]{ new Edge(K, 25) };
        M.adjacencies = new Edge[]{ new Edge(R, 8) };
        O.adjacencies = new Edge[]{ new Edge(K, 40) };
        P.adjacencies = new Edge[]{ new Edge(Z, 18) };
        R.adjacencies = new Edge[]{ new Edge(P, 15) };
        Z.adjacencies = new Edge[]{ new Edge(P, 18) };


        computePaths(A); // run Dijkstra
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<Vertex> path = getShortestPathTo(Z);
        System.out.println("Path: " + path);
*/
    }











    public static class Vertex implements Comparable<Vertex>
    {
        public final String name;
        public Edge[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;
        public Vertex(String argName) { name = argName; }
        public String toString() { return name; }
        public int compareTo(Vertex other)
        {
            return Double.compare(minDistance, other.minDistance);
        }

    }


    public static class Edge
    {
        public final Vertex target;
        public final double weight;
        public Edge(Vertex argTarget, double argWeight)
        { target = argTarget; weight = argWeight; }
    }


    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
}