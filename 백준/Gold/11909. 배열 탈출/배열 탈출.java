import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Position.setMax(N);

        int[][] maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        int[][] moneyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(moneyMap[i], Integer.MAX_VALUE);
        }
        moneyMap[0][0] = 0;

        int[][] directions = {{0, 1}, {1, 0}};
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 0));
        while (!q.isEmpty()) {
            Position cur = q.poll();
            for (int[] direction : directions) {
                try {
                    Position moved = cur.move(direction);
                    int money = calcMoney(maps, cur, moved);
                    if (moneyMap[moved.y][moved.x] == Integer.MAX_VALUE) {
                        q.add(moved);
                    }
                    moneyMap[moved.y][moved.x] = Math.min(moneyMap[moved.y][moved.x], moneyMap[cur.y][cur.x] + money);
                } catch (IllegalStateException ignore) {
                }
            }
        }


        System.out.println(moneyMap[N - 1][N - 1]);
    }

    private static int calcMoney(int[][] maps, Position cur, Position next) {
        if (maps[next.y][next.x] < maps[cur.y][cur.x]) {
            return 0;
        }
        return maps[next.y][next.x] - maps[cur.y][cur.x] + 1;
    }

}

class Position {

    private static int max;
    int y;
    int x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public static void setMax(int max) {
        Position.max = max;
    }

    public Position move(int[] direction) {
        int newY = y + direction[0];
        int newX = x + direction[1];
        validation(newY, newX);
        return new Position(newY, newX);
    }

    private void validation(int newY, int newX) {
        boolean result = 0 <= newY && newY < max && 0 <= newX && newX < max;
        if (!result) {
            throw new IllegalStateException();
        }
    }

}
