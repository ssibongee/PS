package graph.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1800_인터넷_설치 {

    public static int n, p, k;
    public static List<Edge>[] adj;
    public static int[] dist;

    public static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }

    public static int dijkstra(int fee) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.v;
            int cost = edge.w;

            if (cost != dist[node]) continue;

            for (Edge next : adj[node]) {
                int nextFee = dist[node] + (next.w > fee ? 1 : 0);
                if (dist[next.v] > nextFee) {
                    dist[next.v] = nextFee;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        return dist[n];
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int left = 0, right = 0;

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
            right = Math.max(right, w + 1);
        }

        while (left < right) {
            int mid = (left + right) / 2;

            if (k >= dijkstra(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(dist[n] == Integer.MAX_VALUE ? -1 : left);


    }
}
