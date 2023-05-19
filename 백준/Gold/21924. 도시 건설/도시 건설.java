import java.util.*;
import java.io.*;

public class Main {
    static int[] parents;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        parents = new int[N+1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }
        long answer = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            answer += c;
            q.offer(new int[] {a, b, c});
        }

        while(!q.isEmpty()) {
            int[] combo = q.poll();
            int a = combo[0];
            int b = combo[1];
            int c = combo[2];
            if (union(a, b)) {
                answer -= c;
            }
        }
        
        if (!check()) {
            answer = -1;
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
        } else if (foundA > foundB) {
            parents[foundA] = foundB;
        } else {
            parents[foundB] = foundA;
        }
        return true;
    }
    
    public static boolean check() {
        int temp = find(1);
        int N = parents.length - 1;
        for (int i = 1; i < N + 1; i++) {
            if (temp != find(i)) {
                return false;
            }
        }
        return true;
    }
}