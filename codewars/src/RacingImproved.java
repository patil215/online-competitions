import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RacingImproved {
    public static ArrayList<Double> pathPoints = new ArrayList<Double>();

    public static void main(String args[]) throws FileNotFoundException {
        // Load in input file
        Scanner scanner = new Scanner(new File("racing.txt"));

        int numPlanets = scanner.nextInt();
        int numTrains = scanner.nextInt();

        ArrayList<Planet> planets = new ArrayList<Planet>();

        // List of planets
        for(int i = 0; i < numPlanets; i++) {
            planets.add(new Planet(String.valueOf(i)));
        }

        // Generate adjacencies in graph
        ArrayList<Train> adjacencies = new ArrayList<Train>();
        for(int i = 0; i < numTrains; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int time = scanner.nextInt();
            int price = scanner.nextInt();
            Planet planet = planets.get(start);
            Train[] adjs = planet.adjacencies;
            Train[] newadjs = new Train[adjs.length + 1];
            for(int d = 0; d < adjs.length; d++) {
                newadjs[d] = adjs[d];
            }
            Train train = new Train(end, time, price, start);
            adjacencies.add(train);
            newadjs[adjs.length] = train;
            planet.adjacencies = newadjs;
        }

        computePaths(planets.get(0), planets);

        System.out.println((Math.round(planets.get(1499).minDistance)));
        System.out.println(((planets.get(1499).minDistance - Math.round(planets.get(1499).minDistance))) * 100000000);

        // Generate costs because of weird java error
        HashMap<String, Double> pathCosts = new HashMap<String, Double>();
        for(int i = 0; i < adjacencies.size(); i++) {
            Train train = adjacencies.get(i);
            //System.out.println(i + " " + planets.get(train.start).name + " " + planets.get(train.target).name + " " + train.points);
            pathCosts.put(planets.get(train.start).name + "dif" +planets.get(train.target).name, train.points);
        }

        getShortestPathTo(planets.get(1499));

        double min = Double.MAX_VALUE;
        for(int i = 0; i < pathPoints.size(); i++) {
            if(pathPoints.get(i) < min) {
                min = pathPoints.get(i);
            }
        }

        System.out.println("MINIMUM TIME: " + planets.get(1499).minDistance);
        System.out.println("MINIMUM POINTS: " + min);


    }


    public static class Planet implements Comparable<Planet> {

        public final String name;
        public Train[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Planet previous;

        public Planet(String name) {
            this.name = name;
            adjacencies = new Train[0];
        }

        public String toString() {
            return name;
        }


        @Override
        public int compareTo(Planet other) {
            return Double.compare(minDistance, other.minDistance);
        }
    }

    public static class Train {
        public final int target;
        public final double weight;
        public final double points;
        public final int start;
        public Train(int target, double weight, double points, int start) {
            this.target = target;
            this.weight = weight;
            this.points = points;
            this.start = start;
        }
    }

    public static void computePaths(Planet source, ArrayList<Planet> planets)
    {
        source.minDistance = 0.;
        PriorityQueue<Planet> vertexQueue = new PriorityQueue<Planet>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Planet u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Train e : u.adjacencies)
            {
                Planet v = planets.get(e.target);
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight + ((e.points / 100000000));

                if(distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Planet> getShortestPathTo(Planet target)
    {
        List<Planet> path = new ArrayList<Planet>();
        for (Planet vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
}