import java.util.*;
import java.io.*;

public class Main {
    static int[][] board;
    static int airCleaner;
    static int R;
    static int C;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        board = new int[R][C];
        airCleaner = -1; // air cleaner is on only column 1, biggest row number
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    airCleaner = i;
                }
            }
        }
        
        while(T-- > 0) {
            spread();
            action(true);
            action(false);
        }
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += board[i][j];
                // System.out.printf("%3d", board[i][j]);
            }
            // System.out.println();
        }
        System.out.println(answer + 2);
    }
    
    public static void spread() {
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] >= 5) {
                    int q = board[i][j] / 5;
                    int cnt = 0;
                    for (int[] direction : directions) {
                        int y = i + direction[0];
                        int x = j + direction[1];
                        if (0 <= y && y < R && 0 <= x && x < C && board[y][x] != -1) {
                            temp[y][x] += q;
                            cnt++;
                        }
                    }
                    temp[i][j] += board[i][j] - q * cnt;
                } else {
                    temp[i][j] += board[i][j];
                }
            }
        }
        board = temp.clone();
    }
    
    public static void action(boolean up) {
        int[] ud = up ? new int[] {airCleaner - 1, 0} : new int[] {airCleaner, 0};
        int[][] directions = up ? new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}} : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] boundary = up ? new int[] {0, ud[0] + 1, 0, C} : new int[] {ud[0], R};
        
        
        int[] location = ud.clone();
        int d = 0;
        int v = 0;
        while(true) {
            location[0] += directions[d][0];
            location[1] += directions[d][1];
            if (location[0] < boundary[0] || location[0] >= boundary[1] || location[1] < 0 || location[1] >= C) {
                location[0] -= directions[d][0];
                location[1] -= directions[d][1];
                d++;
                continue;
            }
            if (Arrays.equals(location, ud)) {
                break;
            }
            int temp = v;
            v = board[location[0]][location[1]];
            board[location[0]][location[1]] = temp;
        }
        
    }
}