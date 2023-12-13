import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[][] coor = new int[n + 2][2];
            coor[0] = parse();
            for(int i = 1; i <= n; i++) {
                coor[i] = parse();
            }
            coor[n + 1] = parse();
            
            boolean[] isVisited = new boolean[n + 2];
            Queue<Integer> q = new ArrayDeque<>();
            q.add(0);
            
            while(!q.isEmpty()) {
                int index = q.poll();
                isVisited[index] = true;
                
                for (int i = 0; i < n + 2; i++) {
                    if (!isVisited[i] && calcDistance(coor[index], coor[i]) <= 1000) {
                        q.add(i);
                    }
                }
            }
            
            sb.append(isVisited[n+1] ? "happy\n" : "sad\n");
        }
        System.out.println(sb);
    }
    
    private static int calcDistance(int[] cur, int[] next) {
        return Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]);
    }

    private static int[] parse() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }
}
