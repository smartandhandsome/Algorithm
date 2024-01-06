import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Solution {
    int row;
    int col;

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        Cell[][] board = new Cell[51][51];
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j < 51; j++) {
                board[i][j] = new Cell("");
            }
        }

        for (String command : commands) {
            String[] co = command.split(" ");
            // System.out.println(command);
            String comm = co[0];
            switch (comm) {
                case "UPDATE":
                    if (co.length == 3) {
                        String value1 = co[1];
                        String value2 = co[2];

                        update(board, value1, value2);
                    } else if (co.length == 4) {
                        int r = Integer.parseInt(co[1]);
                        int c = Integer.parseInt(co[2]);

                        row = Math.max(r, row);
                        col = Math.max(c, col);

                        String value = co[3];
                        board[r][c].changeContent(value);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(co[1]);
                    int c1 = Integer.parseInt(co[2]);

                    int r2 = Integer.parseInt(co[3]);
                    int c2 = Integer.parseInt(co[4]);

                    row = Math.max(row, r1);
                    row = Math.max(row, r2);
                    col = Math.max(col, c1);
                    col = Math.max(col, c2);

                    if (board[r1][c1] == board[r2][c2]) {
                        break;
                    }
                    merge(board, r1, c1, r2, c2);
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(co[1]);
                    int c = Integer.parseInt(co[2]);

                    unmerge(board, r, c);
                    break;
                case "PRINT":
                    r = Integer.parseInt(co[1]);
                    c = Integer.parseInt(co[2]);

                    answer.add(board[r][c].print());
                    break;
            }
            // print(board);
        }

        return answer.toArray(new String[answer.size()]);
    }

    public void print(Cell[][] board) {
        int max = Math.max(row, col);
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= max; j++) {
                if (board[i][j].isBlank()) {
                    System.out.print("E ");
                    continue;
                }
                System.out.print(board[i][j].getContent() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void update(Cell[][] board, String value1, String value2) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 1});

        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] coor = q.poll();

            for (int[] dir : dirs) {
                int dy = coor[0] + dir[0];
                int dx = coor[1] + dir[1];
                if (1 <= dy && dy <= row && 1 <= dx && dx <= col && !visited[dy][dx]) {
                    if (board[dy][dx].getContent().equals(value1)) {
                        board[dy][dx].changeContent(value2);
                    }
                    visited[dy][dx] = true;
                    q.add(new int[]{dy, dx});
                }
            }
        }
    }

    public void merge(Cell[][] board, int r1, int c1, int r2, int c2) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Cell target = null;
        Cell org = null;
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        if (board[r1][c1].isBlank()) {
            org = board[r1][c1];
            visited[r1][c1] = true;
            target = board[r2][c2];
            q.add(new int[]{r1, c1});
        } else if (board[r2][c2].isBlank()) {
            org = board[r2][c2];
            visited[r2][c2] = true;

            target = board[r1][c1];

            q.add(new int[]{r2, c2});
        } else {
            org = board[r2][c2];
            visited[r2][c2] = true;
            target = board[r1][c1];
            q.add(new int[]{r2, c2});
        }

        while (!q.isEmpty()) {
            int[] coor = q.poll();

            for (int[] dir : dirs) {
                int dy = coor[0] + dir[0];
                int dx = coor[1] + dir[1];
                if (0 <= dy && dy <= row && 0 <= dx && dx <= col && !visited[dy][dx]) {
                    if (board[dy][dx] == org) {
                        board[dy][dx] = target;
                    }
                    q.add(new int[]{dy, dx});
                    visited[dy][dx] = true;
                }
            }
        }

        if (board[r1][c1].isBlank()) {
            board[r1][c1] = board[r2][c2];
        } else if (board[r2][c2].isBlank()) {
            board[r2][c2] = board[r1][c1];
        } else {
            board[r2][c2] = board[r1][c1];
        }
    }

    public void unmerge(Cell[][] board, int r, int c) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Cell target = board[r][c];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});

        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] coor = q.poll();

            for (int[] dir : dirs) {
                int dy = coor[0] + dir[0];
                int dx = coor[1] + dir[1];
                if (0 <= dy && dy <= row && 0 <= dx && dx <= col && !visited[dy][dx]) {
                    if (board[dy][dx] == target) {
                        board[dy][dx] = new Cell("");
                    }
                    q.add(new int[]{dy, dx});
                    visited[dy][dx] = true;
                }
            }
        }
    }

    static class Cell {
        String content;

        public Cell(String content) {
            this.content = content;
        }

        public boolean isBlank() {
            return content.isBlank();
        }

        public void changeContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public String print() {
            return isBlank() ? "EMPTY" : content;
        }
    }
}
