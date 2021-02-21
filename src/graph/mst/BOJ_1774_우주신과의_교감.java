package graph.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1774_우주신과의_교감 {

    public static int n, m;
    public static int[] group;
    public static double[][] distance;

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double getDistance(Pair a, Pair b) {
        return Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }

    public static int find(int v) {
        if (group[v] < 0) return v;
        return group[v] = find(group[v]);
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return false;
        group[v] = u;
        return true;
    }

    public static class Edge implements Comparable<Edge> {
        int x, y;
        Double length;

        public Edge(int x, int y, double length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }

        @Override
        public int compareTo(Edge e) {
            return this.length.compareTo(e.length);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        group = new int[n + 1];
        distance = new double[n + 1][n + 1];

        Arrays.fill(group, -1);

        Pair[] points = new Pair[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Pair(x, y);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double length = getDistance(points[i], points[j]);
                pq.add(new Edge(i, j, length));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        double cost = 0;
        int edge = m;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if (union(e.x, e.y)) {
                cost += e.length;
                edge += 1;
            }

            if(edge == n - 1) break;
        }

        System.out.printf("%.2f", cost);
    }
}
