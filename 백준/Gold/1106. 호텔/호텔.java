import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[] price = new int[N];
        int[] people = new int[N];
        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            price[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N][C + 1];
        for (int i = 0; i < N; i++) {
            int money = price[i];
            int get = people[i];
            for(int j = 1; j <= C; j++) {
                int cnt = j / get;
                if (j % get != 0) {
                    cnt++;
                }
                int next = j - get;
                if (i == 0) dp[i][j] = cnt * money;
                else dp[i][j] = Math.min((j - get >= 0 ? dp[i][j - get] : 0) + money, dp[i - 1][j]);
            }
        }
        
        System.out.println(dp[N-1][C]);
    }
}