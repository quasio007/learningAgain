package learning.graph;

import java.util.*;

public class BipatiteDetection {
    public static void main(String[] args) {
        BipatiteDetection bipatiteDetection = new BipatiteDetection();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);

        System.out.println("Is the current graph biparite through bfs : " + bipatiteDetection.bfs(graph));

        System.out.println("Is the current graph biparite through dfs : " + bipatiteDetection.dfs(graph));
    }

    private boolean bfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
        int[] colourArr = new int[totalNodes + 1];
        for (var node : graph.keySet())
            if (colourArr[node] == 0)
                if (!bfs(graph, colourArr, node))
                    return false;
        return true;
    }

    private boolean bfs(Map<Integer, List<Integer>> graph, int[] colourArr, Integer startingNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNode);
        colourArr[startingNode] = 1;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (var n : graph.get(node)) {
                if (colourArr[n] == 0) {
                    queue.add(n);
                    colourArr[n] = 3 - colourArr[node];
                } else if (colourArr[n] == colourArr[node])
                    return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph) {
        int totalNodes = graph.size();
        int[] colourArr = new int[totalNodes + 1];
        for (var node : graph.keySet())
            if (colourArr[node] == 0)
                if (!dfs(graph, colourArr, node))
                    return false;
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int[] colourArr, Integer node) {
        if (colourArr[node] == 0)
            colourArr[node] = 1;
        for (var n : graph.get(node))
            if (colourArr[n] == 0) {
                colourArr[n] = 3 - colourArr[node];
                if (!dfs(graph, colourArr, n))
                    return false;
            } else if (colourArr[n] == colourArr[node])
                return false;
        return true;
    }

    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(1, new ArrayList<>(Arrays.asList(2)));
        graph.put(2, new ArrayList<>(Arrays.asList(1, 3, 8)));
        graph.put(3, new ArrayList<>(Arrays.asList(2, 4)));
        graph.put(4, new ArrayList<>(Arrays.asList(3, 5)));
        graph.put(5, new ArrayList<>(Arrays.asList(4, 6, 8)));
        graph.put(6, new ArrayList<>(Arrays.asList(5, 7)));
        graph.put(7, new ArrayList<>(Arrays.asList(6)));
        graph.put(8, new ArrayList<>(Arrays.asList(2, 5)));
    }
}
