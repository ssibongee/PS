package tree.index;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_구간_합_구하기_인덱스_트리 {

    public static int n, m, k, offset;
    public static long[] tree;

    public static long query(int from, int to) {
        long result = 0;
        from += offset;
        to += offset;

        while (from <= to) {
            if (from % 2 == 1) {
                result += tree[from++];
            }
            if (to % 2 == 0) {
                result += tree[to--];
            }
            from /= 2;
            to /= 2;
        }

        return result;
    }

    public static void update(int index, long value) {
        index += offset;

        tree[index] = value;

        index /= 2;

        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index /= 2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (offset = 1; offset <= n + 1; offset *= 2) ;

        tree = new long[(offset << 1) + 1];

        for (int i = 1; i <= n; i++) {
            tree[offset + i] = Integer.parseInt(br.readLine());
        }

        for (int i = offset - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }


        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                update((int) b, c);
            } else {
                sb.append(query((int) b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);


    }
}
