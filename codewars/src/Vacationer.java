import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Vacationer {

    public static void main(String args[]) throws FileNotFoundException {
        // Load in input file
        Scanner scanner = new Scanner(new File("vacationer_test6.txt"));

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
            int goodService = scanner.nextInt();

            Planet planet = planets.get(start);
            Train[] adjs = planet.adjacencies;
            Train[] newadjs = new Train[adjs.length + 1];
            for(int d = 0; d < adjs.length; d++) {
                newadjs[d] = adjs[d];
            }
            Train train;
            if(goodService == 0) {
                train = new Train(end, time, false, start);
            } else {
                train = new Train(end, time, true, start);
            }
            newadjs[adjs.length] = train;
            adjacencies.add(train);
            planet.adjacencies = newadjs;
        }

        computePaths(planets.get(0), planets);
        System.out.println(planets.get(numPlanets -1).minDistance);
        System.out.println(planets.get(numPlanets -1).minDistanceWithCoin);
    }


    public static class Planet implements Comparable<Planet> {

        public final String name;
        public Train[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public double minDistanceWithCoin = Double.POSITIVE_INFINITY;
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
        public final boolean goodService;
        public final int start;
        public Train(int target, double weight, boolean goodService, int start) {
            this.target = target;
            this.weight = weight;
            this.goodService = goodService;
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
                double distanceThroughUNoCoin = u.minDistance + weight;
                double distanceThroughUCoin = u.minDistanceWithCoin + weight;

                if(e.goodService) {
                    if(distanceThroughUNoCoin < v.minDistanceWithCoin) {
                        vertexQueue.remove(v);
                        v.minDistanceWithCoin = distanceThroughUNoCoin;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                } else {

                    if(distanceThroughUNoCoin < v.minDistance) {
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughUNoCoin;
                        v.previous = u;
                        vertexQueue.add(v);
                    }

                    if(distanceThroughUCoin < v.minDistanceWithCoin) {
                        vertexQueue.remove(v);
                        v.minDistanceWithCoin = distanceThroughUCoin;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }
    }
}