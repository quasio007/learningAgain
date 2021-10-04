package learning.graph;

import java.util.*;

/**
 * Through Kruskal Algorithm
 */
public class MinimumSpanningTree2 {

    public static void main(String[] args) {
        MinimumSpanningTree2 mst = new MinimumSpanningTree2();
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        convertToEdge(graph, queue);
        System.out.println();
    }

    private static void convertToEdge(Map<Integer, List<Node>> graph, PriorityQueue<Edge> queue) {
        for (var node : graph.keySet())
            for (var n : graph.get(node))
                queue.add(new Edge(node, n.node, n.weight));
    }

    static class Edge implements Comparable<Edge> {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private static void init(Map<Integer, List<Node>> graph) {

        graph.put(0, new ArrayList<>(
                Arrays.asList(
                        new Node(1, 2),
                        new Node(3, 1),
                        new Node(4, 4))));
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 2),
                        new Node(2, 3),
                        new Node(3, 3),
                        new Node(5, 7))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 3),
                        new Node(3, 5),
                        new Node(5, 8))));
        graph.put(3, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 1),
                        new Node(1, 3),
                        new Node(2, 5),
                        new Node(4, 9))));
        graph.put(4, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 4),
                        new Node(3, 9))));
        graph.put(5, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 7),
                        new Node(2, 8))));
    }

    static class Node implements Comparable<Node> {
        private int node;
        private int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int getNode() {
            return node;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
