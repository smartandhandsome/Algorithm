import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int t = 1; ; t++) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) {
                break;
            }
            int[][] cave = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(s[j]);
                }
            }
            sb.append("Problem ").append(t).append(": ").append(bfs(cave, N)).append("\n");
        }
        
        System.out.println(sb);
    }

    public static int bfs(int[][] cave, int N) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        int[][] sMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(sMap[i], Integer.MAX_VALUE);
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0});
        sMap[0][0] = cave[0][0];

        while(!q.isEmpty()) {
            int[] coor = q.poll();

            for (int[] dir : dirs) {
                int dy = coor[0] + dir[0];
                int dx = coor[1] + dir[1];
                
                if (isIn(dy,dx,N) && cave[dy][dx] + sMap[coor[0]][coor[1]] < sMap[dy][dx]) {
                    sMap[dy][dx] = cave[dy][dx] + sMap[coor[0]][coor[1]];
                    q.add(new int[]{dy, dx});
                }
            }
        }
        return sMap[N - 1][N - 1];
    }

    public static boolean isIn(int dy, int dx, int N) {
        return 0 <= dy && dy < N && 0 <= dx && dx < N;
    }
}