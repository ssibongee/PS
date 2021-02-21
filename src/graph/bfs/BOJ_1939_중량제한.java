package graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939_중량제한 {

    public static int n, m, s, e;
    public static boolean[] visited;
    public static List<Pair>[] adj;

    public static class Pair {
        int v, w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static boolean bfs(long weight) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[n + 1];

        visited[s] = true;
        queue.add(s);

        while(!queue.isEmpty()) {
            int curr = queue.poll();

            for (Pair next : adj[curr]) {

                if(visited[next.v] || next.w < weight) continue;

                visited[next.v] = true;
                queue.add(next.v);
            }
        }

        return visited[e];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Pair(b, c));
            adj[b].add(new Pair(a, c));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        long start = 0, end = 1_000_000_001;

        while(start < end) {
            long mid = (start + end) / 2;

            if(bfs(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        System.out.println(start - 1);

    }
}
