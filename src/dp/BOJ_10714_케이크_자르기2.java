package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10714_케이크_자르기2 {

    public static int n;
    public static long[] cakes;
    public static long[][][] dp;


    public static long dfs(int turn, int left, int right) {

        long ret = dp[turn][left][right];

        if (turn == 0 && ret != -1) return ret;

        if ((right + 1) % n == left) return 0;


        if (turn == 0) {
            dp[turn][left][right] = Math.max(dfs(1, (left + n - 1) % n, right) + cakes[(left + n - 1) % n],
                    dfs(1, left, (right + 1) % n) + cakes[(right + 1) % n]);
        } else {
            if(cakes[(left + n - 1) % n] > cakes[(right + 1) %n]) {
                dp[turn][left][right] = dfs(0, (left + n - 1) % n, right);
            } else {
                dp[turn][left][right] = dfs(0, left, (right + 1) % n);
            }
        }

        return dp[turn][left][right];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        cakes = new long[n];
        dp = new long[2][n][n];

        for (int i = 0; i < n; i++) {
            cakes[i] = Long.parseLong(br.readLine());
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, cakes[i] + dfs(1, i, i));
        }

        System.out.println(max);

    }
}
