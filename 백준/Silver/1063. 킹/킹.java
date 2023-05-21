import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String king = sc.next();
        String stone = sc.next();
        char[] alpha = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        int kingRow = king.charAt(1) - '0';
        int kingCol = -1;
        int stoneRow = stone.charAt(1) - '0';
        int stoneCol = -1;
        for (int i = 0; i < 8; i++) {
            if (alpha[i] == king.charAt(0)) {
                kingCol = i;
            }
            if (alpha[i] == stone.charAt(0)) {
                stoneCol = i;
            }
        }

        HashMap<String, int[]> map = new HashMap<>();
        map.put("R", new int[]{0, 1});
        map.put("L", new int[]{0, -1});
        map.put("B", new int[]{-1, 0});
        map.put("T", new int[]{1, 0});
        map.put("RT", new int[]{1, 1});
        map.put("RB", new int[]{-1, 1});
        map.put("LT", new int[]{1, -1});
        map.put("LB", new int[]{-1, -1});

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String comm = sc.next();
            int[] move = map.get(comm);
            int a = kingRow + move[0];
            int b = kingCol + move[1];

            if (1 <= a && a <= 8 && 0 <= b && b < 8) {

                if (a == stoneRow && b == stoneCol) {
                    int c = stoneRow + move[0];
                    int d = stoneCol + move[1];
                    if (1 <= c && c <= 8 && 0 <= d && d < 8) {
                        stoneRow = c;
                        stoneCol = d;
                    } else {
                        continue;
                    }
                }
                kingRow = a;
                kingCol = b;
            }
        }

        System.out.println(alpha[kingCol] + "" + kingRow);
        System.out.println(alpha[stoneCol] + "" + stoneRow);
    }
}