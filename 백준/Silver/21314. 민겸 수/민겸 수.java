import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringBuilder maxSb = new StringBuilder();
        StringBuilder minSb = new StringBuilder();

        int i = 0;
        int mCountI = 0;
        int mCountJ = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == 'M') {
                mCountI++;
                mCountJ++;
            } else if (c == 'K') {

                maxSb.append("5")
                        .append("0".repeat(mCountI));


                if (mCountJ != 0) {
                    minSb.append("1")
                            .append("0".repeat(mCountJ - 1));
                }
                minSb.append("5");

                mCountI = 0;
                mCountJ = 0;
            }
            i++;
        }

        if (mCountI != 0) {
            maxSb.append("1".repeat(mCountI));
        }

        if (mCountJ != 0) {
            minSb.append("1")
                    .append("0".repeat(mCountJ - 1));
        }


        System.out.println(maxSb);
        System.out.println(minSb);
    }
}
