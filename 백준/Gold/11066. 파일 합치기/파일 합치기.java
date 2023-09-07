import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int count = 0; count < T; count++) {
            int numberOfChapter = Integer.parseInt(br.readLine());
            int[] chapterWeight = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] sum = new int[numberOfChapter];
            sum[0] = chapterWeight[0];
            IntStream.range(1, numberOfChapter)
                    .forEach(operand -> sum[operand] = chapterWeight[operand] + sum[operand - 1]);

            int[][] dp = new int[numberOfChapter][numberOfChapter];
            for (int size = 1; size < numberOfChapter; size++) {
                for (int start = 0; start + size < numberOfChapter; start++) {
                    int end = start + size;
                    dp[start][end] = Integer.MAX_VALUE;
                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(
                                dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + sum[end] - (start < 1 ? 0 : sum[start - 1])
                        );
                    }
                }
            }
            System.out.println(dp[0][numberOfChapter - 1]);
        }
    }

}
