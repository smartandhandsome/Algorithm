import java.util.*;

class Solution {
    private int answer = -1;
    
    public int solution(int k, int[][] dungeons) {
        
        dfs(dungeons, k, new boolean[dungeons.length], 0);
        
        return answer;
    }
    
    public void dfs(int[][] dungeons, int hp, boolean[] visited, int count) {
        answer = Math.max(count, answer);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (dungeons[i][0] > hp || visited[i]) {
                continue;
            }
            
            visited[i] = true;
            dfs(dungeons, hp-dungeons[i][1], visited, count + 1);
            visited[i] = false;
        }
        
    }
}