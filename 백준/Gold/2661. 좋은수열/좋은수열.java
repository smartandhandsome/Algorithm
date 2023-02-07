import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String answer = addNum("");
        System.out.println(answer);
    }

    public static String addNum(String answer) {
        if (answer.length() == n) {
            System.out.print(answer);
            System.exit(0);
        }
        char[] nums = {'1', '2', '3'};
        for (int i = 0; i < 3; i++) {
            if (checkDuplication(answer, nums[i])) {
                addNum(answer + nums[i]);
            }
        }
        return "";
    }

    public static boolean checkDuplication(String s, char c) {
        String tmp = s + c;
        int length = tmp.length();
        for (int i = length / 2; i > 0; i--) {
            if (tmp.substring(length - i).equals(tmp.substring(length - 2 * i, length - i))) {
                return false;
            }
        }
        return true;
    }
}
