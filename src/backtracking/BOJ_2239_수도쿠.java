package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2239_수도쿠 {

    public static char[][] map = new char[9][9];
    public static int n;
    public static List<Pair> position = new ArrayList<>();
    public static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static boolean find = false;
    public static StringBuilder sb = new StringBuilder();

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dfs(int index) {
        if(find) return;
        if(index == position.size()) {
            find = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            return;
        }

        Pair curr = position.get(index);
        for (int i = 1; i <= 9; i++) {
            char value = (char) (i + '0');
            if (isValidCol(curr.y, value) && isValidRow(curr.x, value)
                    && isValidBox(curr.x, curr.y, value)) {
                map[curr.x][curr.y] = value;
                dfs(index + 1);
                map[curr.x][curr.y] = '0';
            }
        }
    }

    public static boolean isValidRow(int row, char n) {
        for (int i = 0; i < 9; i++) {
            if(map[row][i] == n) return false;
        }
        return true;
    }

    public static boolean isValidCol(int col, char n) {
        for (int i = 0; i < 9; i++) {
            if(map[i][col] == n) return false;
        }
        return true;
    }

    public static boolean isValidBox(int x, int y, char n) {
        int nx = 3 * (x / 3) + 1;
        int ny = 3 * (y / 3) + 1;

        if(map[nx][ny] == n) return false;

        for (int i = 0; i < 8; i++) {
            int nnx = nx + dx[i];
            int nny = ny + dy[i];
            if(map[nnx][nny] == n) return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            map[i]  = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                if(map[i][j] == '0') {
                    position.add(new Pair(i, j));
                }
            }
        }

        dfs(0);
    }
}
