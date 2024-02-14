import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] prices = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(prices[i], 20000001);
            prices[i][i] = 0;
        }
        
        for (int i = 0; i < fares.length; i++) {
            int[] edge = fares[i];
            prices[edge[0]][edge[1]] = edge[2];
            prices[edge[1]][edge[0]] = edge[2];
        }
        
        for (int i = 1; i <= n; i++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    prices[x][y] = Math.min(prices[x][y], prices[x][i] + prices[i][y]);
                }
            }
        }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j <= n; j++) {
        //         System.out.printf("%2d ", (prices[i][j] == 20000001) ? -1 : prices[i][j]);
        //     }
        //     System.out.println();
        // }
        
        int answer = prices[s][a] + prices[s][b];
        for (int i = 1; i <= n; i++) {
            answer = Math.min(prices[s][i] + prices[i][a] + prices[i][b], answer);
        }
        
        return answer;
    }
}