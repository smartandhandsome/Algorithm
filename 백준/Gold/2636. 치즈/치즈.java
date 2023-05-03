import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] maps = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int check = -1;
        int count = 0;
        while(true){
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0});
            boolean[][] visited = new boolean[H][W];
            boolean flag = false;
            int temp = 0;           
            
            while(!q.isEmpty()) {
                int[] coor = q.poll();
                for (int i = 0; i < 4; i++) {
                    int newY = coor[0] + directions[i][0];
                    int newX = coor[1] + directions[i][1];
                    if (newY < 0 || newY >= H || newX < 0 || newX >= W) {
                        continue;
                    }
                    
                    if (maps[newY][newX] > check && maps[newY][newX] <= 0) {
                        if (!visited[newY][newX]) {
                            visited[newY][newX] = true;
                            q.offer(new int[] {newY, newX});   
                        }
                    } else if (maps[newY][newX] == 1) {
                        temp++;
                        maps[newY][newX] = check;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                break;
            }
            count = temp;
            check -= 1;
        }
        
        System.out.println(-(check + 1));
        System.out.println(count);
    }
}