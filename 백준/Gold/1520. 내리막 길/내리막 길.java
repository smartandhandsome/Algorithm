import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dp;
    static int[] moveRow = {0, 0, -1, 1};
    static int[] moveCol = {-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
//                dp[i][j] = -1;
            }
        }
//        System.out.print(dfs(0, 0));
        System.out.print(bfs());
    }

//    public static int dfs(int row, int col) {
//        if (row == N - 1 && col == M - 1) {
//            return 1;
//        }
//        if (dp[row][col] != -1) {
//            return dp[row][col];
//        }
//        dp[row][col] = 0;
//        for (int i = 0; i < 4; i++) {
//            int movedRow = row + moveRow[i];
//            int movedCol = col + moveCol[i];
//            if (0 <= movedRow && movedRow < N && 0 <= movedCol && movedCol < M &&
//                    map[movedRow][movedCol] < map[row][col]) {
//                dp[row][col] += dfs(movedRow, movedCol);
//            }
//        }
//        return dp[row][col];
//    }

    public static int bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return map[o2[0]][o2[1]] - map[o1[0]][o1[1]];
            }
        });
        q.add(new int[]{0, 0});
        dp[0][0] = 1;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int movedRow = temp[0] + moveRow[i];
                int movedCol = temp[1] + moveCol[i];
                if (0 <= movedRow && movedRow < N && 0 <= movedCol && movedCol < M &&
                        map[movedRow][movedCol] < map[temp[0]][temp[1]]) {
                    if (dp[movedRow][movedCol] == 0) {
                        q.add(new int[]{movedRow, movedCol});
                    }
                    dp[movedRow][movedCol] += dp[temp[0]][temp[1]];
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
