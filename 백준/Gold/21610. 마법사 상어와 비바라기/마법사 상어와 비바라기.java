import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] diagonals = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static ArrayList<int[]> clouds;
    static ArrayList<int[]> mark;
    static int[][] board;
    static int N;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        clouds = new ArrayList<>();
        clouds.add(new int[]{N-2, 0});
        clouds.add(new int[]{N-2, 1});
        clouds.add(new int[]{N-1, 0});
        clouds.add(new int[]{N-1, 1});
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            moveAndRain(d, s);
            copyWater();
            searchCloud();
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += board[i][j];
            }
        }
        System.out.println(answer);
    }
    
    public static void moveAndRain(int d, int s) {
        int[] direction = directions[d];
        for (int i = 0 ; i < clouds.size(); i++) {
            int[] location = clouds.get(i);
            location[0] = (location[0] + s * direction[0] + (N * 50)) % N;
            location[1] = (location[1] + s * direction[1] + (N * 50)) % N;
            board[location[0]][location[1]] += 1;
        }
    }
    
    public static void copyWater() {
        for (int i = 0 ; i < clouds.size(); i++) {
            int[] location = clouds.get(i);
            int cnt = 0;
            for (int[] diagonal: diagonals) {
                int y = location[0] + diagonal[0];
                int x = location[1] + diagonal[1];
                if (0 <= y && y < N && 0 <= x && x < N && board[y][x] > 0) {
                    cnt++;
                }
            }
            board[location[0]][location[1]] += cnt;
        }
    }
    
    public static void searchCloud() {
        mark = (ArrayList<int[]>)clouds.clone();
        clouds.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] >= 2) {
                    int[] temp = new int[] {i, j};
                    boolean flag = false;
                    for (int k = 0; k < mark.size(); k++){
                        if (Arrays.equals(temp, mark.get(k))) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                    board[i][j] -= 2; 
                    clouds.add(temp);
                }
            }
        }
    }
}