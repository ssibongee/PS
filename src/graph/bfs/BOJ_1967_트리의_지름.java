package graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1967_트리의_지름 {

    public static List<Pair>[] adj;
    public static int n, node;
    public static int[] dist;

    public static class Pair {
        int v, w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        int max = 0;

        Arrays.fill(dist, -1);
        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Pair next : adj[curr]) {
                if (dist[next.v] != -1) continue;
                dist[next.v] = dist[curr] + next.w;
                if (dist[next.v] > max) {
                    max = dist[next.v];
                    node = next.v;
                }
                queue.add(next.v);
            }
        }

        return dist[node];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        adj = new List[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Pair(v, w));
            adj[v].add(new Pair(u, w));
        }

        bfs(1);
        if(node == 0) System.out.println(0);
        else System.out.println(bfs(node));
    }
}
