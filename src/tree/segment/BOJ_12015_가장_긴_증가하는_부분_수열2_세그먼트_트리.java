package tree.segment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12015_가장_긴_증가하는_부분_수열2_세그먼트_트리 {

    public static final int MAX_NUM = 1_000_000;
    public static int n, offset;
    public static int[] tree, arr;

    public static int query(int node, int from, int to, int left, int right) {
        if (right < from || left > to) return 0;

        if (from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        return Math.max(query(node * 2, from, mid, left, right),
                query(node * 2 + 1, mid + 1, to, left, right));
    }

    public static int update(int node, int from, int to, int index, int value) {
        if (index < from || index > to) return 0;

        if (from == to) return tree[node] = value;

        int mid = (from + to) / 2;

        if (index > mid) {
            return tree[node] = Math.max(update(node * 2 + 1, mid + 1, to, index, value), tree[node * 2]);
        } else {
            return tree[node] = Math.max(tree[node * 2 + 1], update(node * 2, from, mid, index, value));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        offset = (1 << 20);
        tree = new int[(offset << 1) + 2];

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int result = 0;

        for (int i = 1; i <= n; i++) {
            max = query(1, 1, MAX_NUM, 1, arr[i] - 1) + 1;
            update(1, 1, MAX_NUM, arr[i], max);
            result = Math.max(result, max);
        }

        System.out.println(result);
    }

}
