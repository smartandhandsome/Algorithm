import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] A = new int[100][100];
    static int row = 3;
    static int col = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (A[r][c] != k && cnt++ < 100) {
            selectOperation();
        }
        System.out.println(cnt > 100 ? -1 : cnt);
    }

    private static void selectOperation() {
        if (row >= col) {
            R();
        } else {
            C();
        }
    }

    private static void R() {
        int newCol = -1;
        for (int i = 0; i < row; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Num> pq = new PriorityQueue<>();

            for (int j = 0; j < col; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                A[i][j] = 0;
            }
            map.forEach((n, cnt) -> pq.offer(new Num(n, cnt)));

            int c = 0;
            while (!pq.isEmpty()) {
                Num num = pq.poll();
                A[i][c++] = num.n;
                if (c >= 100) {
                    break;
                }
                A[i][c++] = num.cnt;
                if (c >= 100) {
                    break;
                }
            }
            newCol = Math.max(c, newCol);
        }
        col = newCol;
    }

    private static void C() {
        int newRow = -1;
        for (int i = 0; i < col; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Num> pq = new PriorityQueue<>();
            for (int j = 0; j < row; j++) {
                if (A[j][i] == 0) {
                    continue;
                }
                map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
                A[j][i] = 0;
            }
            map.forEach((n, cnt) -> pq.offer(new Num(n, cnt)));

            int r = 0;
            while (!pq.isEmpty()) {
                Num num = pq.poll();
                A[r++][i] = num.n;
                if (r >= 100) {
                    break;
                }
                A[r++][i] = num.cnt;
                if (r >= 100) {
                    break;
                }
            }
            newRow = Math.max(r, newRow);
        }
        row = newRow;
    }
}

class Num implements Comparable<Num> {
    int n;
    int cnt;

    public Num(int n, int cnt) {
        this.n = n;
        this.cnt = cnt;
    }

    public int compareTo(Num o) {
        return (this.cnt == o.cnt) ? Integer.compare(this.n, o.n) : Integer.compare(this.cnt, o.cnt);
    }
}