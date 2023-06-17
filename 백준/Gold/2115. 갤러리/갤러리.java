import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] maps;
    static int answer;
    static boolean[][] up;
    static boolean[][] down;
    static boolean[][] left;
    static boolean[][] right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new boolean[N][M];
        up = new boolean[N][M];
        down = new boolean[N][M];
        left = new boolean[N][M];
        right = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == 'X') {
                    maps[i][j] = false;
                } else {
                    maps[i][j] = true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!maps[i][j]) {
                    howManyPic(i, j);
                }
            }
        }
        System.out.println(answer);
    }

    public static void howManyPic(int y, int x) {
        int[][] dir = {{0, 1}, {1, 0}};
        for (int i = 0; i < 2; i++) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];
            if (isInAndX(dy, dx)) {
                if (i == 0) {
                    answer += countUpDownBlank(y, x, dy, dx);
                } else {
                    answer += countLeftRightBlank(y, x, dy, dx);
                }
            }
        }
    }

    private static int countLeftRightBlank(int y, int x, int dy, int dx) {  // X 세로
        int ret = 0;
        if (isInAndBlank(y, x - 1) && isInAndBlank(dy, dx - 1)) { // left
            if (!left[y][x - 1]
                    && !left[dy][dx - 1]) {
                left[y][x - 1] = true;
                left[dy][dx - 1] = true;
                ret++;
            }
        }
        if (isInAndBlank(y, x + 1) && isInAndBlank(dy, dx + 1)) { // right
            if (!right[y][x + 1]
                    && !right[dy][dx + 1]) {
                right[y][x + 1] = true;
                right[dy][dx + 1] = true;
                ret++;
            }
        }
        return ret;
    }

    public static int countUpDownBlank(int y, int x, int dy, int dx) {  // X 가로
        int ret = 0;
        if (isInAndBlank(y - 1, x) && isInAndBlank(dy - 1, dx)) { // Up
            if (!up[y - 1][x]
                    && !up[dy - 1][dx]) {
                up[y - 1][x] = true;
                up[dy - 1][dx] = true;
                ret++;
            }
        }
        if (isInAndBlank(y + 1, x) && isInAndBlank(dy + 1, dx)) { // down
            if (!down[y + 1][x]
                    && !down[dy + 1][dx]) {
                down[y + 1][x] = true;
                down[dy + 1][dx] = true;
                ret++;
            }
        }
        return ret;
    }

    public static boolean isInAndX(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && !maps[y][x];
    }

    public static boolean isInAndBlank(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && maps[y][x];
    }
}