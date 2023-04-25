import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] S = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }       
        
        int start = 0;
        int end = 0;
        int answer = 0;
        int cnt = 0;
        while (start < N && end < N) {
            if (cnt > K) {
                if (S[start] % 2 == 1) {
                    cnt--;
                }
                start++;
                continue;
            }
            
            if (S[end] % 2 == 1) {
                cnt++;
                if (cnt > K) {
                    answer = Math.max(answer, end - start - K);
                }
            }
            end++;
        }
        
        answer = Math.max(answer, end - start - cnt);
        System.out.print(answer);
    }
}