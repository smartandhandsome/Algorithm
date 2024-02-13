
class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        
        // 아이디는 소문자, 숫자, -, _, . 만 가능
        // . 처음x, 끝x, 연속x
        
        // 1단계
        answer = answer.toLowerCase(); 
        
        // 2단계
        answer = answer.replaceAll("[^a-z0-9._\\-]", "");
        
        // 3단계
        answer = answer.replaceAll("\\.{1,}", ".");
        
        // 4단계
        if (answer.length() != 0 && answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        
        if (answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }
        
        if (answer.isBlank()) {
            answer = "a";
        }
        
        if (answer.length() >= 16) {
            if (answer.charAt(14) == '.') {
                answer = answer.substring(0, 14);
            } else {
                answer = answer.substring(0, 15);
            }
        }
        
        if (answer.length() <= 2) {
            String s = answer.charAt(answer.length() - 1) + "";
            answer = answer + s.repeat(3 - answer.length());
        }
        
        return answer;
    }
}