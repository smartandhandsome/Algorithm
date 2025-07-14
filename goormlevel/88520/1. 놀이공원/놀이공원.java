import java.io.*;
import java.util.*;

class Main {

    private static final int[][] directions = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int K = Integer.parseInt(split[1]);

            boolean[][] map = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int check = Integer.parseInt(s[j]);
                    map[i][j] = check == 1;
                }
            }

            boolean[][] visited = new boolean[N - K + 1][N - K + 1];

            Queue<Candi> q = new ArrayDeque<>();
            int count = 0;
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    if (map[i][j]) {
                        count++;
                    }
                }
            }
            int y = 0;
            int x = 0;
            q.offer(new Candi(count, y, x));
            visited[0][0] = true;

            int answer = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                Candi c = q.poll();
                answer = Math.min(answer, c.count);
                for (int[] direction : directions) {
                    int dcount = c.count;
                    int dy = c.y + direction[0];
                    int dx = c.x + direction[1];

                    if (dy < 0 || dy >= N - K + 1 || dx < 0 || dx >= N - K + 1 || visited[dy][dx]) {
                        continue;
                    }
                    for (int i = 0; i < K; i++) {
                        int removedY = c.y + direction[1] * i;
                        int removedX = c.x + direction[0] * i;

                        int addedY = c.y + K * direction[0] + direction[1] * i;
                        int addedX = c.x + K * direction[1] + direction[0] * i;

                        if (map[removedY][removedX]) {
                            dcount--;
                        }
                        if (map[addedY][addedX]) {
                            dcount++;
                        }
                    }
                    visited[dy][dx] = true;
                    q.offer(new Candi(dcount, dy, dx));
                }
            }
            System.out.println(answer);
        }
    }

    static class Candi {
        int count;
        int y;
        int x;

        public Candi(int count, int y, int x) {
            this.count = count;
            this.y = y;
            this.x = x;
        }
    }
}
