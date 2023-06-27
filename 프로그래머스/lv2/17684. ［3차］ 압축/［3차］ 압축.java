
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        List<Integer> answers = new ArrayList<>();

        int answer = 0;
        int index = 0;
        while (index < msg.length()) {
            String s = "";
            int p = 1;
            answer = 0;
            while (index + p <= msg.length()) {
                String substring = msg.substring(index, index + p);
                if (map.containsKey(substring)) {
                    answer = map.get(substring);
                    p++;
                } else {
                    map.put(substring, map.size() + 1);
                    answers.add(answer);
                    break;
                }
            }
            index += (p - 1);
        }
        answers.add(answer);

        return answers.stream().mapToInt(value -> (int) value).toArray(); 
    }
}

