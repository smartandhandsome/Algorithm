import java.util.*;

public class Solution {
    private int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;
    private int N;
    private int M;
    private String[][] p;

    public int[] solution(String[][] places) {
        p = places;
        int T = places.length;
        N = places[0].length;
        M = places[0][0].length();

        visited = new boolean[N][M];

        int[] answer = new int[T];
        Arrays.fill(answer, 1);

        for (int t = 0; t < T; t++) {
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                boolean flag = false;

                for (int j = 0; j < M; j++) {

                    if (places[t][i].charAt(j) == 'P') {
                        int result = dfs(i, j, 0, 0, t);

                        if (result <= -1) {
                            answer[t] = 0;
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
        }

        return answer;
    }


    public int dfs(int y, int x, int depth, int sum, int t) {
        System.out.println(y + " " + x);
        visited[y][x] = true;
        if (depth == 2) {
            return sum;
        }

        for (int[] dir : directions) {
            int dy = y + dir[0];
            int dx = x + dir[1];
            if (0 <= dy && dy < N && 0 <= dx && dx < M && !visited[dy][dx] && p[t][dy].charAt(dx) != 'X') {
                if (p[t][dy].charAt(dx) == 'P') {
                    return -1;
                }
                sum += dfs(dy, dx, depth + 1, sum, t);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        new Solution().solution(
                new String[][]{{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}}
        );
    }
}
