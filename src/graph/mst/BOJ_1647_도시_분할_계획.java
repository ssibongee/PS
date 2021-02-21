package graph.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시_분할_계획 {

    public static int n, m;
    public static int[] group;
    public static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }

    public static int find(int v) {
        if(group[v] < 0) return v;
        return group[v] = find(group[v]);
    }

    public static boolean union(int u, int v) {
        u = find(u); v = find(v);

        if(u == v) return false;
        group[v] = u;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        group = new int[n + 1];
        Arrays.fill(group, -1);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }

        int edge = 0, cost = 0;

        while(!pq.isEmpty()) {
            Edge node = pq.poll();

            if(union(node.u, node.v)) {
                cost += node.w;
                edge += 1;
            }

            if(edge == n - 2) break;
        }

        System.out.println(cost);
    }
}
