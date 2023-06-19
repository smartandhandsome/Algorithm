import java.util.*;

class Solution {
    static Map<Character, Integer> map = new HashMap<>(
            Map.of('S', 1, 'D', 2, 'T', 3)
    );

    public int solution(String dartResult) {
        int answer = 0;
        int index = 0;
        int N = dartResult.length();

        List<Integer> scores = new ArrayList<>();
        while (index < N) {
            int score = dartResult.charAt(index) - '0';
            index++;
            if (score == 1 && index < N && dartResult.charAt(index) == '0') {
                score = 10;
                index++;
            }
            int pow = map.get(dartResult.charAt(index));
            index++;
            int cur = (int) Math.pow(score, pow);
            if (index < N) {
                if (dartResult.charAt(index) == '#') {
                    cur *= -1;
                    index++;
                } else if (dartResult.charAt(index) == '*') {
                    cur *= 2;
                    if (scores.size() > 0) {
                        scores.set(scores.size() - 1, scores.get(scores.size() - 1) * 2);
                    }
                    index++;
                }
            }
            scores.add(cur);
        }
        for (Integer score : scores) {
            answer += score;
        }
        return answer;
    }
}