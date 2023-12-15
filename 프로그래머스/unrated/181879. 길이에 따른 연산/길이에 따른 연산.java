import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public int solution(int[] num_list) {
        AtomicInteger answer = new AtomicInteger();
        if (num_list.length < 11) {
            AtomicInteger a = new AtomicInteger(1);
            Arrays.stream(num_list)
                    .forEach(value -> a.updateAndGet(v -> v * value));
            answer.set(a.get());
        } else {
            Arrays.stream(num_list)
                    .forEach(answer::addAndGet);
        }
        return answer.get();
    }
}