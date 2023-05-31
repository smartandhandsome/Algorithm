import java.util.*;

public class Main {
    static Node[] tree;
    static boolean[] visited;
    static int N;
    static int answer = 0;
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        tree = new Node[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N; i++) {
            int cur = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            tree[cur] = new Node(l, r);
        }
        dfs(1);
        removeRightCnt(1);
        System.out.println(--answer);
    }
    
    public static void dfs(int idx) {
        if (checkLeft(idx)) {
            answer++;
            dfs(tree[idx].l);
        }
        if (checkRight(idx)) {
            answer++;
            dfs(tree[idx].r);
        }
        answer++;
    } 
    
    public static void removeRightCnt(int idx) {
        if (tree[idx].r == -1) {
            return;
        }
        removeRightCnt(tree[idx].r);
        answer--;
    }
    
    public static boolean checkLeft(int idx) {
        return tree[idx].l != -1 && !visited[tree[idx].l];
    }
    
    public static boolean checkRight(int idx) {
        return tree[idx].r != -1 && !visited[tree[idx].r];
    }
}

class Node {
    int l;
    int r;
    
    Node(int l, int r) {
        this.l = l;
        this.r = r;
    }
}