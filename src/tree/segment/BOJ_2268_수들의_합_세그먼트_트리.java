package tree.segment;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2268_수들의_합_세그먼트_트리 {

    public static int n, m, offset;
    public static long[] tree;

    public static long update(int node, int from, int to, int index, int value) {

        if (index < from || index > to) return tree[node];

        if (from == to) return tree[node] = value;

        int mid = (from + to) / 2;

        return tree[node] = update(node * 2, from, mid, index, value) +
                update(node * 2 + 1, mid + 1, to, index, value);
    }

    public static long query(int node, int from, int to, int left, int right) {
        if(from > right || to < left) return 0;

        if(from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        return query(node * 2, from, mid, left, right) +
                query(node * 2 + 1, mid + 1, to, left, right);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        offset = (1 << 20);
        tree = new long[(offset << 1) + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0) {
                if(b > c) {
                    int temp = b;
                    b = c;
                    c = temp;
                }


                sb.append(query(1, 1, n, b, c)).append("\n");
            } else {
                update(1, 1, n, b, c);
            }
        }

        System.out.println(sb);
    }
}
