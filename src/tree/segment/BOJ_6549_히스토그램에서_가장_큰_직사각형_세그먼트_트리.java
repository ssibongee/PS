package tree.segment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6549_히스토그램에서_가장_큰_직사각형_세그먼트_트리 {

    public static int n, offset;
    public static int[] tree;
    public static long[] height;

    public static int init(int node, int from, int to) {
        if(from == to) return tree[node] = from;

        int mid = (from + to) / 2;

        int leftIndex = init(node * 2, from, mid);
        int rightIndex = init(node * 2 + 1, mid + 1, to);

        return tree[node] = height[leftIndex] > height[rightIndex] ? rightIndex : leftIndex;
    }

    public static int query(int node, int from, int to, int left, int right) {
        if(from > right || to < left) return Integer.MAX_VALUE;

        if(from >= left && to <= right) return tree[node];

        int mid = (from + to) / 2;

        int leftIndex = query(node * 2, from, mid, left, right);
        int rightIndex = query(node * 2 + 1, mid + 1, to, left, right);

        if(leftIndex == Integer.MAX_VALUE) return rightIndex;
        else if(rightIndex == Integer.MAX_VALUE) return leftIndex;
        else return height[leftIndex] > height[rightIndex] ? rightIndex : leftIndex;
    }

    public static long findMaxWidth(int left, int right) {
        int index = query(1, 1, n, left, right);
        long size = (right - left + 1) * height[index];

        if(index - 1 >= left) {
            size = Math.max(size, findMaxWidth(left, index - 1));
        }

        if(index + 1 <= right) {
            size = Math.max(size, findMaxWidth(index + 1, right));
        }

        return size;
    }




    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        offset = (1 << 20);
        tree = new int[(offset << 1) + 1];


        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            Arrays.fill(tree, 0);


            height = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            init(1, 1, n);
            sb.append(findMaxWidth(1, n)).append("\n");
        }

        System.out.println(sb);

    }
}
