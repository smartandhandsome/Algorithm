import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] box = new int[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                box[i][j] = num;
                if (num == 1) {
                    pq.add(new int[]{num, i, j});
                }
            }
        }

        int[] moveRow = {0, 0, 1, -1};
        int[] moveCol = {1, -1, 0, 0};
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int day = temp[0];
            int row = temp[1];
            int col = temp[2];
            for (int i = 0; i < 4; i++) {
                int movedRow = row + moveRow[i];
                int movedCol = col + moveCol[i];
                if (0 <= movedRow && movedRow < N && 0 <= movedCol && movedCol < M && box[movedRow][movedCol] == 0) {
                    box[movedRow][movedCol] = box[row][col] + 1;
                    pq.add(new int[]{box[movedRow][movedCol], movedRow, movedCol});
                }
            }
        }
        boolean flag = false;
        int totalDay = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    flag = true;
                    break;
                }
                totalDay = Math.max(box[i][j], totalDay);
            }
        }
        totalDay = flag ? -1 : totalDay - 1;
        System.out.print(totalDay);
    }
}