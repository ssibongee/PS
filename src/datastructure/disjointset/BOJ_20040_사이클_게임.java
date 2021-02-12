package datastructure.disjointset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20040_사이클_게임 {

    public static int n, m;
    public static int[] parent;

    public static int find(int v) {
        if (parent[v] < 0) return v;
        return parent[v] = find(parent[v]);
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return false;
        parent[v] = u;

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        Arrays.fill(parent, -1);

        boolean cycle = false;
        int index = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(union(u, v)) continue;

            if(!cycle) {
                cycle = true;
                index = i;
            }
        }

        System.out.println(index);
    }
}
