import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] gates;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        gates = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            gates[i] = i;
        }
        int answer = 0;
        for (int i = 0; i < M; i++) {
            int g = sc.nextInt();
            if (!union(g)) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
        sc.close();
    }

    public static int find(int a) {
        if (gates[a] == a) {
            return a;
        }
        return gates[a] = find(gates[a]);
    }

    public static boolean union(int a) {
        int foundA = find(a);
        if (foundA == 0) {
            return false;
        }
        gates[foundA] = foundA - 1;
        return true;
    }

}