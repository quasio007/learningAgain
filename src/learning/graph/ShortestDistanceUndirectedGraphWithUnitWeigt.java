package learning.graph;

import java.util.*;

public class ShortestDistanceUndirectedGraphWithUnitWeigt {
    public static void main(String[] args) {
        ShortestDistanceUndirectedGraphWithUnitWeigt shortestDistanceUnDirectedGraph = new ShortestDistanceUndirectedGraphWithUnitWeigt();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        init(graph);
        int src = 0;

        int totalNodes = graph.size();
        int[] distanceArr = new int[totalNodes];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);

        shortestDistanceUnDirectedGraph.getShortestPathWhenWeightIsOneUnitPerEdge(graph, src, distanceArr);
        for (int i = 0; i < distanceArr.length; i++)
            System.out.println(String.format("Node '%s'   :  '%s' Distance", i, distanceArr[i]));

        System.out.println("\n");
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
    }

    private void getShortestPathWhenWeightIsOneUnitPerEdge(Map<Integer, List<Integer>> graph, int src, int[] distanceArr) {
        //        Using BFS algo - modified:
        Queue<Integer> queue = new LinkedList<>();

//        For startingNode :
        queue.add(src);
        distanceArr[src] = 0;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            int srcDistance = distanceArr[node];

            for (var n : graph.get(node))
                if (distanceArr[n] > srcDistance + 1) {
                    distanceArr[n] = srcDistance + 1;
                    queue.add(n);
                }
        }


    }


    private static void init(Map<Integer, List<Integer>> graph) {
        graph.put(0, new ArrayList<>(Arrays.asList(1, 3)));
        graph.put(1, new ArrayList<>(Arrays.asList(0, 2, 3)));
        graph.put(2, new ArrayList<>(Arrays.asList(1, 6)));
        graph.put(3, new ArrayList<>(Arrays.asList(0, 1, 4)));
        graph.put(4, new ArrayList<>(Arrays.asList(3, 5)));
        graph.put(5, new ArrayList<>(Arrays.asList(4, 6)));
        graph.put(6, new ArrayList<>(Arrays.asList(2, 5, 7, 8)));
        graph.put(7, new ArrayList<>(Arrays.asList(6, 8)));
        graph.put(8, new ArrayList<>(Arrays.asList(6, 7)));
    }
}
