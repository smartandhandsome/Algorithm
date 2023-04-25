import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            map.put(sushi[i], 0);
        }
        
        map.put(c, 1);
        int temp = 1;
        for (int i = 0 ; i < k; i++) {
            if (map.get(sushi[i]) == 0) {
                temp++;
            } 
            map.put(sushi[i], map.get(sushi[i]) + 1);
        }
        int answer = temp;
        // System.out.println(temp+ " " +map);
        int idx = k;
        while (idx < N + k) {
            if (map.get(sushi[idx % N]) == 0){
                temp++;
            }
            map.put(sushi[idx % N], map.get(sushi[idx % N]) + 1);
            map.put(sushi[idx - k], map.get(sushi[idx - k]) - 1);
            if (map.get(sushi[idx - k]) == 0) {
                temp--;
            }
            // System.out.println(temp+ " " +map);
            answer = Math.max(answer, temp);
            idx++;
        }
        System.out.println(answer); 
    }
}