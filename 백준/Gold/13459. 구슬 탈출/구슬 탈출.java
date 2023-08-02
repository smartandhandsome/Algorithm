import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken());

        Ball red = null;
        Ball blue = null;
        map = new char[y][x];
        for (int i = 0; i < y; i++) {
            String line = br.readLine();
            for (int j = 0; j < x; j++) {
                char c = line.charAt(j);
                if (c == 'B') {
                    blue = new Ball(i, j);
                    c = '.';
                }
                if (c == 'R') {
                    red = new Ball(i, j);
                    c = '.';
                }
                map[i][j] = c;
            }
        }

        Queue<CustomObj> q = new LinkedList<>();
        q.add(new CustomObj(red.copy(), blue.copy(), 0));
        while (!q.isEmpty()) {
            CustomObj poll = q.poll();
            Ball r = poll.red;
            Ball b = poll.blue;
//            printNow(r, b, new int[]{0, 0});
//            System.out.println();
            int c = poll.count;
            if (c == 10) {
                break;
            }
            for (int[] direction : directions) {
                Ball copiedR = r.copy();
                Ball copiedB = b.copy();
                int action = action(copiedR, copiedB, direction);
                if (action == 0) {
                    System.out.println(1);
                    System.exit(0);
                }
                if (action == -1) continue;
                q.add(new CustomObj(copiedR, copiedB, c + 1));

//                printNow(copiedR, copiedB, direction);
//                System.out.println();
            }
        }
        System.out.println(0);
    }

    private static void printNow(Ball r, Ball b, int[] direction) {

        System.out.printf("=====%s=====\n", Arrays.toString(direction));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == r.y && j == r.x) {
                    System.out.print("R");
                    continue;
                }
                if (i == b.y && j == b.x) {
                    System.out.print("B");
                    continue;
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    private static int action(Ball ball1, Ball ball2, int[] direction) {
        boolean fGoal = false;
        boolean lGoal = false;

        while (true) {
            boolean fFlag = false;
            boolean lFlag = false;
            if (!fGoal && ball1.canGo(map, direction, ball2)) {
                ball1.y += direction[0];
                ball1.x += direction[1];
                fFlag = true;
            }
            if (!lGoal && ball2.canGo(map, direction, ball1)) {
                ball2.y += direction[0];
                ball2.x += direction[1];
                lFlag = true;
            }
            if (!fFlag && !lFlag) {
                break;
            }
            if (map[ball1.y][ball1.x] == 'O') {
                fGoal = true;
            }
            if (map[ball2.y][ball2.x] == 'O') {
                lGoal = true;
            }
//            printNow(ball1, ball2, direction);
//            System.out.println();
        }
        if ((fGoal && lGoal) || (!fGoal && lGoal)) {
            return -1;
        }
        return fGoal ? 0 : 1;
    }
}

class CustomObj {
    Ball red;
    Ball blue;
    int count;

    public CustomObj(Ball red, Ball blue, int count) {
        this.red = red;
        this.blue = blue;
        this.count = count;
    }
}

class Ball {
    int y;
    int x;

    public Ball(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Ball copy() {
        return new Ball(this.y, this.x);
    }

    public boolean canGo(char[][] map, int[] direction, Ball another) {
        int targetY = this.y + direction[0];
        int targetX = this.x + direction[1];
        if (map[targetY][targetX] == '#' || (blockAnotherBall(another, direction) && map[targetY][targetX] != 'O')) {
            return false;
        }
        return true;
    }

    private boolean blockAnotherBall(Ball another, int[] direction) {
        return this.y + direction[0] == another.y && this.x + direction[1] == another.x;
    }
}

