import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int[] size;
    private static final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static Queue<int[]> cur = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        map = new int[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < size[1]; j++) {
                map[i][j] = Integer.parseInt(s[j]);
                if (map[i][j] != 0) {
                    cur.offer(new int[]{i, j});
                }
            }
        }

        int answer = 0;
        for (int count = 1; ; count++) {
            cur = turn(cur);
            if (cur.isEmpty()) {
                break;
            }

            if (broken(cur.peek())) {
                answer = count;
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean broken(int[] coor) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(coor);

        boolean[][] isVisited = new boolean[size[0]][size[1]];
        isVisited[coor[0]][coor[1]] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];

            for (int[] direction : directions) {
                int dy = y + direction[0];
                int dx = x + direction[1];

                if (in(dy, dx) && !isVisited[dy][dx] && map[dy][dx] != 0) {
                    q.offer(new int[]{dy, dx});
                    isVisited[dy][dx] = true;
                    count++;
                }
            }
        }

        return count != cur.size();
    }

    private static Queue<int[]> turn(Queue<int[]> cur) {
        Queue<int[]> next = new ArrayDeque<>();
        int[][] temp = new int[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++) {
            temp[i] = Arrays.copyOf(map[i], size[1]);
        }

        while (!cur.isEmpty()) {
            int[] poll = cur.poll();
            int y = poll[0];
            int x = poll[1];

            int count = 0;
            for (int[] direction : directions) {
                int dy = y + direction[0];
                int dx = x + direction[1];

                if (in(dy, dx) && temp[dy][dx] == 0) {
                    count++;
                }
            }

            int num = temp[y][x] - count;
            if (num > 0) {
                next.offer(new int[]{y, x});
            }
            map[y][x] = Math.max(num, 0);
        }
        return next;
    }

    private static boolean in(int y, int x) {
        return 0 <= y && y < size[0] && 0 <= x && x < size[1];
    }
}