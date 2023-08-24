import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static Coordinate[] directions = new Coordinate[]{
            new Coordinate(-2, -1),
            new Coordinate(-2, 1),
            new Coordinate(-1, -2),
            new Coordinate(-1, 2),

            new Coordinate(2, -1),
            new Coordinate(2, 1),
            new Coordinate(1, -2),
            new Coordinate(1, 2)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int M = Integer.parseInt(s[0]);
        int[][] maps = new int[M][M];
        int N = Integer.parseInt(s[1]);
        int[] count = new int[N + 1];

        int[] k = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Coordinate knight = new Coordinate(k[0] - 1, k[1] - 1);
        for (int i = 0; i < N; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            maps[temp[0] - 1][temp[1] - 1] = i + 1;
        }

        Queue<Coordinate> q = new LinkedList<>();
        q.add(knight);
        while (!q.isEmpty()) {
            Coordinate co = q.poll();
            if (maps[co.getY()][co.getX()] > 0) {
                count[maps[co.getY()][co.getX()]] = co.getCount();
                boolean flag = false;
                for (int i = 1; i < count.length; i++) {
                    if (count[i] == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (maps[co.getY()][co.getX()] == -1) {
                continue;
            }
            maps[co.getY()][co.getX()] = -1;

            for (Coordinate direction : directions) {
                try {
                    Coordinate moved = co.move(direction, M);
                    if (maps[moved.getY()][moved.getX()] == -1) {
                        continue;
                    }
                    q.add(moved);
                } catch (IllegalStateException ignored) {
                }
            }
        }
        for (int i = 1; i < count.length; i++) {
            System.out.print(count[i] + " ");
        }
    }
}


class Coordinate {
    private final int y;
    private final int x;
    private int count = 0;


    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Coordinate(int[] yx) {
        this.y = yx[0] - 1;
        this.x = yx[1] - 1;
    }

    public Coordinate(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getCount() {
        return count;
    }

    public Coordinate move(Coordinate l, int max) {
        int newY = this.y + l.y;
        int newX = this.x + l.x;
        if (0 > newY || newY >= max || 0 > newX || newX >= max)
            throw new IllegalStateException();
        return new Coordinate(newY, newX, this.count + 1);
    }
}

