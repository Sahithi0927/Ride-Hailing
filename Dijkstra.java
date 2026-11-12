package algorithms;

import java.util.*;

/**
 * Dijkstra's shortest path algorithm for Ride-Hailing Shortest Route.
 */
public class Dijkstra {
    public static class Result {
        public int[] prev;
        public int[] dist;
        public List<Integer> path;
        public int totalDist;
        public Result(int[] prev, int[] dist, List<Integer> path, int totalDist) {
            this.prev = prev;
            this.dist = dist;
            this.path = path;
            this.totalDist = totalDist;
        }
    }

    public static Result dijkstra(int n, List<List<int[]>> adj, int src, int dest) {
        int[] dist = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];
            if (d > dist[u]) continue;
            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int crawl = dest;
        if (dist[dest] == Integer.MAX_VALUE) return new Result(prev, dist, path, -1);
        while (crawl != -1) {
            path.add(0, crawl);
            crawl = prev[crawl];
        }
        return new Result(prev, dist, path, dist[dest]);
    }
}
