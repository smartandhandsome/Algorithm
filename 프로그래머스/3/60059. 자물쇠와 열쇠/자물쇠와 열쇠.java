class Solution {
    public boolean solution(int[][] key, int[][] lock) {        
        int m = lock.length;
        int n = key.length;
        
        int extend = m + ((n - 1) * 2);
        int[][] b = new int[extend][extend];
        for (int i = n - 1; i < n - 1 + m; i++) {
            for (int j = n - 1; j < n - 1 + m; j++) {
                b[i][j] = lock[i - n + 1][j- n + 1];
            }
        }
        // for (int[] line : b) {
        //     for (int cell : line) {
        //         System.out.print(cell + " ");
        //     }
        //     System.out.println();
        // }
        
        for (int t = 0; t < 4; t++) {
            if (t != 0) key = turn(key);
            for (int i = 0; i <= extend - n; i++) {
                for (int j = 0; j <= extend - n; j++) {
                    if (check(b, key, n, m, i, j)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    static int[][] turn(int[][] block) {
        int r = block.length;
        int c = block[0].length;
        int[][] temp = new int[c][r];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp[j][r - 1 - i] = block[i][j];
            }
        }
        
        // for (int i = 0; i < r; i++) {
        //     for (int j = 0; j < c; j++) {
        //         System.out.print(block[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        return temp;
    }
    
    static boolean check(int[][] b, int[][] key, int n, int m, int y, int x) {
        int e = b.length;
        int[][] temp = new int[e][e];
        
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < e; j++) {
                if (i - y >= 0 && i - y < n && j - x >= 0 && j - x < n)
                    temp[i][j] = b[i][j] + key[i - y][j - x];
                else
                    temp[i][j] = b[i][j];
            }
        }

        for (int i = n - 1; i < n - 1 + m; i++) {
            for (int j = n - 1; j < n - 1 + m; j++) {
                if (temp[i][j] == 0 || temp[i][j] == 2) {
                    return false;
                }
                // System.out.print(temp[i][j] + " ");
            }
            // System.out.println();
        }
        return true;
    }
}