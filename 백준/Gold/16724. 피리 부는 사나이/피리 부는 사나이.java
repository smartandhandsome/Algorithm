import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static int[][] visited;
    static char[][] maps;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        // 방문한 곳 다시 방문 하면 +1
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new char[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                maps[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    if (check(i, j)) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int y, int x) {
        char c = maps[y][x];
        Direction d = Direction.valueOf(String.valueOf(c));

        int dy = y + d.y;
        int dx = x + d.x;

        if (visited[dy][dx] == 2) { // 이미 방문한 사이클
            
            return false;
        }

        if (visited[dy][dx] == 1) {  // 내가 방문하고 있는 곳을 또 방문, 새로운 사이클
            visited[dy][dx] = 2;
            return true;
        }

        if (visited[dy][dx] == 0) {
            visited[dy][dx] = 1;
            boolean check = check(dy, dx);
            visited[dy][dx] = 2;
            return check;
        }
        visited[dy][dx] = 2;
        return false;
    }
}

enum Direction {
    U(-1, 0), D(1, 0), L(0, -1), R(0, 1);

    public final int y;
    public final int x;

    Direction(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
