import java.util.*;
import java.io.*;

public class Main {
    static int[][] odd = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}};
    static int[][] even = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}};
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] maps = new int[H+2][W+2];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H+2][W+2];
        int[][] directions;
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] coor = q.poll();
            if (coor[0] % 2 == 0) {
                directions = even.clone();
            } else {
                directions = odd.clone();
            }
            for (int i = 0; i < 6; i++) {
                int newY = coor[0] + directions[i][0];
                int newX = coor[1] + directions[i][1];
                if (newY < 0 || newY >= H + 2 || newX < 0 || newX >= W + 2) {
                    continue;
                }
                if (maps[newY][newX] > 0) { // 1이면
                    maps[newY][newX] = -1;
                } else if (maps[newY][newX] < 0) {
                    maps[newY][newX] += -1;
                } else {
                    if (!visited[newY][newX]) {
                        q.offer(new int[] {newY, newX});
                        visited[newY][newX] = true;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i <= H + 1; i++) {
            for (int j = 0; j <= W + 1; j++) {
                // System.out.printf("%3d", maps[i][j]);
                if (maps[i][j] < 0) {
                    answer += maps[i][j];
                }
            }
            // System.out.println();
        }
        
        System.out.println(-answer);
    }
}