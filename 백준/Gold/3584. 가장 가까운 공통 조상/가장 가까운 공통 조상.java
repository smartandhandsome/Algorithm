import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer> path = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer>[] tree = new ArrayList[N+1];
            for (int i = 1; i < N+1; i++){
                tree[i] = new ArrayList<>();
            }
            HashSet<Integer> parent = new HashSet<>();
            HashSet<Integer> son = new HashSet<>();
            for (int i = 0; i < N-1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                parent.add(a);
                int b = Integer.parseInt(st.nextToken());
                son.add(b);
                tree[a].add(b);
            }
            parent.removeAll(son);
            int top = parent.iterator().next();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            dfs(tree, top, a);
            ArrayList<Integer> forA = (ArrayList<Integer>) path.clone();
            path.clear();
            
            dfs(tree, top, b);
            ArrayList<Integer> forB = (ArrayList<Integer>) path.clone();
            path.clear();
            
            int answer = top;
            int leng = Math.min(forA.size(), forB.size());
            for (int i = 0; i < leng; i++) {
                int f = forA.get(i);
                int s = forB.get(i);
                if (f != s) {
                    break;
                }
                answer = f;
            }
            
            System.out.println(answer);
        }
        
    }
    public static void dfs(ArrayList<Integer>[] tree, int node, int target) {
        if (node == target){
            return;
        }
        
        for(int next : tree[node]){
            path.add(next);
            dfs(tree, next, target);
            if (path.contains(target)){
                break;
            }
            path.remove((Integer) next);
        }
    }
}