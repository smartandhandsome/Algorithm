import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree;
    static ArrayList<Integer>[] relation;
    static ArrayList<Integer> order;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        indegree = new int[N];
        relation = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            relation[A].add(B);
            indegree[B]++;
        }

        order = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) { 
                q.offer(i);
            }
        }
        while(!q.isEmpty()) {
            int n = q.poll();
            order.add(n);
            for (int next : relation[n]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int i : order) {
            System.out.print((i+1) + " ");
        }
    }
}