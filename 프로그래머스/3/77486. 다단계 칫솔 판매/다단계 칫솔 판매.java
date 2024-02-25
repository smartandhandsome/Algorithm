import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        
        Map<String, String> parents = new HashMap<>();
        Map<String, Integer> sales = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sales.put(enroll[i], 0);
            parents.put(enroll[i], referral[i]);
        }
        
        int m = seller.length;
        for (int i = 0; i < m; i++) {
            calc(parents, sales, seller[i], amount[i] * 100);
        }
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = sales.get(enroll[i]);
        }
        
        return answer;
    }
    
    static void calc(Map<String, String> parents, Map<String, Integer> sales, String sell, int total) {
        String name = sell;
        int money = total;
        sales.put(name, sales.get(name) + money);
        while(true){
            String p = parents.get(name);
            int ten = money / 10;
            
            sales.put(name, sales.get(name) - ten);
            if(p.equals("-") || ten == 0) {
                break;
            }
            sales.put(p, sales.get(p) + ten);
            
            name = p;
            money = ten;
        }
    }
}