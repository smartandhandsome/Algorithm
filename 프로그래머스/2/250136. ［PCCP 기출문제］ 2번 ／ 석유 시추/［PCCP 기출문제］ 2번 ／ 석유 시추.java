import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        
        int[][] visited = new int[land.length][land[0].length];
        
        Map<Integer, Integer> map = new HashMap<>();
        int number = 1;
        
        
        for (int c = 0; c < land[0].length; c++) {
            Set<Integer> set = new HashSet<>();
            int cnt = 0;
            
            for (int r = 0; r < land.length; r++) {
                if (land[r][c] == 1) {
                    if (visited[r][c] == 0) {
                        map.put(number, bfs(land, visited, r, c, number));
                        number++;
                    }
                    set.add(visited[r][c]);
                }
            }
            
            for (int s : set) {
                cnt += map.get(s);
            }
            
            answer = Math.max(cnt, answer);
        }
        
        return answer;
    }
    
    static int bfs(int[][] land, int[][] visited, int r, int c, int number) {
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[] {r, c});
        visited[r][c] = number;
        
        List<int[]> cntSave = new ArrayList<>();
        cntSave.add(new int[] {r, c});
        
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int[] coor = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int newR =coor[0] + directions[i][0];
                int newC = coor[1] + directions[i][1];
                
                if (0 <= newR && newR < land.length && 0 <= newC && newC < land[0].length && land[newR][newC] == 1 && visited[newR][newC] == 0) {
                    q.add(new int[] {newR, newC});
                    visited[newR][newC] = number;            
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}