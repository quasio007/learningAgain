package learning.graph;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        List<Integer> bfsTopologicalSort = new ArrayList<>();
        topologicalSort.bfsTopologicalSort(graph, bfsTopologicalSort);
        System.out.print("BFS Topological Sort : ");
        for (var node : bfsTopologicalSort)
            System.out.print(node + " ");


        System.out.println("\n");

        List<Integer> dfsTopologicalSort = new ArrayList<>();
        topologicalSort.dfsTopologicalSort(graph, dfsTopologicalSort);
        System.out.print("DFS Topological Sort : ");
        for (var node : dfsTopologicalSort)
            System.out.print(node + " ");
    }

    private void bfsTopologicalSort(Map<Integer, List<Integer>> graph, List<Integer> bfsTopologicalSort) {
        int totalNodes = graph.size();
        int[] inDegreeArr = new int[totalNodes];

//        Finding indegree for nodes
        for (var node : graph.keySet())
            for (var n : graph.get(node))
                inDegreeArr[n]++;

        Queue<Integer> queue = new LinkedList<>();
//            Adding nodes with in degree 0 to queue
        for (int i = 0; i < inDegreeArr.length; i++)
            if (inDegreeArr[i] == 0)
                queue.add(i);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            bfsTopologicalSort.add(node);
            for (var n : graph.get(node))
                if (inDegreeArr[n] > 0 && --inDegreeArr[n] == 0)
                    queue.add(n);
        }
    }

    private void dfsTopologicalSort(Map<Integer, List<Integer>> graph, List<Integer> dfsTopologicalSort) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes];
        Stack<Integer> stack = new Stack<>();

        for (var node : graph.keySet())
            if (!visited[node])
                dfs(graph, visited, node, stack);

        while (!stack.isEmpty())
            dfsTopologicalSort.add(stack.pop());
    }

    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, Integer node, Stack<Integer> stack) {
        visited[node] = true;

        for (var n : graph.get(node))
            if (!visited[n])
                dfs(graph, visited, n, stack);

        stack.push(node);

    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(0, new ArrayList<>(Arrays.asList()));
        graph.put(1, new ArrayList<>(Arrays.asList()));
        graph.put(2, new ArrayList<>(Arrays.asList(3)));
        graph.put(3, new ArrayList<>(Arrays.asList(1)));
        graph.put(4, new ArrayList<>(Arrays.asList(0, 1)));
        graph.put(5, new ArrayList<>(Arrays.asList(0, 2)));

        graph.put(6, new ArrayList<>(Arrays.asList(7)));
        graph.put(7, new ArrayList<>(Arrays.asList(8)));
        graph.put(8, new ArrayList<>(Arrays.asList()));
        graph.put(9, new ArrayList<>(Arrays.asList(6, 7)));
    }
}
