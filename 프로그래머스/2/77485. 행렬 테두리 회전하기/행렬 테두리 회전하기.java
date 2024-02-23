class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = (i * columns) + (j+1);
            }
        }
        
        
        for (int i = 0; i < queries.length; i++)  {
            answer[i] = spin(map, queries[i]);
        }
        
        return answer;
    }
    
    static int spin(int[][] map, int[] query) {
        int min = Integer.MAX_VALUE;
        
        int minY = query[0] - 1;
        int minX = query[1] - 1;
        
        int maxY = query[2] - 1;
        int maxX = query[3] - 1;
        
        int y = minY;
        int x = minX;
        
        int prev = map[y][x];
        int i = 0;
        while(true) {
            y += directions[i][0];
            x += directions[i][1];
            
            if (minY > y || y > maxY || minX > x || x > maxX) {
                y -= directions[i][0];
                x -= directions[i][1];
                i++;
                if (i >= 4) {
                    break;
                }
                continue;
            }
            min = Math.min(map[y][x], min);
            int temp = map[y][x];
            map[y][x] = prev;
            prev = temp;
        }
        
        return min;
    }
    
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}