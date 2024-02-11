class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = type == 1 ? -s[5] : s[5];
            
            sum[r1][c1] += degree;
            sum[r1][c2 + 1] += -degree;
            sum[r2 + 1][c1] += -degree;
            sum[r2 + 1][c2 + 1] += degree;
        }
        
        for (int c = 0; c < sum[0].length; c++) {
            for (int r = 1; r < sum.length; r++) {
                sum[r][c] += sum[r-1][c];
            }
        }
        
        for (int r = 0; r < sum.length; r++) {
            for (int c = 1; c < sum[0].length; c++) {
                sum[r][c] += sum[r][c-1];
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += sum[i][j];
            }
        }
        
        for (int[] bo : board) {
            for (int b : bo) {
                if (b > 0) {
                    answer++;
                }
                // System.out.print(b + " ");
            }
            // System.out.println();
        }
        
        return answer;
    }
}