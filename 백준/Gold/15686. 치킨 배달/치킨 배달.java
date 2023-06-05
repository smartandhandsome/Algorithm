import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    static List<Block> cList = new ArrayList<>();
    static List<Block> pList = new ArrayList<>();
    static List<Block> empty = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][N];
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    pList.add(new Block(i, j));
                } else if (map[i][j] == 2) {
                    cList.add(new Block(i, j));
                }
            }
        }
        combinations(0, 0);
        System.out.println(answer);
    }

    public static void combinations(int depth, int idx) {
        if (depth == M) {
            int distance = 0;
            for (Block p : pList) {
                int min = Integer.MAX_VALUE;
                for (Block b : empty) {
                    min = Math.min(Math.abs(p.y - b.y) + Math.abs(p.x - b.x), min);
                }
                distance += min;
                if (distance > answer) {
                    return;
                }
            }
            answer = distance;
            return;
        }
        for (int i = idx; i < cList.size(); i++) {
            Block b = cList.get(i);
            empty.add(b);
            combinations(depth + 1, i + 1);
            empty.remove(empty.size() - 1);
        }
    }
}

class Block {
    int y;
    int x;

    public Block(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
