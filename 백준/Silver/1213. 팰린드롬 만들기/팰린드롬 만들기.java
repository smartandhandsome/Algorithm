import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hanSoo = br.readLine();
        int[] alphabet = new int[26];
        for (char c : hanSoo.toCharArray()) {
            alphabet[c - 'A']++;
        }
        int odd = 0;
        int center = -1;
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] % 2 == 1) {
                odd++;
                center = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (odd > 1) {
            sb.append("I'm Sorry Hansoo");
        } else {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < alphabet[i] / 2; j++) {
                    sb.append((char) (i + 'A'));
                }
            }
            String rev = new StringBuilder(sb).reverse().toString();
            if (center != -1) {
                sb.append((char) ('A' + center));
            }
            sb.append(rev);
        }
        System.out.println(sb);
    }
}