import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Wire[] wires = new Wire[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            wires[i] = new Wire(left, right);
        }
        int answer = 0;
        Arrays.sort(wires);

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 최소 연결 개수
            for (int j = 0; j < i; j++) {
                if (wires[i].right > wires[j].right) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        
        System.out.println(N - answer);
    }
}

class Wire implements Comparable<Wire> {
    int left;
    int right;

    public Wire(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Wire o) {
        return Integer.compare(this.left, o.left);
    }

}