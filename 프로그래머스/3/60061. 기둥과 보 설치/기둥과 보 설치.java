import java.util.*;

class Solution {
    boolean[][][] map;
    int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        N = n;
        map = new boolean[n + 1][n + 1][2];
        for (int[] line : build_frame) {
            int x = line[0];
            int y = line[1];
            int a = line[2];
            int b = line[3];
            
            if (b == 0) {
                remove(y, x, a);
            } else {
                build(y, x, a);
            }    
        }
        
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (map[j][i][0]) {
                    list.add(new int[] {i, j, 0});
                }
                
                if (map[j][i][1]) {
                    list.add(new int[] {i, j, 1});
                }
            }
        }
        
        return list.toArray(int[][]::new);
    }
    
    public void remove(int y, int x, int a) {
        map[y][x][a] = false;
        if (a == 0) { // 기둥
            if (
                (isInBound(y + 1, x, 0) && map[y + 1][x][0] ? canBuild(y + 1, x, 0) : true) &&
                (isInBound(y + 1, x - 1, 1) && map[y + 1][x - 1][1] ? canBuild(y + 1, x - 1, 1) : true) &&
                (isInBound(y + 1, x, 1) && map[y + 1][x][1] ? canBuild(y + 1, x, 1) : true)
           ) {
                return;
            }
        } else { // 보
            if (
                (isInBound(y, x, 0) && map[y][x][0] ? canBuild(y, x, 0) : true) &&
                (isInBound(y, x + 1, 0) && map[y][x + 1][0] ? canBuild(y, x + 1, 0) : true) &&
                (isInBound(y, x - 1, 1) && map[y][x - 1][1] ? canBuild(y, x - 1, 1) : true) &&
                (isInBound(y, x + 1, 1) && map[y][x + 1][1] ? canBuild(y, x + 1, 1) : true)
            ) {
               return; 
            }
        }
        map[y][x][a] = true;
    }
    
    public void build(int y, int x, int a) {
        if (canBuild(y, x, a)) {
            map[y][x][a] = true;
        }
    }
    
    public boolean canBuild(int y, int x, int a) {
        if (!isInBound(y, x, a)) {
            return false;
        }
        
        if (a == 0) {
            return y == 0 ||
                (isInBound(y, x, 1) && map[y][x][1]) ||
                (isInBound(y, x - 1, 1) && map[y][x - 1][1]) ||
                (isInBound(y - 1, x, 0) && map[y - 1][x][0]);
        } else {
            return isInBound(y - 1, x, 0) && map[y - 1][x][0] ||
                isInBound(y - 1, x + 1, 0) && map[y - 1][x + 1][0] ||
                (isInBound(y, x - 1, 1) && map[y][x - 1][1] &&
                 isInBound(y, x + 1, 1) && map[y][x + 1][1]);
        }
    }
    
    public boolean isInBound(int y, int x, int a) {
        if (a == 0) { // 기둥
            return 0 <= y && y < N && 
                0 <= x && x <= N;
        } else { // 보
            return 0 <= y && y <= N && 
                0 <= x && x < N;
        }
    }
}
