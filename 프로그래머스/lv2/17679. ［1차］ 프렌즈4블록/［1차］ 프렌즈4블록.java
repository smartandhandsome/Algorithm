import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int solution(int m, int n, String[] board) {
        Character[][] cBoard = convert(m, n, board);
        while (true) {
            List<Integer[]> goingToBreak = isGoingToBreak(m, n, cBoard);
            if (goingToBreak.size() == 0) {
                break;
            }
            breakBlock(cBoard, goingToBreak);
            down(cBoard);
        }

        int answer = 0;
        for (Character[] characters : cBoard) {
            for (Character character : characters) {
                if (character == '.') {
                    answer++;
                }
            }
        }
        return answer;
    }

    private void down(Character[][] board) {
        for (int i = board.length - 2; i >= 0; i--) {
            for (int j = board[0].length - 1; j >= 0; j--) {
                if (board[i][j] != '.' && board[i + 1][j] == '.') {
                    int gap = 1;
                    while (i + gap < board.length && board[i + gap][j] == '.') {
                        gap += 1;
                    }
                    board[i + gap - 1][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
    }

    private void breakBlock(Character[][] board, List<Integer[]> breakList) {
        for (Integer[] coordinate : breakList) {
            int y = coordinate[0], x = coordinate[1];
            for (int i = y; i < y + 2; i++) {
                for (int j = x; j < x + 2; j++) {
                    if (board[i][j] != '.') {
                        board[i][j] = '.';
                    }
                }
            }
        }
    }

    private Character[][] convert(int m, int n, String[] board) {
        Character[][] convertedBoard = new Character[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                convertedBoard[i][j] = board[i].charAt(j);
            }
        }
        return convertedBoard;
    }

    private List<Integer[]> isGoingToBreak(int m, int n, Character[][] board) {
        List<Integer[]> breakList = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (board[i][j] != '.' && is4Blocks(board, i, j)) {
                    breakList.add(new Integer[]{i, j});
                }
            }
        }
        return breakList;
    }

    private boolean is4Blocks(Character[][] board, int y, int x) {
        char block = board[y][x];
        for (int i = y; i < y + 2; i++) {
            for (int j = x; j < x + 2; j++) {
                if (board[i][j] != block) {
                    return false;
                }
            }
        }
        return true;
    }
}