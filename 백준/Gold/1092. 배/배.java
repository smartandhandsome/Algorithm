import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> cran = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cran.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(cran, Collections.reverseOrder());
        
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(box, Collections.reverseOrder());
        
        int answer = 0;
        if (cran.get(0) < box.get(0)){
            answer = -1;    
        } else {
            while (!box.isEmpty()) {
                int boxNum = 0;
                int craneNum = 0;
                while (craneNum < cran.size() && boxNum < box.size()) { // 1 turn
                    if (cran.get(craneNum) >= box.get(boxNum)) {
                        box.remove(boxNum);
                        craneNum++;
                    } else {
                        boxNum++;
                    }
                }
                answer++;
            }
        }
        System.out.println(answer);
    }
}