import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        if (N < K) {
            System.out.println(0);
            System.exit(0);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] diff = new int[N-1];
        Arrays.sort(dist);
        for (int i = 0 ; i < N - 1; i++) {
            diff[i] = dist[i+1] - dist[i];
        }
        
        Arrays.sort(diff);
        int answer = 0;
        for (int a : Arrays.copyOfRange(diff, 0, N-K)) {
            answer += a;
        }
        System.out.println(answer);
    }
}