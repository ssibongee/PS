package tree.index;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2243_사탕상자_인덱스_트리 {

    public static int n, offset;
    public static int[] tree;

    public static void update(int index, int value) {
        index += offset;
        tree[index] += value;
        index /= 2;

        while(index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index /= 2;
        }
    }

    public static int findKth(int kth) {
        int index = 1, left, right;

        while(index < offset) {
            left = index * 2; right = left + 1;

            if(tree[left] >= kth) {
                index = left;
            } else {
                kth -= tree[left];
                index = right;
            }
        }

        return index - offset;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        offset = (1 << 20);

        tree = new int[(offset << 1) + 1];

        int a, b, c;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(a == 1) {
                int kth = findKth(b);
                sb.append(kth).append("\n");
                update(kth, -1);
            } else {
                c = Integer.parseInt(st.nextToken());
                update(b, c);
            }
        }

        System.out.println(sb);
    }
}
