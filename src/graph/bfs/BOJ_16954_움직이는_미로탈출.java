package graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16954_움직이는_미로탈출 {

    public static char[][] map = new char[8][8];
    public static int sx = 7, sy = 0, ex = 0, ey = 7;
    public static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static int[][] visited = new int[8][8];
    public static Queue<Pair> queue = new LinkedList<>();
    public static boolean find;

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void wallMove() {
        boolean empty = true;
        for (int i = 7; i > 0; i--) {
            Arrays.fill(map[i], '.');
            for (int j = 0; j < 8; j++) {
                if(map[i-1][j] == '#') empty = false;
                map[i][j] = map[i - 1][j];
            }
        }
        Arrays.fill(map[0], '.');
        if(empty) find = true;
    }

    public static boolean move() {
        int size = queue.size();
        boolean move = false;
        for (int k = 0; k < size; k++) {
            Pair p = queue.poll();

            if (map[p.x][p.y] == '#') continue;

            if(p.x == 0 && p.y == 7) {
                find = true;
                return false;
            }

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || map[nx][ny] == '#' || visited[nx][ny] == visited[p.x][p.y] + 1) continue;

                if(!move) move = true;
                visited[nx][ny] = visited[p.x][p.y] + 1;
                queue.add(new Pair(nx, ny));
            }

            queue.add(p);
        }

        if(!move) return false;

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        queue.add(new Pair(sx, sy));
        for (int i = 0; i < 8; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[sx][sy] = 0;

        while(true) {

            if(!move() || find) break;

            wallMove();

        }

        System.out.println(find ? 1 : 0);

    }
}
