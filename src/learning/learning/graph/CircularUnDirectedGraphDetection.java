package learning.graph;

import java.util.*;

public class CircularUnDirectedGraphDetection {
    public static void main(String[] args) {
        CircularUnDirectedGraphDetection circularUnDirectedGraphDetection = new CircularUnDirectedGraphDetection();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        System.out.println("Is the current graph circular through bfs : " + circularUnDirectedGraphDetection.bfs(graph));

        System.out.println("Is the current graph circular through dfs : " + circularUnDirectedGraphDetection.dfs(graph));
    }

    private boolean bfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes + 1];
        for (var node : graph.keySet()) {
            if (!visited[node]) {
                if (bfs(graph, visited, new Node(node, 0)))
                    return true;
            }
        }
        return false;
    }

    private boolean bfs(Map<Integer, List<Integer>> graph, boolean[] visited, Node startingNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(startingNode);
        visited[startingNode.node] = true;

        while (!queue.isEmpty()) {
            int node = queue.peek().node;
            int parent = queue.remove().parent;
            for (var n : graph.get(node))
                if (!visited[n]) {
                    queue.add(new Node(n, node));
                    visited[n] = true;
                } else if (n != parent)
                    return true;

        }

        return false;
    }

    class Node {
        int node;
        int parent;

        public Node(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }


    private boolean dfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes + 1];

        for (var node : graph.keySet())
            if (!visited[node])
                if (dfs(graph, visited, node, 0))
                    return true;


        return false;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int node, int parent) {
        visited[node] = true;
        for (var n : graph.get(node))
            if (!visited[n]) {
                if (dfs(graph, visited, n, node))
                    return true;
            } else if (n != parent)
                return true;

        return false;
    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(1, new ArrayList<>(Arrays.asList(7)));
        graph.put(2, new ArrayList<>(Arrays.asList(5)));
        graph.put(3, new ArrayList<>(Arrays.asList(5, 9)));
        graph.put(4, new ArrayList<>(Arrays.asList(5, 6)));
        graph.put(5, new ArrayList<>(Arrays.asList(2, 3, 4)));
        graph.put(6, new ArrayList<>(Arrays.asList(4, 9)));
        graph.put(7, new ArrayList<>(Arrays.asList(1, 8)));
        graph.put(8, new ArrayList<>(Arrays.asList(7)));
        graph.put(9, new ArrayList<>(Arrays.asList(3, 6, 10)));
        graph.put(10, new ArrayList<>(Arrays.asList(9)));
        graph.put(11, new ArrayList<>(Arrays.asList(12, 14)));
        graph.put(12, new ArrayList<>(Arrays.asList(11, 13)));
        graph.put(13, new ArrayList<>(Arrays.asList(12)));
        graph.put(14, new ArrayList<>(Arrays.asList(11, 15, 16)));
        graph.put(15, new ArrayList<>(Arrays.asList(14, 17)));
        graph.put(16, new ArrayList<>(Arrays.asList(14)));
        graph.put(17, new ArrayList<>(Arrays.asList(15)));
    }
}
