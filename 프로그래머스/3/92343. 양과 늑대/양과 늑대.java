import java.util.*;

class Solution {
    static int nodeN;

    public int solution(int[] info, int[][] edges) {
        nodeN = info.length;
        int answer = 1;
        // info 노드 최대 17개

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }

        // 탈출조건
        // 갈 수 있는 길이 늑대랑 동일한 수 밖에 없을 때

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(list.get(0)));

        while(!q.isEmpty()) {
            Node node = q.poll();
            answer = Math.max(node.s, answer);

            Iterator<Integer> it = node.set.iterator();
            while(it.hasNext()) {
                int next = it.next();
                // System.out.println(next);
                int target = info[next];

                if (target == 1) {
                    // 늑대일때
                    if (node.w + 1 >= node.s) {
                        // 늑대 수 같아지면 글로 안 감
                        continue;
                    }
                    q.add(new Node(next, node.w+1, node.s, node.set, node.n, list.get(next)));
                } else {
                    q.add(new Node(next, node.w, node.s+1, node.set, node.n, list.get(next)));
                }
            }
            // System.out.println();
        }
        return answer;
    }

    static class Node {
        int n;
        int w;
        int s;
        Set<Integer> set;

        Node(List<Integer> nexts) {
            n = 0;
            w = 0;
            s = 1;
            set = new HashSet<>();
            set.addAll(nexts);
        }

        Node(int n, int w, int s, Set<Integer> set, int now, List<Integer> nexts) {
            this.n = n;
            this.w = w;
            this.s = s;
            this.set = new HashSet(set);
            this.set.remove(n);
            this.set.addAll(nexts);
        }

    }

}
