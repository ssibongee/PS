package graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1525_퍼즐 {

    public static final String TARGET = "123456780";
    public static String source = "";
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int idx;
    public static Map<String, Integer> visited = new HashMap<>();

    public static class Pair {
        String value;
        int index;

        public Pair(String value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(source, idx));
        visited.put(source, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                Pair pair = queue.poll();
                String curr = pair.value;
                int index = pair.index;
                int cost = visited.get(curr);

                int x = index / 3;
                int y = index % 3;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;
                    int nextIndex = nx * 3 + ny;
                    String newString = getNextString(curr, index, nextIndex);

                    if (visited.containsKey(newString)) continue;

                    visited.put(newString, cost + 1);
                    queue.add(new Pair(newString, nextIndex));
                }
            }
        }

        if(visited.containsKey(TARGET)) {
            return visited.get(TARGET);
        }

        return -1;
    }

    public static String getNextString(String str, int curr, int next) {
        char[] strings = str.toCharArray();
        char temp = strings[curr];
        strings[curr] = strings[next];
        strings[next] = temp;
        return String.valueOf(strings);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                source += st.nextToken();
            }
        }

        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == '0') {
                idx = i;
                break;
            }
        }


        System.out.println(bfs());
    }
}
