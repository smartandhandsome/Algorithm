import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waiting = new ArrayDeque<>();
        for (int truck_weight : truck_weights) {
            waiting.add(truck_weight);
        }
        
        int time = 0;
        Queue<int[]> bridge = new ArrayDeque<>();
        int currentWeight = 0;
        while(!waiting.isEmpty() || !bridge.isEmpty()) {
            // System.out.println("bridge: " + bridge + ", waiting: " + waiting);
            if (!bridge.isEmpty()) {
                int[] data = bridge.peek();
                if (data[1] + bridge_length <= time) {
                    currentWeight -= bridge.poll()[0];
                }
            }
            
            if (!waiting.isEmpty()) {
                int w = waiting.peek();
                if (weight >= currentWeight + w && bridge.size() < bridge_length) {
                    bridge.add(new int[] {waiting.poll(), time});
                    currentWeight += w;
                }
            }
            time++;
        }
        
        return time;
    }
}