import java.util.*;

class Solution {
    static final Lang LANG = new Lang();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (String i : info) {
            String[] data = i.split(" ");
            LANG.get(data[0]).get(data[1]).get(data[2]).get(data[3]).add(Integer.parseInt(data[4]));
        }
        
        for(int i = 0; i < query.length; i++) {
            String[] data = query[i].split(" ");
            answer[i] = LANG.get(data);
        }
        
        return answer;
    }
    
    static class Lang { // 0
        Map<String, Part> values;
        
        Lang() {
            values = new HashMap<>();
            values.put("cpp", new Part());
            values.put("java", new Part());
            values.put("python", new Part());
        }
        
        Part get(String data) {
            return values.get(data);
        }
        
        
        int get(String[] data) {
            if (data[0].equals("-")) {
                return values.get("cpp").get(data) + values.get("java").get(data) + values.get("python").get(data);
            }
            return values.get(data[0]).get(data);
        }
    }
    
    static class Part {  // 2
        Map<String, Career> values;
        
        Part() {
            values = new HashMap<>();
            values.put("backend", new Career());
            values.put("frontend", new Career());
        }
        
        Career get(String data) {
            return values.get(data);
        }
        
        int get(String[] data) {
            if (data[2].equals("-")) {
                return values.get("backend").get(data) + values.get("frontend").get(data);
            }
            return values.get(data[2]).get(data);
        }
    }
    
    static class Career {  // 4
        Map<String, Food> values;
        
        Career() {
            values = new HashMap<>();
            values.put("junior", new Food());
            values.put("senior", new Food());
        }
        
        Food get(String data) {
            return values.get(data);
        }
        
        int get(String[] data) {
            if (data[4].equals("-")) {
                return values.get("junior").get(data) + values.get("senior").get(data);
            }
            return values.get(data[4]).get(data);
        }
    }
    
    static class Food { // 6
        Map<String, Score> values;
        
        Food() {
            values = new HashMap<>();
            values.put("chicken", new Score());
            values.put("pizza", new Score());
        }
        
        Score get(String data) {
            return values.get(data);
        }
        
        int get(String[] data) {
            if (data[6].equals("-")) {
                return values.get("chicken").size(Integer.parseInt(data[7])) + values.get("pizza").size(Integer.parseInt(data[7]));
            }
            return values.get(data[6]).size(Integer.parseInt(data[7]));
        }
    }
    
    static class Score { // 7
        List<Integer> values = new ArrayList<>();
        boolean isSorted = false;
        
        void add(int score) {
            values.add(score);
        }
        
        int size(int score) {
            if (!isSorted) {
                Collections.sort(values);
                isSorted = true;
            }
            int s = 0;
            int e = values.size() - 1;
            while(s <= e) {
                int m = (s + e) / 2;
                
                if (score > values.get(m)) {
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
            return values.size() - s;
        }
    }
}