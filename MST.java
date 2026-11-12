package algorithms;

import java.util.*;

/**
 * Minimum Spanning Tree algorithms: Prim's and Kruskal's for Network Design.
 */
public class MST {
    public static class Edge {
        public int src, dest, weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static class Result {
        public List<Edge> edges;
        public int totalCost;
        public Result(List<Edge> edges, int totalCost) {
            this.edges = edges;
            this.totalCost = totalCost;
        }
    }

    // Prim's algorithm
    public static Result prim(int n, List<Edge> edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.src).add(e);
            adj.get(e.dest).add(new Edge(e.dest, e.src, e.weight));
        }
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        List<Edge> mst = new ArrayList<>();
        int total = 0;
        visited[0] = true;
        pq.addAll(adj.get(0));
        while (!pq.isEmpty() && mst.size() < n - 1) {
            Edge e = pq.poll();
            if (visited[e.dest]) continue;
            visited[e.dest] = true;
            mst.add(e);
            total += e.weight;
            for (Edge next : adj.get(e.dest)) {
                if (!visited[next.dest]) pq.add(next);
            }
        }
        if (mst.size() != n - 1) return new Result(new ArrayList<>(), -1); // Disconnected
        return new Result(mst, total);
    }

    // Kruskal's algorithm
    public static Result kruskal(int n, List<Edge> edges) {
        edges = new ArrayList<>(edges);
        edges.sort(Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        List<Edge> mst = new ArrayList<>();
        int total = 0;
        for (Edge e : edges) {
            int u = find(parent, e.src);
            int v = find(parent, e.dest);
            if (u != v) {
                mst.add(e);
                total += e.weight;
                parent[u] = v;
            }
        }
        if (mst.size() != n - 1) return new Result(new ArrayList<>(), -1); // Disconnected
        return new Result(mst, total);
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}
