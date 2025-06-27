import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        Queue<Job> jobQ = new PriorityQueue<>(
                Comparator.comparingInt(job -> job.time)
        );
        for (int i = 0; jobs.length > i; i++) {
            int[] job = jobs[i];
            Job j = new Job();
            j.time = job[0];
            j.size = job[1];
            j.index = i;
            jobQ.add(j);
        }

        Queue<Job> workQ = new PriorityQueue<>(
                Comparator.comparingInt((Job job) -> job.size)

                        .thenComparingInt(job -> job.time)
                        .thenComparingInt(job -> job.index)
        );
        int current = 0;
        while (!workQ.isEmpty() || !jobQ.isEmpty()) {
            
            while (jobQ.peek() != null && jobQ.peek().time <= current) {
                workQ.add(jobQ.poll());
            }
            
            if (!workQ.isEmpty()) {
                Job job = workQ.poll();
                current += job.size;
                answer += current - job.time;
            } else {
                current++;
            }
        }

        return answer / jobs.length;
    }

    static class Job {
        int time;
        int size;
        int index;
    }
}
