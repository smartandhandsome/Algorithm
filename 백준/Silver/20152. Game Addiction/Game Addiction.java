import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        int H = Integer.parseInt(s[0]);
        int N = Integer.parseInt(s[1]);
        
        int dist = Math.abs(H - N) + 1;
        long[][] map = new long[dist][dist];
        
        Arrays.fill(map[0], 1);
        for (int i = 1; i < dist; i++) {
            for (int j = i; j < dist; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        System.out.println(map[dist-1][dist-1]);
    }
}