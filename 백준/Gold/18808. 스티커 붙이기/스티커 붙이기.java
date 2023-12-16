import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static boolean[][] nb;

    private static int h;
    private static int w;

    private static int[] size;
    private static boolean[][] sticker;

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");

        h = Integer.parseInt(s[0]);
        w = Integer.parseInt(s[1]);
        nb = new boolean[h][w];

        int N = Integer.parseInt(s[2]);

        for (int n = 0; n < N; n++) { // 100

            size = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            sticker = new boolean[size[0]][size[1]];
            for (int i = 0; i < size[0]; i++) {

                String[] line = br.readLine().split(" ");

                for (int j = 0; j < size[1]; j++) {
                    boolean a = sticker[i][j];
                    sticker[i][j] = line[j].equals("1") ? true : false;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (i != 0) {
                    sticker = turn();
                }
//                printS();
                if (search()) {
                    break;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (nb[i][j]) {
                    answer++;
//                    System.out.print("1 ");
                } else {
//                    System.out.print("0 ");
                }
            }
        }
        System.out.println(answer);
    }

    public static void print() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (nb[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printS() {
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                if (sticker[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean[][] turn() {
        boolean[][] temp = new boolean[size[1]][size[0]];

        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                temp[j][size[0] - 1 - i] = sticker[i][j];
            }
        }

        int t = size[0];
        size[0] = size[1];
        size[1] = t;

        return temp;
    }

    public static boolean search() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (attach(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean attach(int y, int x) {
        boolean[][] temp = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                temp[i][j] = nb[i][j];
            }
        }

        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                if (!isIn(y + i, x + j) || (sticker[i][j] && nb[y + i][x + j])) { // 겹침
                    return false;
                }
                if (sticker[i][j]) {
                    temp[y + i][x + j] = true;
                }
            }
        }
        nb = temp;
//        print();
        return true;
    }

    public static boolean isIn(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
