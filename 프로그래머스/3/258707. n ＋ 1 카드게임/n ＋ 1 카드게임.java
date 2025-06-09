import java.util.*;

class Solution {
    
    Queue<Card[]> np1 = new PriorityQueue<>(Comparator.comparingInt(cards ->
            (cards[0].needCoin ? 1 : 0) + (cards[1].needCoin ? 1 : 0)));
    Card[] myCards;
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        myCards = new Card[n + 1];
        
        int index = 0;
        for (; index < n / 3; index++) {
            int card = cards[index];
            myCards[card] = new Card(card, false);
            
            if (myCards[n + 1 - card] != null) {
                np1.add(new Card[] {new Card(card, false),
                                    new Card(n + 1 - card, false)});
            }
        }
        
        int round = 1;
        while(index < n) {
            int card1 = cards[index++];
            myCards[card1] = new Card(card1, true);
            if (myCards[n + 1 - card1] != null) {
                np1.add(new Card[] {new Card(card1, true),
                                    myCards[n + 1 - card1]});
            }
            
            int card2 = cards[index++];
            myCards[card2] = new Card(card2, true);
            if (myCards[n + 1 - card2] != null) {
                np1.add(new Card[] {new Card(card2, true),
                                    myCards[n + 1 - card2]});
            }
            
            if (np1.isEmpty()) {
                break;
            }
            Card[] poll = np1.poll();
            if (poll[0].needCoin) {
                if (coin == 0) {
                    break;
                }
                coin--;
            }
            
            if (poll[1].needCoin) {
                if (coin == 0) {
                    break;
                }
                coin--;
            }
            
            round++;
        }
        return round;
    }
    
    
    static class Card {
        private final int number;
        private final boolean needCoin;
        
        public Card(int number, boolean needCoin) {
            this.number = number;
            this.needCoin = needCoin;
        }
    }
}














