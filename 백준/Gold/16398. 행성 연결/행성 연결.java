import java.util.*;
import java.io.*;

public class Main {
    static int[] parents;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt((o) -> o[2]));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (j >= i + 1) {
                    q.offer(new int[]{i, j, graph[i][j]});
                }
            }
        }
        long answer = 0;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            if (union(temp[0], temp[1])) {
                // System.out.println(temp[0] + " " + temp[1]);
                // System.out.println(Arrays.toString(parents));
                boolean flag = true;
                int t = parents[0];
                for (int i = 0; i < N; i++) {
                    if (t != parents[i]) {
                        flag = false;
                    }
                }
                answer += temp[2];
                if(flag) {
                    break;
                }
            }
        }
        System.out.println(answer);
        
    }
    
    public static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    
    public static boolean union(int a, int b) {
        int foundA = find(a);
        int foundB = find(b);
        
        if (foundA == foundB) {
            return false;
        }
        if (foundA > foundB) {
            parents[foundA] = foundB;
        } else {
            parents[foundB] = foundA;
        }
        return true;
    }
}