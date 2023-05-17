import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                if (start == mid) {
                    continue;
                }
                for (int dest = 0; dest < N; dest++) {
                    if (dest == start || dest == mid) {
                        continue;
                    }
                    map[start][dest] = Math.min(map[start][dest], map[start][mid] + map[mid][dest]);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int timeLimit = Integer.parseInt(st.nextToken());
            String answer = "Enjoy other party";
            if (map[start][end] > timeLimit) {
                answer = "Stay here";
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void printMap(int[][] map, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }
    }
}