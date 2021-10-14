package learning.graph;

import java.util.*;

/**
 * Kosaraju's Algorithm
 */
public class StronglyConnectedComponents {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        List<List<Integer>> stronglyConnectedComponents = new LinkedList<>();

        System.out.println("Strongly Connected Components in given graph are as follows: ");
        for (var list : getStronglyConnectedComponents(graph)) {
            for (var node : list)
                System.out.print(String.format("%s ", node));
            System.out.println();
        }
    }

    private static List<List<Integer>> getStronglyConnectedComponents(Map<Integer, List<Integer>> graph) {
        List<List<Integer>> stronglyConnectedComponentsList = new LinkedList<>();
        int totalNodes = graph.size();
//        Step 1 : Find Topological Sort :
        Stack<Integer> topologicalSort = new Stack<>();
        boolean[] visited = new boolean[totalNodes + 1]; // +1 as it is 1 index/base graph

        for (var node : graph.keySet())
            if (!visited[node])
                dfs(graph, node, visited, topologicalSort);

//        Step 2 : Transpose the graph:
        Map<Integer, List<Integer>> transposeGraph = new HashMap<>();
        // Initialize the transposeGraph with required nodes as keys:
        for (var key : graph.keySet())
            transposeGraph.put(key, new ArrayList<>());

        for (var node1 : graph.keySet())
            for (var node2 : graph.get(node1))
                transposeGraph.get(node2).add(node1);

//          Step 3: run DFS according to finish time/topological sort

        //Resetting visited array
        Arrays.fill(visited, false);
        while (!topologicalSort.isEmpty()) {
            int node = topologicalSort.pop();
            if (!visited[node]) {
                List<Integer> stronglyConnectedComponents = new ArrayList<>();
                dfs(transposeGraph, node, visited, stronglyConnectedComponents);
                stronglyConnectedComponentsList.add(stronglyConnectedComponents);
            }
        }

        return stronglyConnectedComponentsList;
    }

    private static void initTransposeGraph(Map<Integer, List<Integer>> transposeGraph, Set<Integer> keys) {
        for (var key : keys)
            transposeGraph.put(key, new ArrayList<>());
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited, Stack<Integer> topologicalSort) {
        visited[node] = true;
        for (var n : graph.get(node))
            if (!visited[n])
                dfs(graph, n, visited, topologicalSort);

        topologicalSort.push(node);
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited, List<Integer> stronglyConnectedComponents) {
        visited[node] = true;
        stronglyConnectedComponents.add(node);
        for (var n : graph.get(node))
            if (!visited[n])
                dfs(graph, n, visited, stronglyConnectedComponents);
    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(1, new ArrayList<>(Arrays.asList(2)));
        graph.put(2, new ArrayList<>(Arrays.asList(3, 4)));
        graph.put(3, new ArrayList<>(Arrays.asList(1)));
        graph.put(4, new ArrayList<>(Arrays.asList(5)));
        graph.put(5, new ArrayList<>(Arrays.asList(6, 7)));
        graph.put(6, new ArrayList<>(Arrays.asList(7)));
        graph.put(7, new ArrayList<>(Arrays.asList(8)));
        graph.put(8, new ArrayList<>(Arrays.asList(5)));
        graph.put(9, new ArrayList<>(Arrays.asList(3)));
    }
}
