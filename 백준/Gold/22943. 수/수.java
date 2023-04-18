import java.io.*;
import java.util.*;

public class Main {
    static boolean[] primes; 
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int start = (int) Math.pow(10, K - 1);
        int end = (int) Math.pow(10, K);
        primes = new boolean[end];
        Arrays.fill(primes, true);
        getPrime(end);
        int cnt = 0;
        
        if (M < 9){
            System.out.println(cnt);
            System.exit(0);
        }
        for (int i = start; i < end; i++) {
            if (!checkNumber(i)) {
                continue;
            }   
            boolean flag1 = false;
            boolean flag2 = false;
            // System.out.println("standard: " + i);
            for (int p = 2; p < i / 2 + 1 ; p++){
                if (!primes[p]){
                    continue;
                }
                // System.out.println("prime: " + p);
                if (!flag1) {
                    flag1 = first(i, p);
                }
                if (!flag2){
                    flag2 = second(i, p, M);
                }
                
                if (flag1 && flag2){
                    cnt++;
                    break;
                }
            }
            
        }
        System.out.print(cnt);
    }
    
    public static boolean first(int num, int prime){
        if (prime != (num - prime) && primes[num - prime]){
            return true;
        }
        return false;
    }
    
    public static boolean second(int num, int prime, int M) {
        int temp = num;
        while (temp % M == 0) {
            temp /= M;
        }
        if (temp % prime == 0 && primes[temp/prime]){
            return true;
        }
        return false;
    }
    
    public static boolean checkNumber(int num) {
        String s = String.valueOf(num);
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return s.length() == set.size();
    }
    

	public static void getPrime(int num) {
	    primes[0] = false;
	    primes[1] = false;
		for (int i = 2; i*i < num; i++){
		    if (primes[i]) {
		        for (int j = i * i; j < num; j+=i) {
		            primes[j] = false;
		        }
		    }
		}
	}
}
