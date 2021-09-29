package learning.graph;

import java.util.*;


public class MinimumSpanningTree {

    public static void main(String[] args) {
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);
        int startingNode = 0;

        int[] parent = minimumSpanningTree.primAlgorithm(graph, startingNode);
        for (int i = 0; i < parent.length; i++)
            if (parent[i] != -1)
                System.out.println(String.format("Parent for node '%s' is '%s'", i, parent[i]));
    }

    /**
     * Prims Algorithm
     *
     * @param graph
     */
    public int[] primAlgorithm(Map<Integer, List<Node>> graph, int startingNode) {
        int totalNodes = graph.size();

//        key or Distance array:
        int[] key = new int[totalNodes];
        int[] parent = new int[totalNodes];
        boolean[] mst = new boolean[totalNodes];

//        Filling infinity or Integer.MAX_VALUE in key array:
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>(totalNodes);

        pq.add(new Node(startingNode, 0));

//        Setting parent for parent as -1
        parent[startingNode] = -1;
        key[startingNode] = 0;
//        mst[startingNode] = true; // Optional as done in below loop as well

        while (--totalNodes > 0) {
            int node = pq.remove().node;
            while (mst[node]){
                node = pq.remove().node;
            }

            mst[node] = true;

            for (var n : graph.get(node)) {
                if (key[n.node] > n.weight && !mst[n.node]) {
                    key[n.node] = n.weight;
                    parent[n.node] = node;
                    pq.add(new Node(n.node, n.weight));
                }

            }

        }

        return parent;
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
//        Graph 1:
        /*
        graph.put(0, new ArrayList<Node>(
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

         */


//        Graph 2:
/*
        graph.put(0, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 2),
                        new Node(3, 6))));
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 2),
                        new Node(2, 3),
                        new Node(3, 8),
                        new Node(4, 5))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 3),
                        new Node(4, 7))));
        graph.put(3, new ArrayList<Node>(Arrays.asList()));
        graph.put(4, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 5),
                        new Node(2, 7))));

 */
//        Graph 3:
        graph.put(0, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 1),
                        new Node(2, 3))));
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 1),
                        new Node(2, 2),
                        new Node(3, 8))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(0, 3),
                        new Node(1, 2))));
        graph.put(3, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 8),
                        new Node(4, 3),
                        new Node(5, 1))));
        graph.put(4, new ArrayList<Node>(
                Arrays.asList(
                        new Node(3, 3),
                        new Node(5, 2))));
        graph.put(5, new ArrayList<Node>(
                Arrays.asList(
                        new Node(3, 1),
                        new Node(4, 2))));

    }
}
