import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static int[] size;
    private static int[][] cattle;
    private static boolean[][] zero;

    public static void main(String[] args) throws IOException {
        size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        zero = new boolean[size[0]][size[1]];
        cattle = makeCattle();

        Queue<int[]> q = firstWave();
        int count = q.isEmpty() ? 0 : 1;
        while (true) {
            q = wave(q);
            if (q.isEmpty()) {
                break;
            }
            count++;
        }
        System.out.println(count);
    }

    private static Queue<int[]> wave(Queue<int[]> q) {
        Queue<int[]> newQ = new LinkedList<>();
        while (!q.isEmpty()) {
            int[] brokenPoint = q.poll();
            cattle[brokenPoint[0]][brokenPoint[1]] = 0;
            for (int[] direction : directions) {
                int y = brokenPoint[0] + direction[0];
                int x = brokenPoint[1] + direction[1];
                if (!zero[y][x] && isBroken(y, x)) {
                    newQ.add(new int[]{y, x});
                    zero[y][x] = true;
                }
            }
        }
        return newQ;
    }

    private static Queue<int[]> firstWave() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i < size[0] - 1; i++) {
            for (int j = 1; j < size[1] - 1; j++) {
                if (!zero[i][j] && isBroken(i, j)) {
                    q.add(new int[]{i, j});
                    zero[i][j] = true;
                }
            }
        }
        return q;
    }

    private static boolean isBroken(int y, int x) {
        int count = 0;
        for (int[] direction : directions) {
            int checkY = y + direction[0];
            int checkX = x + direction[1];
            if (cattle[checkY][checkX] == 0) {
                count++;
                if (count == cattle[y][x]) {
                    break;
                }
            }
        }
        return count == cattle[y][x];
    }

    private static int[][] makeCattle() throws IOException {
        int[][] cattle = new int[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++) {
            String temp = br.readLine();
            for (int j = 0; j < size[1]; j++) {
                char c = temp.charAt(j);
                if (c == '.') {
                    c = '0';
                    zero[i][j] = true;
                }
                if (c == '9') {
                    zero[i][j] = true;
                }
                cattle[i][j] = c - '0';
            }
        }
        return cattle;
    }

    private static int[][] cloneCattle() {
        int[][] newCattle = new int[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++) {
            newCattle[i] = cattle[i].clone();
        }
        return newCattle;
    }
}
