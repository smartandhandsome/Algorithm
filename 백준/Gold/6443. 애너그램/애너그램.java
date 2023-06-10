import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            Arrays.sort(c);
            do {
                sb.append(c).append("\n");
            } while (nextPermutation(c));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean nextPermutation(char[] c) {
        int i = c.length - 1;
        while (i > 0 && c[i] <= c[i - 1]) {
            i--;
        }
        if (i == 0) {
            return false;
        }
        int j = c.length - 1;
        while (c[i - 1] >= c[j]) {
            j--;
        }
        swap(c, i-1, j);

        int k = c.length - 1;
        while (i <= k) {
            swap(c, i++, k--);
        }
        return true;
    }

    public static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}