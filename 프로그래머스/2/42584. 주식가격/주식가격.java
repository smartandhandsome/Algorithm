import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        ArrayDeque<Node> st = new ArrayDeque<>();
        st.push(new Node(prices[0], 0));
        for (int i = 1; i < prices.length; i++) {
            // System.out.println("prices[" + i + "]: " + prices[i]);
            while (!st.isEmpty() && prices[i] < st.peek().price) {
                Node node = st.pop();
                // System.out.println("Node: " + node.price + ", " + node.time);
                answer[node.time] = i - node.time;
            }
            st.push(new Node(prices[i], i));
        }
        
        while (!st.isEmpty()) {
            Node node = st.pop();
            answer[node.time] = (prices.length - 1) - node.time;
        }
        
        return answer;
    }
    
    static class Node {
        int price; int time;
        
        public Node(int price, int time) {
            this.price = price;
            this.time = time;
        }
    }
}