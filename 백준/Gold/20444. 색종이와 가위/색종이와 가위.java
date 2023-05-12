import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        
        long left = 0;
        long right = n / 2;
        String answer = "NO";
        while(left <= right) {
            long row = (left + right) / 2;
            long col = n - row;
 
            long total = (row+1) * (col+1);
            if(total == k) {
                answer = "YES";
                break;
            } else if(total > k) {
                right = row - 1;
            } else if (total < k){
                left = row + 1;
            }  
        }
        
        System.out.println(answer);
    }
}