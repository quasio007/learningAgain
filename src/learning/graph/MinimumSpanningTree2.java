package learning.graph;

import java.util.*;

/**
 * Through Kruskal Algorithm
 */
public class MinimumSpanningTree2 {

    public static void main(String[] args) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        convertToEdge(graph, edges);

        System.out.println("We have following edges :: ");
        for (var edge : getMST(edges, graph.size()))
            System.out.println(String.format("%s - %s : weight - %s", edge.startNode, edge.endNode, edge.weight));
    }

    private static List<Edge> getMST(PriorityQueue<Edge> edges, int totalNodes) {
        List<Edge> mst = new ArrayList<>();

//        Setting up Disjoint set:
        int[] parentArr = new int[totalNodes];
        int[] rank = new int[totalNodes];
        Arrays.fill(rank, 0);
        for (int i = 0; i < parentArr.length; i++)
            parentArr[i] = i;


        while (!edges.isEmpty()) {
            Edge edge = edges.remove();
            if (!belongsToSameComponent(edge.startNode, edge.endNode, parentArr)) {
                mst.add(edge);
                union(edge.startNode, edge.endNode, parentArr, rank);
            }
        }

        return mst;
    }

    private static void union(int node1, int node2, int[] parentArr, int[] rank) {
        int parent1 = findParent(node1, parentArr);
        int parent2 = findParent(node2, parentArr);
        if (rank[parent1] > rank[parent2])
            parentArr[parent2] = parent1;
        else if (rank[parent2] > rank[parent1])
            parentArr[parent1] = parent2;
        else {
            rank[parent1]++;
            parentArr[parent2] = parent1;
        }

    }

    private static boolean belongsToSameComponent(int node1, int node2, int[] parentArr) {
        return findParent(node1, parentArr) == findParent(node2, parentArr);
    }

    private static int findParent(int node, int[] parentArr) {
        if (parentArr[node] == node)
            return node;

        return parentArr[node] = findParent(parentArr[node], parentArr);
    }

    private static void convertToEdge(Map<Integer, List<Node>> graph, PriorityQueue<Edge> queue) {
        Set<String> edges = new HashSet<>();
        for (var node : graph.keySet())
            for (var n : graph.get(node)) {
                String currentEdge = getEdge(node, n.node);
                if (!edges.contains(currentEdge))
                    queue.add(new Edge(node, n.node, n.weight));
                edges.add(currentEdge);
            }
    }

    private static String getEdge(int node1, int node2) {
        if (node1 > node2) return String.format("%s-%s", node2, node1);
        else return String.format("%s-%s", node1, node2);
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
