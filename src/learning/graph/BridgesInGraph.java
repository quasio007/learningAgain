package learning.graph;

import java.util.*;

public class BridgesInGraph {
    private static int timer = 0;
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        System.out.println("We have following bridges: ");
        for (var bridge : findBridges(graph))
            System.out.println(String.format("%s - %s", bridge.node1, bridge.node2));
    }

    private static List<Edge> findBridges(Map<Integer, List<Integer>> graph) {
        List<Edge> bridges = new ArrayList<>();
        int totalNodes = graph.size();

//        Initializing 2 arrays: timeOfInsertion and LowestTimeOfInsertion
        int[] timeOfInsertion = new int[totalNodes + 1]; // adding 1 to totalNodes , as graph is 1 base
        int[] lowestTimeOfInsertion = new int[totalNodes + 1];

        boolean[] visited = new boolean[totalNodes + 1];


        for (var node : graph.keySet())
            if (!visited[node])
                findBridges(graph, node, -1, timeOfInsertion, lowestTimeOfInsertion, visited, bridges);

        return bridges;
    }

    private static void findBridges(Map<Integer, List<Integer>> graph, int node, int parent, int[] timeOfInsertion, int[] lowestTimeOfInsertion, boolean[] visited, List<Edge> bridges) {
        visited[node] = true;
        timeOfInsertion[node] = ++timer;
        lowestTimeOfInsertion[node] = timer;

        for (var n : graph.get(node))
            if (!visited[n]) {
                findBridges(graph, n, node, timeOfInsertion, lowestTimeOfInsertion, visited, bridges);

//                Checking if lowestTimeOfInsertion of adjacent node is lower than updating for the current node
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[n], lowestTimeOfInsertion[node]);

                if (lowestTimeOfInsertion[n] > timeOfInsertion[node])
                    bridges.add(new Edge(node, n));

            } else if (n != parent)
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], timeOfInsertion[n]);

    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(1, new ArrayList<>(Arrays.asList(2, 4)));
        graph.put(2, new ArrayList<>(Arrays.asList(1, 3)));
        graph.put(3, new ArrayList<>(Arrays.asList(2, 4)));
        graph.put(4, new ArrayList<>(Arrays.asList(1, 3, 5)));
        graph.put(5, new ArrayList<>(Arrays.asList(4, 6)));
        graph.put(6, new ArrayList<>(Arrays.asList(5, 7, 9)));
        graph.put(7, new ArrayList<>(Arrays.asList(6, 8)));
        graph.put(8, new ArrayList<>(Arrays.asList(7, 9, 10)));
        graph.put(9, new ArrayList<>(Arrays.asList(6, 8)));
        graph.put(10, new ArrayList<>(Arrays.asList(8, 11, 12)));
        graph.put(11, new ArrayList<>(Arrays.asList(10, 12)));
        graph.put(12, new ArrayList<>(Arrays.asList(10, 11, 13)));
        graph.put(13, new ArrayList<>(Arrays.asList(12)));
    }

    private static class Edge {
        int node1;
        int node2;

        public Edge(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }
    }
}
