import java.util.*;

class Solution {
    
    Map<Integer, Map<String, Integer>> map = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        
        for (int c : course) {
            map.put(c, new HashMap<>());
        }
        
        for (String order : orders) {
            seperate(order, course);
        }
        
        List<String> answer = new ArrayList<>();
        for (Map<String, Integer> m : map.values()) {
            int max = 2;
            List<String> st = new ArrayList<>();
            for (Map.Entry<String, Integer> e : m.entrySet()) {
                if (max < e.getValue()) {
                    st = new ArrayList<>();
                    st.add(e.getKey());
                    max = e.getValue();
                } else if (max == e.getValue()) {
                    st.add(e.getKey());
                }
                
            }
            answer.addAll(st);
        }
        
        return answer.stream()
            .sorted()
            .toArray(String[]::new);
    }
    
    public void seperate(String s, int[] course) {
        for (int c : course) {
            dfs(s, "", c, 0);
        }
    }
    
    public void dfs(String s, String custom, int max, int index) {
        if (custom.length() == max) {
            char[] chars = custom.toCharArray();
            Arrays.sort(chars);
            String temp = String.valueOf(chars);
            Map<String, Integer> m = map.get(max);
            m.put(temp, m.getOrDefault(temp, 0) + 1);
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            dfs(s, custom + s.charAt(i), max, i + 1);
        }
        
    }
}