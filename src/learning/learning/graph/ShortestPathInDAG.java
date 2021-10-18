package learning.graph;

import java.util.*;

public class ShortestPathInDAG {
    public static void main(String[] args) {
        ShortestPathInDAG shortestPathInDAG = new ShortestPathInDAG();
        Map<Integer, List<Node>> graph = new HashMap<>();
        init(graph);
        int src = 4;

        int totalNodes = graph.size();
        int[] distanceArr = new int[totalNodes];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
//        Setting distance for src  as 0
        distanceArr[src] = 0;

        shortestPathInDAG.shortestPathInDAG(graph, distanceArr, totalNodes, src);

        for (int i = 0; i < distanceArr.length; i++)
            System.out.println(String.format("Node '%s'   :  '%s' Distance", i, distanceArr[i]));
    }

    private void shortestPathInDAG(Map<Integer, List<Node>> graph, int[] distanceArr, int totalNodes, int src) {
//        Step1 : Find Topological Sort:
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[totalNodes];

        for (var node : graph.keySet())
            if (!visited[node])
                dfs(graph, visited, stack, node);


//          Step 2 : traverse over stack and keep finding the shortest distance:
        while (!stack.isEmpty()) {
            int node = stack.pop();

//            Checking if scr is later or previous to current position
//            If it is later (distance is Integer.Max) then skip this iteration
            if (distanceArr[node] == Integer.MAX_VALUE) {
                continue;
            }

            int distanceTillNode = distanceArr[node];

            for (var n : graph.get(node))
                if (distanceArr[n.node] > distanceTillNode + n.weight)
                    distanceArr[n.node] = distanceTillNode + n.weight;
        }

    }

    private void dfs(Map<Integer, List<Node>> graph, boolean[] visited, Stack<Integer> stack, Integer node) {
        visited[node] = true;

        for (var n : graph.get(node))
            if (!visited[n.node])
                dfs(graph, visited, stack, n.node);

        stack.push(node);
    }

    private static void init(Map<Integer, List<Node>> graph) {
        graph.put(0, new ArrayList<Node>(
                Arrays.asList(
                        new Node(1, 2),
                        new Node(4, 1))));
        graph.put(1, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 3))));
        graph.put(2, new ArrayList<Node>(
                Arrays.asList(
                        new Node(3, 6))));
        graph.put(3, new ArrayList<Node>(Arrays.asList()));
        graph.put(4, new ArrayList<Node>(
                Arrays.asList(
                        new Node(2, 2),
                        new Node(5, 4))));
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
}
