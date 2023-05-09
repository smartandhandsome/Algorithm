import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(seq);
        // System.out.println(Arrays.toString(seq));
        int answer = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, N-1});
        boolean[][] visited = new boolean[N+1][N+1];
        
        while(!q.isEmpty()) {
            int[] range = q.poll();
            if (range[0] == range[1]) {
                break;
            }
            
            if (seq[range[1]] < seq[range[0]] + seq[range[0] + 1] && seq[range[1]] + seq[range[1] - 1] > seq[range[0]]){
                // System.out.println(range[0] + " " + range[1]);
                answer = range[1] - range[0] + 1;
                break;
            } else {
                if (!visited[range[0] + 1][range[1]]) {
                    visited[range[0] + 1][range[1]] = true;
                    q.offer(new int[] {range[0] + 1, range[1]});
                }
                if (!visited[range[0]][range[1] - 1]) {
                    visited[range[0]][range[1] - 1] = true;
                    q.offer(new int[] {range[0], range[1] - 1});
                }
            }
        }
        System.out.println(answer);
    }
}