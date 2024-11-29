import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(br.readLine());

        int count = 0;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (num == v) {
                count++;
            }
        }
        System.out.println(count);
    }
}