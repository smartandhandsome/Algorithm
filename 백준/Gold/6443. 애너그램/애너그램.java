import java.io.*;
import java.util.*;

public class Main {
    static String S;
    static int L;
    static int[] cnt;
    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            S = br.readLine();
            L = S.length();
            cnt = new int[26];
            for (int l = 0; l < L; l++) {
                cnt[S.charAt(l) - 'a']++;
            }
            answer = new ArrayList<>();
            dfs(new StringBuilder());
            for (String ans : answer) {
                bw.write(ans);
                bw.write("\n");
            }
            bw.flush();
        }
        bw.close();
    }

    public static void dfs(StringBuilder anagram) {
        if (anagram.length() == L) {
            answer.add(anagram.toString());
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                anagram.append((char) (i + 'a'));
                dfs(anagram);
                anagram.deleteCharAt(anagram.length() - 1);
                cnt[i]++;
            }
        }
    }
}