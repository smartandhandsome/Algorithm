import java.util.*;

class Solution {
    
    private final Set<Integer> primes = new HashSet<>();
    
    public int solution(String numbers) {
        
        dfs(numbers, "", new boolean[numbers.length()]);
        
        return primes.size();
    }
    
    public void dfs(String numbers, String number, boolean[] vistied) {
        if(!number.equals("")) {
            int iNumber = Integer.parseInt(number);
            if (isPrime(iNumber)) {
                primes.add(iNumber);
            }
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if (vistied[i]) {
                continue;
            }
            vistied[i] = true;
            dfs(numbers, number + numbers.charAt(i), vistied);
            vistied[i] = false;
        }
    }
    
    public boolean isPrime(int number) {
        if (number == 0 || number == 1) {
            return false;
        }
        int half = (int) Math.sqrt(number);
        for (int i = 2; i < half + 1; i++) {
            if (number % i == 0) {
                return false;
            } 
        }
        return true;
    }
}