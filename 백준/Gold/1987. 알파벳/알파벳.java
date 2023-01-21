import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Integer[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Integer[][] map;
    static Integer answer = 0;

    public static void main(String[] args) throws IOException {
        String[] str = br.readLine().split(" ");
        int row = Integer.parseInt(str[0]);
        int col = Integer.parseInt(str[1]);

        map = new Integer[row][col];
        setMap(row, col);

        boolean[] check = new boolean[26];
        DFS(0, 0, check, 1);

        System.out.println(answer);
    }

    public static void setMap(int row, int col) throws IOException {
        for (int r = 0; r < row; r++) {
            String temp = br.readLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = temp.charAt(c) - 'A';
            }
        }
    }

    public static void DFS(int y, int x, boolean[] check, int length) {
        check[map[y][x]] = true;
        answer = Math.max(length, answer);

        for (Integer[] direction : directions) {
            int movedY = direction[0] + y;
            int movedX = direction[1] + x;
            if (movedY >=  0 && movedX >= 0 && movedY < map.length && movedX < map[0].length) {
                if (!check[map[movedY][movedX]]) {
                    DFS(movedY, movedX, check, length + 1);
                    check[map[movedY][movedX]] = false;
                }
            }
        }
    }
}
