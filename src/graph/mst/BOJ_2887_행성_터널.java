package graph.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2887_행성_터널 {

    public static int n;
    public static int[] group;
    public static Pair[] planet;

    public static class Pair {
        int idx;
        int x, y, z;

        public Pair(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    public static class Edge implements Comparable<Edge> {
        int u, v;
        Long distance;

        public Edge(int u, int v, long distance) {
            this.u = u;
            this.v = v;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            return this.distance.compareTo(e.distance);
        }
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

    public static long getDistance(int x, int y) {
        return Math.abs(x - y);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        group = new int[n];
        Arrays.fill(group, -1);

        planet = new Pair[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planet[i] = new Pair(i, x, y, z);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.sort(planet, (p1, p2) -> p1.x - p2.x);
        for (int i = 0; i < n - 1 ; i++) {
            pq.add(new Edge(planet[i].idx, planet[i + 1].idx, getDistance(planet[i].x, planet[i + 1].x)));
        }

        Arrays.sort(planet, (p1, p2) -> p1.y - p2.y);
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Edge(planet[i].idx, planet[i + 1].idx, getDistance(planet[i].y, planet[i + 1].y)));
        }

        Arrays.sort(planet, (p1, p2) -> p1.z - p2.z);
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Edge(planet[i].idx, planet[i + 1].idx, getDistance(planet[i].z, planet[i + 1].z)));
        }

        int edge = 0;
        long cost = 0;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if (union(e.u, e.v)) {
                cost += e.distance;
                edge += 1;
            }

            if(edge == n - 1) break;
        }

        System.out.println(cost);

    }
}
