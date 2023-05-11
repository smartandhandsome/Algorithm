import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] immi = new int[N];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            immi[i] = Integer.parseInt(br.readLine());
            min = Math.min(immi[i], min);
        }

        long letsgo = 0;
        long answer = 0;
        long bisearch = (long) min * M;

        while(letsgo <= bisearch) {
            long cnt = 0;
            long mid = (letsgo + bisearch) / 2;
            
            for (int i : immi) {
                cnt += mid / i;
            }
            
            if (cnt >= M) {
                answer = mid;
                bisearch = mid - 1;
            } else {
                letsgo = mid + 1;
            }
        }
        System.out.println(answer);
    }
}