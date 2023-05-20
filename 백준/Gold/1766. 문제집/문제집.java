import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree;
    static ArrayList<Integer>[] relation;
    static ArrayList<Integer> order;
    static boolean[] solved;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        indegree = new int[N];
        relation = new ArrayList[N];
        solved = new boolean[N];
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
        addZero(q);
        while(!q.isEmpty()) {
            int n = q.poll();
            order.add(n);
            for (int next : relation[n]) {
                indegree[next]--;
            }
            addZero(q);
        }
        for (int i : order) {
            System.out.print((i+1) + " ");
        }
    }
    
    public static void addZero(Queue<Integer> q) {
        int N = indegree.length;
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0  && !solved[i]) {
                q.add(i);
                solved[i] = true;
            }
        }
    }
}