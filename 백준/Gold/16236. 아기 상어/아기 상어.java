import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static final int[] moveRow = {-1, 0, 0, 1};
    static final int[] moveCol = {0, -1, 1, 0};

    static class Shark {
        int col;
        int row;
        int size;
        int eaten;
        int totalMove;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
            this.eaten = 0;
            totalMove = 0;
        }

        public int countMove() {
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) ->
                    o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : (o1[1] != o2[1] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[2], o2[2]))
            );
            boolean[][] visited = new boolean[N][N];

            q.add(new int[]{0, this.row, this.col});
            while (!q.isEmpty()) {
                int[] temp = q.poll();
                int distance = temp[0];
                int row = temp[1];
                int col = temp[2];
                if (0 < map[row][col] && map[row][col] < this.size) {
                    eat(row, col);
                    return distance;
                }
                for (int i = 0; i < 4; i++) {
                    int newRow = row + moveRow[i];
                    int newCol = col + moveCol[i];
                    if (0 <= newRow && newRow < N && 0 <= newCol && newCol < N && !visited[newRow][newCol]) {
                        if (map[newRow][newCol] <= this.size) {
                            visited[newRow][newCol] = true;
                            q.add(new int[]{distance + 1, newRow, newCol});
                        }
                    }
                }
            }
            return -1;
        }

        public void eat(int row, int col) {
            this.eaten++;
            map[row][col] = 0;
            this.row = row;
            this.col = col;
            if (eaten == size) {
                size++;
                eaten = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        Shark s = null;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    s = new Shark(i, j);
                    num = 0;
                }
                map[i][j] = num;
            }
        }
        while (true) {
            int cnt = s.countMove();
            if (cnt == -1) {
                break;
            }
            answer += cnt;
        }

        System.out.print(answer);
    }
}