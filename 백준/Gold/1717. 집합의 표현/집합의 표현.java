import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    int[] parent;

    Main(int n, int m) {
        this.n = n;
        this.m = m;
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Main main = new Main(n, m);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operation == 0) {
                main.union(a, b);
            } else if (operation == 1) {
                boolean isSame = main.check(a, b);
                sb.append((isSame ? "YES" : "NO") + "\n");
            }
        }
        System.out.print(sb);
    }

    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public boolean check(int a, int b) {
        return find(a) == find(b);
    }

    public void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }
}
