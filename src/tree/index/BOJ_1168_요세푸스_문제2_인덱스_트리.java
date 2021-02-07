package tree.index;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ_1168_요세푸스_문제2_인덱스_트리 {

    public static int n, k, offset;
    public static int[] tree;

    public static void update(int index, int value) {
        index += offset;
        tree[index] += value;
        index /= 2;

        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index /= 2;
        }
    }

    public static int findKth(int kth) {
        int index = 1, left, right;

        while (index < offset) {
            left = index * 2;
            right = left + 1;

            if (tree[left] >= kth) {
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

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        offset = (1 << 17);

        tree = new int[(offset << 1) + 1];

        for (int i = 0; i <= n; i++) {
            tree[offset + i] = 1;
        }

        for (int i = offset - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }




        List<Integer> result = new ArrayList<>();
        int index = -1, size= n;
        for(int i = 0; i<n; i++) {
            index = (index + k) % size;
            int kth = findKth(index + 1);
            result.add(kth + 1);
            update(kth, -1);
            index--; size--;

        }



        System.out.println(result.stream().map(String::valueOf)
                .collect(Collectors.joining(", ", "<", ">")));

    }
}
