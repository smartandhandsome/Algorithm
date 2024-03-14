import java.util.*;
import java.io.*;


public class Main {
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            dfs(N, 2, "1");
            System.out.println();
        }
    }
    
    public static void dfs(int N, int num, String exp) {
        if (N == num - 1) {
            if (calc(exp) == 0) {
                System.out.println(exp);
            }
            return;
        }
        dfs(N, num+1, exp + " " + num);
        dfs(N, num+1, exp + "+" + num);
        dfs(N, num+1, exp + "-" + num);
    }
    
    public static int calc(String exp) {
        String temp = exp.replace(" ", "");
        
        StringTokenizer st = new StringTokenizer(temp, "[+, -]", true);
        
        int sum = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.equals("+")) {
                sum += Integer.parseInt(st.nextToken());
            } else {
                sum -= Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(exp + " = " + sum);
        return sum;
    }
}
