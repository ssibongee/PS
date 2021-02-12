package tree.segment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7578_공장_세그먼트_트리 {

    public static int n, offset;
    public static int[] tree, from, to;

    public static long query(int node, int from, int to, int left, int right) {
        if (from > right || to < left) return 0;

        if (from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        return query(node * 2, from, mid, left, right) +
                query(node * 2 + 1, mid + 1, to, left, right);
    }

    public static int update(int node, int from, int to, int index, int value) {
        if (index < from || index > to) return tree[node];

        if (from == to) return tree[node] += value;

        int mid = (from + to) / 2;

        return tree[node] = update(node * 2, from, mid, index, value) +
                update(node * 2 + 1, mid + 1, to, index, value);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        offset = (1 << 20);
        tree = new int[(offset << 1) + 1];

        n = Integer.parseInt(br.readLine());

        from = new int[1000001];
        to = new int[1000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            from[Integer.parseInt(st.nextToken())] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            to[i] = from[Integer.parseInt(st.nextToken())];
        }

        long result = 0;

        for (int i = 1; i <= n; i++) {
            result += query(1, 1, n, to[i] + 1, n);
            update(1, 1, n, to[i], 1);
        }

        System.out.println(result);
    }
}
