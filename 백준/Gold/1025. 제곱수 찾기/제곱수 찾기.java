import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt();
        sc.nextLine();
        int[][] table = new int[r][c];
        for (int i = 0; i < r; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < c; j++) {
                table[i][j] = line.charAt(j) - '0';
            }
        }
        int answer = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer = Math.max(bruteforce(table, r, c, i, j), answer);
            }
        }
        System.out.println(answer);
    }

    public static int bruteforce(int[][] tables, int r, int c, int y, int x) {
        int ret = -1;
        for (int i = -r + 1; i <= r - 1; i++) {
            if (y + i < 0 || y + i >= r) {
                continue;
            }
            for (int j = -c + 1; j <= c - 1; j++) {
                if (x + j < 0 || x + j >= c) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                int k = 0;
                while (y + i * k >= 0 && y + i * k < r && x + j * k >= 0 && x + j * k < c) {
                    sb.append(tables[y + i * k][x + j * k]);
                    int num = Integer.parseInt(sb.toString());
                    if (num % Math.sqrt(num) == 0 || num == 0) {
                        ret = Math.max(ret, (int) num);
                    }
                    if (i == 0 && j == 0) {
                        break;
                    }
                    k++;
                }
            }
        }
        return ret;
    }
}