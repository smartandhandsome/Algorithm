import java.util.*;

public class Main {
    static boolean[][] grid = grid = new boolean[101][101];
    static int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt(), y = sc.nextInt(), d = sc.nextInt(), g = sc.nextInt();
            // 즉, K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다.
            solution(y, x, d, g);
        }
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static void solution(int y, int x, int d, int g) {
        List<int[]> coors = new ArrayList<>();
        coors.add(new int[]{y, x});
        coors.add(new int[]{y + directions[d][0], x + directions[d][1]});
        for (int i = 1; i <= g; i++) {
            int[] point = coors.get(coors.size() - 1);
            int size = coors.size() - 1;
            for (int j = size; j >= 0; j--) {
                int[] target = coors.get(j);
                coors.add(turn(target[0], point[0], target[1], point[1]));
            }
        }
        for (int[] coor : coors) {
            if (coor[0] < 0 || coor[0] >= 101 || coor[1] < 0 || coor[1] >= 101) {
                continue;
            }
            grid[coor[0]][coor[1]] = true;
        }
    }

    public static int[] turn(int y, int a, int x, int b) {
        int[] point = {y, x};
        // 기준을 0, 0으로 옮기기
        point[0] -= a;
        point[1] -= b;
        // 90 도 돌리기
        int temp = point[0];
        point[0] = point[1];
        point[1] = -temp;
        // 원래 기준으로 졸리기
        point[0] += a;
        point[1] += b;
        return point;
    }
}
