import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Meet[] meets = new Meet[N + 1];
        int[] dp = new int[N + 1];
        StringTokenizer st;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            meets[i] = new Meet(T, P);
        }
        meets[N] = new Meet();
        
        int payGood = 0;
        for (int i = 0; i <= N; i++) {
            payGood = Math.max(payGood, dp[i]);
            int f = meets[i].T + i;
            if (f > N)
                continue;
            dp[f] = Math.max(meets[i].P + payGood, dp[f]);
        }
        
        System.out.println(dp[N]);
        // System.out.println(Arrays.toString(dp));
    }
}

class Meet {
    int T;
    int P;
    
    Meet() {
        T = 0;
        P = 0;
    }
    
    Meet(int T, int P) {
        this.T = T;
        this.P = P;
    }
}