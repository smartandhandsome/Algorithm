import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        int max = -1;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, A[i]);
        }

        long answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(A[0]);
        for (int i = 1; i < N; i++) {
            if (A[i] > stack.peek()) {
                answer += (A[i] - stack.pop());
                stack.push(A[i]);
            } else if (A[i] < stack.peek()) {
                stack.pop();
                stack.push(A[i]);
            }
        }
        answer += max - stack.pop();
        System.out.println(answer);
    }
}
