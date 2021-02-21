package graph.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2186_문자판 {

    public static int n, m, k;
    public static int[][][] dp;
    public static char[][] map;
    public static char[] word;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int dfs(int index, int x, int y) {

        int ret = dp[x][y][index];

        if (ret != -1) return dp[x][y][index];

        if (index == word.length) return 1;

        dp[x][y][index] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j] * i;
                int ny = y + dy[j] * i;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || word[index] != map[nx][ny]) continue;
                dp[x][y][index] += dfs(index + 1, nx, ny);
            }
        }

        return dp[x][y][index];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n][m][81];
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        word = br.readLine().toCharArray();

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != word[0]) continue;
                ret += dfs(1, i, j);
            }
        }

        System.out.println(ret);

    }
}
