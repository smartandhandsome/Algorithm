import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Node> min = new PriorityQueue<>(Comparator.comparingInt(node -> node.num));
        Queue<Node> max = new PriorityQueue<>(Comparator.comparingInt(node -> -node.num));
        
        for (String operation : operations) {
            String[] op = operation.split(" ");
            String comm = op[0];
            int num = Integer.parseInt(op[1]);
            if (comm.equals("I")) {
                Node node = new Node(num);
                max.offer(node);
                min.offer(node);
            } else if (comm.equals("D")) {
                if (num == -1) {
                    remove(min, true);
                } else if (num == 1) {
                    remove(max, true);
                }
            }
        }
         
        return new int[] {remove(max, false), remove(min, false)};
    }
    static class Node {
        int num;
        boolean isDeleted;
        
        public Node(int num) {
            this.num = num;
            this.isDeleted = false;
        }
    }
    
    public int remove(Queue<Node> q, boolean delete) {
        while(q.peek() != null && q.peek().isDeleted) {
            q.poll();
        }

        if (!q.isEmpty()) {
            Node node = q.poll();
            if (delete) {
                node.isDeleted = true;
            }
            return node.num;
        }
        return 0;
    }
}