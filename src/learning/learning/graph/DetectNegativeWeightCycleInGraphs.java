package learning.graph;

import java.util.*;

/**
 * Bellman Ford Algorithm
 */
public class DetectNegativeWeightCycleInGraphs {
    public static void main(String[] args) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);
        int src = 1;
        int totalNodes = graph.size();
        int[] distanceArr = new int[totalNodes];
        Arrays.fill(distanceArr, (int) 10e2);
//        Setting distance for src  as 0
        distanceArr[src] = 0;
        List<Edge> edges = convertToEdge(graph);
        shortestDistance(edges, distanceArr, totalNodes);

        for (int i = 0; i < distanceArr.length; i++)
            System.out.println(String.format("Node '%s'   :  '%s' Distance", i, distanceArr[i]));
    }

    private static void shortestDistance(List<Edge> edges, int[] distance, int totalNodes) {
//     Step 1: Relax all edges N-1 times ; where N = total no. of nodes
        while (--totalNodes > 0) {
            for (var edge : edges)
                if (distance[edge.endNode] > distance[edge.startNode] + edge.weight)
                    distance[edge.endNode] = distance[edge.startNode] + edge.weight;
        }

//      Step 2: Checking if there is any negative cycle:
        for (var edge : edges)
            if (distance[edge.endNode] > distance[edge.startNode] + edge.weight){
                System.out.println("Yes there is a negative cycle");
                System.exit(3);
            }
    }

    private static List<Edge> convertToEdge(Map<Integer, List<Node>> graph) {
        List<Edge> edges = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (var node : graph.keySet())
            for (var n : graph.get(node)) {
                String currentEdge = getEdge(node, n.node);
                if (!set.contains(currentEdge))
                    edges.add(new Edge(node, n.node, n.weight));
                set.add(currentEdge);
            }
        return edges;
    }

    private static String getEdge(int node1, int node2) {
        if (node1 > node2) return String.format("%s-%s", node2, node1);
        else return String.format("%s-%s", node1, node2);
    }


    private static void init(Map<Integer, List<Node>> graph) {
        graph.put(0, new ArrayList<>(
                Arrays.asList(
                        new Node(1, 5))));
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, -2),
                        new Node(5, -3))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(4, 3))));
        graph.put(3, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 6)  //, new Node(4, -2)
                )));
        graph.put(4, new ArrayList<Node>(Arrays.asList(new Node(3, -2))));
        graph.put(5, new ArrayList<Node>(
                Arrays.asList(
                        new Node(3, 1))));
    }

    static class Node {
        private int node;
        private int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Edge {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}