package graph.floydwarshall;

import java.util.Arrays;

public class PRO_LV3_합승_택시_요금 {

    public int[][] dist;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < fares.length; i++) {
            int u = fares[i][0];
            int v = fares[i][1];
            int w = fares[i][2];
            dist[u][v] = dist[v][u] = w;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            answer = Math.min(dist[s][i] + dist[i][a] + dist[i][b], answer);
        }

        return answer;
    }

}
