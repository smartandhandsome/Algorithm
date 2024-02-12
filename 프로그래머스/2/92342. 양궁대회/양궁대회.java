import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int[] answer = {-1};
    static int maxV = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        for (int i = 10; i >= 1; i--) {
            if (n < i) {
                continue;
            }
            dfs(10, new ArrayList<>(), i, info, n);
        }
        return answer;
    }

    public static void dfs(int idx, List<Integer> lion, int max, int[] info, int n) {
        if (lion.size() == max) {            
            int count = 0;
            int[] answer = new int[11];
            for (int s : lion) {
                int index = 10 - s;
                answer[index] = info[index] + 1;
                count += info[index] + 1;
                if (count > n) {
                    return;
                }
            }
            
            if (count != n) {
                answer[10] += n - count;
            }

            int ls = 0;
            int as = 0;
            for (int i = 0; i < 11; i++) {
                if (info[i] >= answer[i]) {
                    if (info[i] == 0) {
                        continue;
                    }
                    as += 10 - i;
                } else {
                    ls += 10 - i;
                }
            }
            if (ls > as && ls - as > maxV) {
                System.out.println(ls + " " + as);
                System.out.println(lion);
                maxV = ls - as;
                Solution.answer = answer;
            }
            return;
        }

        for (int i = idx; i >= 1; i--) {
            lion.add(i);
            dfs(i - 1, lion, max, info, n);
            lion.remove((Integer) i);
        }
    }
}
