package learning.graph;

import java.util.*;

public class Traversal {
    public static void main(String[] args) {
        Traversal traversal = new Traversal();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        List<Integer> bfsTraversal = new ArrayList<>();
        traversal.bfs(graph, bfsTraversal);
        for (var i : bfsTraversal)
            System.out.print(i + " ");

        System.out.println("\n");

        List<Integer> dfsTraversal = new ArrayList<>();
        traversal.dfs(graph, dfsTraversal);
        for (var i : dfsTraversal)
            System.out.print(i + " ");

    }


    private void bfs(Map<Integer, List<Integer>> graph, List<Integer> bfsTraversal) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes + 1];
        int totalComponents = 0;
        for (var node : graph.keySet()) {
            if (!visited[node]) {
                totalComponents++;
                bfs(graph, visited, node, bfsTraversal);
            }
        }
        System.out.println("Total components in graph : '" + totalComponents + "'");
    }

    private void bfs(Map<Integer, List<Integer>> graph, boolean[] visited, Integer startingNode, List<Integer> bfsTraversal) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNode);
        visited[startingNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            bfsTraversal.add(node);
            for (var n : graph.get(node)) {
                if (!visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> dfsTraversal) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes + 1];
        int totalComponents = 0;
        for (var node : graph.keySet()) {
            if (!visited[node]) {
                totalComponents++;
                dfs(graph, visited, node, dfsTraversal);
            }
        }
        System.out.println("Total components in graph : '" + totalComponents + "'");
    }

    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, Integer node, List<Integer> dfsTraversal) {
        dfsTraversal.add(node);
        visited[node] = true;
        for (var n : graph.get(node)) {
            if (!visited[n]) {
                dfs(graph, visited, n, dfsTraversal);
            }
        }
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
