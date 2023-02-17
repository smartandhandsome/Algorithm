import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[][][] boxes = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int num = Integer.parseInt(st.nextToken());
                    boxes[i][j][k] = num;
                    if (num == 1) {
                        q.add(new int[]{num, i, j, k});
                    }
                }
            }
        }
        int[] moveDep = {-1, 1, 0, 0, 0, 0};
        int[] moveRow = {0, 0, -1, 1, 0, 0};
        int[] moveCol = {0, 0, 0, 0, -1, 1};
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int day = temp[0];
            int dep = temp[1], row = temp[2], col = temp[3];
            for (int i = 0; i < 6; i++) {
                int movedDep = dep + moveDep[i];
                int movedRow = row + moveRow[i];
                int movedCol = col + moveCol[i];
                if (0 <= movedDep && movedDep < H && 0 <= movedRow && movedRow < N && 0 <= movedCol && movedCol < M && boxes[movedDep][movedRow][movedCol] == 0) {
                    boxes[movedDep][movedRow][movedCol] = boxes[dep][row][col] + 1;
                    q.add(new int[]{boxes[movedDep][movedRow][movedCol], movedDep, movedRow, movedCol});
                }
            }
        }

        boolean flag = false;
        int totalDay = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (boxes[i][j][k] == 0) {
                        flag = true;
                    }
                    totalDay = Math.max(totalDay, boxes[i][j][k]);
                }
            }
        }
        totalDay = flag ? -1 : totalDay - 1;
        System.out.print(totalDay);
    }
}