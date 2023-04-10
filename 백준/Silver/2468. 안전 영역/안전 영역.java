import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        int MIN = 101;
        int MAX = -1;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                MIN = Math.min(MIN, maps[i][j]);
                MAX = Math.max(MAX, maps[i][j]);
            }
        }
//        for (int[] map : maps) {
//            System.out.println(Arrays.toString(map));
//        }
        int answer = 1;
        for (int t = MIN; t <= MAX; t++) {
            boolean[][] visited = new boolean[N][N];
            int temp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (maps[i][j] > t && !visited[i][j]) {
                        bfs(i, j, t, visited);
                        temp++;
                    }
                }
            }
            answer = Math.max(temp, answer);
        }
        System.out.println(answer);
    }

    public static void bfs(int y, int x, int threshold, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        q.add(new int[]{y, x});
        visited[y][x] = true;
        while (!q.isEmpty()) {
            int[] coor = q.poll();
            for (int[] move : direction) {
                int newY = coor[0] + move[0];
                int newX = coor[1] + move[1];
                if (0 <= newY && newY < N && 0 <= newX && newX < N) {
                    if (threshold < maps[newY][newX] && !visited[newY][newX]) {
                        visited[newY][newX] = true;
                        q.add(new int[]{newY, newX});
                    }
                }
            }
        }
    }
}
