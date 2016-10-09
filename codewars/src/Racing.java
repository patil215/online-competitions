import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Racing {
    public static ArrayList<Integer> pathPoints = new ArrayList<Integer>();

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
            newadjs[adjs.length] = train;
            adjacencies.add(train);
            planet.adjacencies = newadjs;

            Planet endPlanet = planets.get(end);
            Train[] adjsend = endPlanet.adjacencies;
            Train[] newadjsend = new Train[adjsend.length + 1];
            for(int d = 0; d < adjsend.length; d++) {
                newadjsend[d] = adjsend[d];
            }
            Train trainback = new Train(start, time, price, end);
            newadjsend[adjsend.length] = trainback;
            adjacencies.add(trainback);
            endPlanet.adjacencies = newadjsend;



        }

        computePaths(planets.get(0), planets);

        // Generate costs because of weird java error
        HashMap<String, Integer> pathCosts = new HashMap<String, Integer>();
        for(int i = 0; i < adjacencies.size(); i++) {
            Train train = adjacencies.get(i);
            // System.out.println(i + " " + planets.get(train.start).name + " " + planets.get(train.target).name + " " + train.points);
            if(pathCosts.containsKey(planets.get(train.start).name + "dif" + planets.get(train.target).name)) {
                System.out.println("DUPLICATE");
            }
            pathCosts.put(planets.get(train.start).name + "dif" +planets.get(train.target).name, train.points);
        }

        getSmallest(planets.get(1499), 0, pathCosts);

        int min = Integer.MAX_VALUE;
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
        public Planet[] previous;

        public Planet(String name) {
            this.name = name;
            adjacencies = new Train[0];
            previous = new Planet[0];
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
        public final int points;
        public final int start;
        public Train(int target, double weight, int points, int start) {
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
                double distanceThroughU = u.minDistance + weight;

                if(distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    Planet[] newP = new Planet[1];
                    newP[0] = u;
                    v.previous = newP;
                    vertexQueue.add(v);
                } else if(distanceThroughU == v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    Planet[] newP = new Planet[v.previous.length + 1];
                    for(int i = 0; i < v.previous.length; i++) {
                        newP[i] = v.previous[i];
                    }
                    newP[v.previous.length] = u;
                    v.previous = newP;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static void getSmallest(Planet current, int cost, HashMap<String, Integer> pathCosts) {
        if(current.previous.length != 0) {
            for(int i = 0; i < current.previous.length; i++) {
                Planet previous = current.previous[i];
                getSmallest(current.previous[i], cost + pathCosts.get(previous.name+ "dif" + current.name), pathCosts);
            }
        } else {
            pathPoints.add(cost);
        }
    }
}