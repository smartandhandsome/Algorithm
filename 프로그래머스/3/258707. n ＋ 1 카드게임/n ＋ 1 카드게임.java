import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        // 1 ~ n 사이의 수가 적힌 카드, 동전 coin개
        // n/3장을 뽑아 가진다. (n은 6의 배수)
        
        // 라운드 시작 시 카드 두 장 뽑음, if 카드 뭉치 남은 카드 없다면? 게임 종료
        // 받은 카드 가지려면 동전 소모 or 버림
        
        // 카드 합이 n+1인 카드 두장을 내고 다음라운드 진출 if 낼 카드 2장 없으면? 게임 종료
        
        int n = cards.length;
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[2])); // int[3] {카드번호1, 카드번호2, 가격}
        for (int i = 0; i < n / 3; i++) {
            visited[cards[i]] = 0;
            if (visited[n + 1 - cards[i]] != -1) {
                q.add(new int[] {cards[i], n + 1 - cards[i], 0});
            }
        }
        
        int index = n / 3;
        int round = 1;
        while(index <= n - 1) {
            visited[cards[index]] = 1;
            if (visited[n + 1 - cards[index]] != -1) {
                q.add(new int[] {cards[index], n + 1 - cards[index], visited[n + 1 - cards[index]] + 1});
            }
            
            visited[cards[index + 1]] = 1;
            if (visited[n + 1 - cards[index + 1]] != -1) {
                q.add(new int[] {cards[index + 1], n + 1 - cards[index + 1], visited[n + 1 - cards[index + 1]] + 1});
            }
            if (q.size() == 0) {
                break;
            }
            int[] poll = q.poll();
            
            
            if (coin < poll[2]) {
                break;
            } else {
                coin -= poll[2];
            }
            
            round+=1;
            index+=2;
        }
        
        return round;
    }
}