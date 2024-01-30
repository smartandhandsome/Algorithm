import java.util.*;

class Solution {
    static Map<Character, Integer> score ;
    
    static {
        score = new HashMap<>();
        score.put('R', 0);
        score.put('T', 0);
        score.put('C', 0);
        score.put('F', 0);
        score.put('J', 0);
        score.put('M', 0);
        score.put('A', 0);
        score.put('N', 0);
    }
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] < 4) {
                char c = survey[i].charAt(0);
                score.put(c, score.get(c) + 4 - choices[i]);
            } else if (choices[i] > 4) {
                char c = survey[i].charAt(1);
                score.put(c, score.get(c) + choices[i] - 4);
            }
        }
        answer += score.get('R') >= score.get('T') ? 'R' : 'T';
        answer += score.get('C') >= score.get('F') ? 'C' : 'F';
        answer += score.get('J') >= score.get('M') ? 'J' : 'M';
        answer += score.get('A') >= score.get('N') ? 'A' : 'N';
        return answer;
    }
} 