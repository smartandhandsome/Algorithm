import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int left = 0;
        int right = str.length() - 1;
        HashSet<Character> alpha = new HashSet<>();
        int answer = -1;
        while (left <= right) {
            char l = str.charAt(left);
            char r = str.charAt(right);
            if (l != r) {
                answer = str.length();
                break;
            }
            alpha.add(l);
            alpha.add(r);
            left++;
            right--;
        }
        
        if (answer == -1) {
            if (alpha.size() == 1) {
                answer = -1;
            } else {
                answer = str.length() - 1;
            }
        }
        System.out.println(answer);
    }
}