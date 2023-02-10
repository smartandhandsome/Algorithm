
import java.util.LinkedList;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        LinkedList<Integer> linkedList = new LinkedList<>();
        long[] factorial = new long[n+1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            linkedList.add(i);
            factorial[i] = factorial[i - 1] * i;
        }
        long temp = k - 1;
        int idx = 0;
        for (int i = n - 1; i >= 0; i--) {
            answer[idx] = linkedList.get((int) (temp / factorial[i]));
            linkedList.remove((int) (temp / factorial[i]));
            temp %= factorial[i];
            idx++;
        }
        return answer;
    }
}