package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ_1158_요세푸스_문제 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new LinkedList<>(IntStream.range(1, n + 1).boxed().collect(Collectors.toList()));

        List<Integer> result = new ArrayList<>();
        int cnt = 0;
        while (!dq.isEmpty()) {
            if (cnt == k - 1) {
                cnt = 0;
                result.add(dq.pollFirst());
            } else {
                dq.addLast(dq.pollFirst());
                cnt++;
            }
        }

        System.out.println(result.stream().map(String::valueOf)
                .collect(Collectors.joining(", ", "<", ">")));
    }
}
