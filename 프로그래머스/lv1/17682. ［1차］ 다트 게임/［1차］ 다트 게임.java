import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String dartResult) {
        int[] nums = new int[3];
        Map<String, Integer> map = new HashMap<>(
            Map.of(
                    "S", 1,
                    "D", 2,
                    "T", 3
            )
        );
        Matcher m = Pattern.compile("([0-9]+)([S|D|T])([#|*]?)").matcher(dartResult);
        int idx = 0;
        while (m.find()) {
            int num = Integer.parseInt(m.group(1));
            int pow = map.get(m.group(2));
            nums[idx] = (int) Math.pow(num, pow);
            if (m.group(3).equals("#")) {
                nums[idx] *= -1;
            }
            if (m.group(3).equals("*")) {
                nums[idx] *= 2;
                if (idx >= 1) {
                    nums[idx - 1] *= 2;
                }
            }
            idx++;
        }
        return nums[0] + nums[1] + nums[2];
    }
}