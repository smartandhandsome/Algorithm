import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree;
    static ArrayList<Integer>[] relation;
    static int[] stand;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        relation = new ArrayList[N];
        indegree = new int[N];
        stand = new int[N];
        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            relation[X].add(Y);
            indegree[Y]++;
        }
        // for (ArrayList<Integer> list : relation) {
        //     System.out.println(list);
        // }
        // System.out.println(Arrays.toString(indegree));
        String answer = "King-God-Emperor";
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int comm = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken()) - 1;
            if (comm == 1) {
                if (!build(building)) {
                    answer = "Lier!";
                    break;
                }
            } else {
                if (!destroy(building)) {
                    answer = "Lier!";
                    break;
                }
            }
        }
        System.out.println(answer);
    }
    
    public static boolean build(int n) {
        if (indegree[n] != 0) {
            return false;
        }
        stand[n] += 1;
        if (stand[n] == 1) {
            for (Integer i : relation[n]) {
                indegree[i] -= 1;
            }
        }
        return true;
    }
    
    public static boolean destroy(int n) {
        if (stand[n] == 0) {
            return false;
        }
        stand[n] -=1;
        if (stand[n] == 0) {
            for (Integer i : relation[n]) {
                indegree[i] += 1;
            }
        }
        return true;
    }
}