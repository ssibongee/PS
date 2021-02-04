package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11062_카드_게임 {

    public static int n;
    public static int[] cards;
    public static int[][][] dp;

    public static int dfs(int left, int right, int turn) {

        if (dp[turn][left][right] != -1) return dp[turn][left][right];

        if (left == right) {
            if (turn == 0) return cards[left];
            else return 0;
        }

        if (turn == 0) {
            dp[turn][left][right] = Math.max(dfs(left + 1, right, 1) + cards[left],
                    dfs(left, right - 1, 1) + cards[right]);
        } else {
            dp[turn][left][right] = Math.min(dfs(left + 1, right, 0),
                    dfs(left, right - 1, 0));
        }
        return dp[turn][left][right];
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());

            cards = new int[n];
            dp = new int[2][n][n];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(dfs(0, n - 1, 0)).append("\n");

        }
        System.out.println(sb);
    }
}
