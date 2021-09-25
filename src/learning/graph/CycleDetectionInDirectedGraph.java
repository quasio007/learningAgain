package learning.graph;

import java.util.*;

public class CycleDetectionInDirectedGraph {
    public static void main(String[] args) {
        CycleDetectionInDirectedGraph cycleDetectionInDirectedGraph = new CycleDetectionInDirectedGraph();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        System.out.println("Is the current graph circular through bfs : " + cycleDetectionInDirectedGraph.bfs(graph));

        System.out.println("Is the current graph circular through dfs : " + cycleDetectionInDirectedGraph.dfs(graph));
    }

    private boolean bfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
        int[] inDegreeArr = new int[totalNodes + 1];

//        Finding indegree for nodes
        for (var node : graph.keySet())
            for (var n : graph.get(node))
                inDegreeArr[n]++;

        Queue<Integer> queue = new LinkedList<>();
//            Adding nodes with in degree 0 to queue
        for (int i = 1; i < inDegreeArr.length; i++)
            if (inDegreeArr[i] == 0)
                queue.add(i);

        int nodeCount = 0;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            nodeCount++;
            for (var n : graph.get(node))
                if (inDegreeArr[n] > 0 && --inDegreeArr[n] == 0)
                    queue.add(n);
        }

        return !(nodeCount == totalNodes);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
//        Creating a visited array:
        boolean[] visited = new boolean[totalNodes + 1];
        boolean[] currVisited = new boolean[totalNodes + 1];

        for (var node : graph.keySet())
            if (!visited[node])
                if (dfs(graph, visited, currVisited, node, 0))
                    return true;


        return false;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] currVisited, int node, int parent) {
        visited[node] = true;
        currVisited[node] = true;

        for (var n : graph.get(node))
            if (!visited[n]) {
                if (dfs(graph, visited, currVisited, n, node))
                    return true;
            } else if (currVisited[n])
                return true;

        currVisited[node] = false;
        return false;
    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(1, new ArrayList<>(Arrays.asList(2)));
        graph.put(2, new ArrayList<>(Arrays.asList(3)));
        graph.put(3, new ArrayList<>(Arrays.asList(4, 6)));
        graph.put(4, new ArrayList<>(Arrays.asList(5)));
        graph.put(5, new ArrayList<>(Arrays.asList()));
        graph.put(6, new ArrayList<>(Arrays.asList(5)));
        graph.put(7, new ArrayList<>(Arrays.asList(2, 8)));
        graph.put(8, new ArrayList<>(Arrays.asList(9)));
        graph.put(9, new ArrayList<>(Arrays.asList(7)));
    }
}
