import java.util.*;

class Solution {
    static int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int solution(String[] board) {
        int N = board.length;
        int M = board[0].length();
        
        Integer[] goal = new Integer[0];
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        
        boolean[][] visited = new boolean[N][M];
        for (int i = 0 ; i < N; i++) {
            for (int j = 0 ; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    queue.offer(new Integer[] {i, j, 0});
                    visited[i][j] = true;
                } else if (board[i].charAt(j) == 'G') {
                    goal = new Integer[] {i, j};            
                }
            }
        }
        
        while (!queue.isEmpty()) {
           Integer[] t = queue.poll();
            int y = t[0];
            int x = t[1];
            // System.out.println(y + " " + x);
            int cnt = t[2];
            if (y == goal[0] && x == goal[1]) {
                return cnt;
            }
            
            for (int[] move : direction) {
                int tempY = y;
                int tempX = x;
                
                while (true) {
                    if (0 > tempY || tempY >= N || 0 > tempX || tempX >= M || board[tempY].charAt(tempX) == 'D') {
                        if (!visited[tempY-move[0]][tempX-move[1]]) {
                            // System.out.println("original: " + tempY + " " + tempX + " moved: " + (tempY-move[0]) + " " + (tempX-move[1]));
                            visited[tempY-move[0]][tempX-move[1]] = true;
                            queue.add(new Integer[] {tempY-move[0], tempX-move[1], cnt + 1});
                        }
                        break;
                    }
                    tempY += move[0];
                    tempX += move[1];
                }
            }
        }
        return -1;
    }
}