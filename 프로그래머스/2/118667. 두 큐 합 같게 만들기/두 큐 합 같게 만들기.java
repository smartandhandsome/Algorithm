import java.util.Arrays;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int[] total = new int[queue1.length + queue2.length];
        int n = total.length;
        
        System.arraycopy(queue1, 0, total, 0, queue1.length);
        System.arraycopy(queue2, 0, total, queue2.length, queue2.length);

        int index1 = 0;
        int index2 = queue1.length;

        long sum1 = Arrays.stream(queue1)
                .reduce(0, Integer::sum);
        long sum2 = Arrays.stream(queue2)
                .reduce(0, Integer::sum);
        int answer = 0;
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                sum1 -= total[index1];
                sum2 += total[index1];
                index1++;
                index1 %= n;
            } else {
                sum1 += total[index2];
                sum2 -= total[index2];
                index2++;
                index2 %= n;
            }
            answer++;
            if (answer >= (n / 2) * 3) {
                return -1;
            }
        }

        return answer;
    }
}
