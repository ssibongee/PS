package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14002_가장_긴_증가하는_부분_수열4 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int max = 0, maxIndex= 0;
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] prev = new int[n];

        Arrays.fill(prev, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if(max < dp[i]) {
                max = dp[i];
                maxIndex = i;
            }
        }

        String result = "";

        System.out.println(max);
        for (int i = maxIndex; i != -1; i = prev[i]) {
            result = arr[i] + " " + result;
        }
        System.out.println(result);
    }
}
