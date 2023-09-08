import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int[][] maps;
    private static int M;
    private static int N;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maps = new int[M][N];
        for (int i = 0; i < M; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            maps[i] = temp;
        }
        dfs(0, 0, 0);
        System.out.println(answer);
    }

    public static void dfs(int y, int x, int depth) {
        if (depth == 2) {
            answer = Math.max(solution(), answer);
            return;
        }
        for (int i = y; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == y && j < x) {
                    continue;
                }
                if (maps[i][j] == 0) {
                    maps[i][j] = 1;
                    dfs(i, j, depth + 1);
                    maps[i][j] = 0;
                }
            }
        }
    }

    public static int solution() {
        boolean[][] visited = new boolean[M][N];
        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] == 2 && !visited[i][j]) {
                    count += getCount(visited, i, j);
                }
            }
        }
        return count;
    }

    private static int getCount(boolean[][] visited, int y, int x) {
        int count = 0;
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(y, x));
        boolean flag = false;
        while (!q.isEmpty()) {
            Position p = q.poll();
            if (visited[p.y][p.x]) {
                continue;
            }
            visited[p.y][p.x] = true;
            count++;
            for (int[] direction : directions) {
                Position moved = p.move(direction);
                if (isOverMaps(moved) || visited[moved.y][moved.x] || maps[moved.y][moved.x] == 1) {
                    continue;
                }
                if (maps[moved.y][moved.x] == 0) {
                    flag = true;
                }
                q.add(moved);
            }
        }
        if (flag) {
            return 0;
        }
        return count;
    }

    static boolean isOverMaps(Position p) {
        return 0 > p.y || p.y >= M || 0 > p.x || p.x >= N;
    }
}

class Position {
    int y;
    int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Position move(int[] direction) {
        return new Position(y + direction[0], x + direction[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

}

