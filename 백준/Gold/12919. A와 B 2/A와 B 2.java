import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        // add A on last;
        // add B on last and then reverse String
        Queue<String> q = new LinkedList<>();
        q.offer(T);
        int answer = 0;
        while (!q.isEmpty()) {
            String st = q.poll();
            int N = st.length();
            if (st.equals(S)) {
                answer = 1;
                break;
            } else if (N < S.length()){
                break;
            }
            if (st.charAt(N - 1) == 'A') {
                q.offer(st.substring(0, N - 1));
            }
            if (st.charAt(0) == 'B') {
                q.offer(new StringBuilder(st.substring(1, N)).reverse().toString());
            }
        }
        System.out.println(answer);
    }
}