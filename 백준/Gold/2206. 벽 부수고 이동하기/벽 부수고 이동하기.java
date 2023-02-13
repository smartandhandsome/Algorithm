import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] shape = setShape();
        int[][] map = setMap(shape[0], shape[1]);
        int answer = BFS(map, shape[0], shape[1]);
        System.out.println(answer);
    }

    public static int BFS(int[][] map, int row, int col) {
        int[][][] visited = new int[row][col][2];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        visited[0][0][0] = 1;
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            if (coordinate[0] == row - 1 && coordinate[1] == col - 1) {
                return visited[coordinate[0]][coordinate[1]][coordinate[2]];
            }
            for (int i = 0; i < 4; i++) {
                int x = coordinate[0] + dx[i];
                int y = coordinate[1] + dy[i];
                int wall = coordinate[2];
                if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y][wall] != 0) {
                    continue;
                }
                if (map[x][y] == 1) {
                    if (wall == 1) {
                        continue;
                    }
                    wall = 1;
                }
                visited[x][y][wall] = visited[coordinate[0]][coordinate[1]][coordinate[2]] + 1;
                queue.add(new int[]{x, y, wall});
            }
        }
        return -1;
    }

    public static int[] setShape() throws IOException {
        String[] token = br.readLine().split(" ");
        int row = Integer.parseInt(token[0]);
        int col = Integer.parseInt(token[1]);
        return new int[]{row, col};
    }

    public static int[][] setMap(int row, int col) throws IOException {
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        return map;
    }
}
