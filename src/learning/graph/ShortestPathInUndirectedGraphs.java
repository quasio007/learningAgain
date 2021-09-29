package learning.graph;

import java.util.*;

/**
 * Below Algorithm is AKA Dijkstra's Algorithm
 */
public class ShortestPathInUndirectedGraphs {
    public static void main(String[] args) {
        ShortestPathInUndirectedGraphs shortestPathInUndirectedGraphs = new ShortestPathInUndirectedGraphs();
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);
        int src = 5;

        int totalNodes = graph.size();
//        As graph is one based:
        int[] distanceArr = new int[totalNodes + 1];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);

        shortestPathInUndirectedGraphs.shortestPathInUndirectedGraphs(graph, distanceArr, src);

        for (int i = 0; i < distanceArr.length; i++)
            if (distanceArr[i] != Integer.MAX_VALUE)
                System.out.println(String.format("Node '%s'   :  '%s' Distance", i, distanceArr[i]));
    }

    private void shortestPathInUndirectedGraphs(Map<Integer, List<Node>> graph, int[] distanceArr, int src) {
//        Setting distance for src  as 0
        distanceArr[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int node = pq.poll().node;
            int distanceTillNode = distanceArr[node];

            for (Node n : graph.get(node))
                if (distanceArr[n.node] > distanceTillNode + n.weight) {
                    distanceArr[n.node] = distanceTillNode + n.weight;
                    pq.add(new Node(n.node, distanceArr[n.node]));
                }
        }
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

    private static void init(Map<Integer, List<Node>> graph) {
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 2),
                        new Node(4, 1))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 2),
                        new Node(3, 4),
                        new Node(5, 5))));
        graph.put(3, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 4),
                        new Node(4, 3),
                        new Node(5, 1))));
        graph.put(4, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 1),
                        new Node(3, 3))));
        graph.put(5, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 5),
                        new Node(3, 1))));
    }
}
