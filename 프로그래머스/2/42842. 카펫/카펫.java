class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        // 최소 3x3
        for (int row = 1; row < Math.sqrt(yellow) + 1; row++) {
            if (yellow % row == 0) {
                int col = yellow / row;
                 System.out.println(row + ", " + col);
                if (brown == (row + 1) * 2 +  (col + 1) * 2) {
                    return new int[]{col + 2, row + 2};
                }
            }
        }
        
        return answer;
    }
}