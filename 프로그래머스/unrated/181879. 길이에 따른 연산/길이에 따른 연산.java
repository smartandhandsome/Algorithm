import java.util.Arrays;

class Solution {
    public int solution(int[] num_list) {
        return num_list.length < 11 ?
                Arrays.stream(num_list).reduce(1, (left, right) -> left * right) :
                Arrays.stream(num_list).sum();
    }
}
