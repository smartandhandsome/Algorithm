import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(s);
        int start = 0;
        int end = N - 1;
        int MIN = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (start < end) {
            int a = s[start];
            int b = s[end];
            if (Math.abs(a + b) < MIN) {
                MIN = Math.abs(a + b);
                left = a;
                right = b;
            }
            if (a + b > 0) {
                end -= 1;
            } else if (a + b < 0){
                start += 1;
            } else {
                break;
            }
        }
        System.out.println(left + " " + right);
    }
}