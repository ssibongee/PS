package tree.segment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11505_구간_곱_구하기_세그먼트_트리 {

    public static final int MOD = 1_000_000_007;
    public static int n, m, k, offset;
    public static long[] tree, arr;

    public static long init(int node, int from, int to) {
        if (from == to) return tree[node] = arr[from];

        int mid = (from + to) / 2;
        return tree[node] = (init(node * 2, from, mid) * init(node * 2 + 1, mid + 1, to)) % MOD;
    }

    public static long query(int node, int from, int to, int left, int right) {
        if (from > right || to < left) return 1;

        if (from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        return (query(node * 2, from, mid, left, right) * query(node * 2 + 1, mid + 1, to, left, right)) % MOD;
    }

    public static long update(int node, int from, int to, int index, int value) {
        if (index < from || index > to) return tree[node];

        if (from == to) return tree[node] = value;

        int mid = (from + to) / 2;

        return tree[node] = (update(node * 2, from, mid, index, value) * update(node * 2 + 1, mid + 1, to, index, value)) % MOD;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        offset = (1 << 20);
        tree = new long[(offset << 1) + 1];
        Arrays.fill(tree, 1);

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) {
                update(1, 1, n, b, c);
            } else {
                sb.append(query(1, 1, n, b, c)).append("\n");
            }
        }

        System.out.println(sb);

    }
}
