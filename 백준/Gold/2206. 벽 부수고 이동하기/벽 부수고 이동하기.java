import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    int row;
    int col;
    int[][][] map;

    Main(int row, int col) {
        this.row = row;
        this.col = col;
        map = new int[2][row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        Main main = new Main(row, col);
        main.setMap(br);

        int shortestPath = main.dfs();
        System.out.print(shortestPath);
    }

    public void setMap(BufferedReader br) throws IOException {
        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                map[0][i][j] = line.charAt(j) - '0';
                map[1][i][j] = line.charAt(j) - '0';
            }
        }
    }

    public int dfs() {
        int[] moveY = {0, 0, -1, 1};
        int[] moveX = {1, -1, 0, 0};
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0, 0});
        while (!queue.isEmpty()) {
            Integer[] coordinate = queue.poll();
            int y = coordinate[0];
            int x = coordinate[1];
            int broke = coordinate[2];
            if (y == row - 1 && x == col - 1) {
                return map[broke][y][x] / 100 + 1;
            }
            for (int i = 0; i < 4; i++) {
                int dy = y + moveY[i];
                int dx = x + moveX[i];
                if (0 <= dy && dy < row && 0 <= dx && dx < col) {
                    if (map[broke][dy][dx] == 1 && broke == 0) {
                        map[1][dy][dx] = map[0][y][x] + 100;
                        queue.add(new Integer[]{dy, dx, 1});
                    } else if (map[broke][dy][dx] == 0) {
                        map[broke][dy][dx] = map[broke][y][x] + 100;
                        queue.add(new Integer[]{dy, dx, broke});
                    }
                }
            }
        }
        return -1;
    }
}

