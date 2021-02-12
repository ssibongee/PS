package tree.segment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14438_수열과_쿼리17_세그먼트_트리 {

    public static int n, m, offset;
    public static int[] tree, arr;

    public static int init(int node, int from, int to) {
        if (from == to) return tree[node] = arr[from];

        int mid = (from + to) / 2;

        return tree[node] = Math.min(init(node * 2, from, mid),
                init(node * 2 + 1, mid + 1, to));
    }

    public static int update(int node, int from, int to, int index, int value) {
        if (index < from || index > to) return tree[node];

        if (from == to) return tree[node] = value;

        int mid = (from + to) / 2;

        return tree[node] = Math.min(update(node * 2, from, mid, index, value),
                update(node * 2 + 1, mid + 1, to, index, value));
    }

    public static int query(int node, int from, int to, int left, int right) {
        if (from > right || to < left) return Integer.MAX_VALUE;

        if (from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        return Math.min(query(node * 2, from, mid, left, right),
                query(node * 2 + 1, mid + 1, to, left, right));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        offset = (1 << 17);
        tree = new int[(offset << 1) + 1];

        Arrays.fill(tree, Integer.MAX_VALUE);

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, n);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, 1, n, b, c);
            } else {
                sb.append(query(1, 1, n, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
