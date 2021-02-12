package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] wait = new long[n];

        long max = 0;
        for (int i = 0; i < n; i++) {
            wait[i] = Long.parseLong(br.readLine());
            max = Math.max(max, wait[i]);
        }

        long start = 0, end = max * m + 1;

        while (start < end) {
            long mid = (start + end) / 2;
            long pass = 0;
            for (int i = 0; i < n; i++) {
                pass += mid / wait[i];
            }

            if (pass >= m) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);

    }
}
