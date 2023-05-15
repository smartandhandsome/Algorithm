import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      PriorityQueue<Word> q = new PriorityQueue<>();
      for (int i = 0; i < N; i++) {
          String s = br.readLine();
          q.offer(new Word(s));
      }
      while(!q.isEmpty()) {
          Word w = q.poll();
          System.out.println(w.word);
      }
    }
}

class Word implements Comparable<Word>{
    String[] chunks;
    String word;
    
    Word(String word) {
        this.word = word;
        List<String> temp = new ArrayList<>();
        boolean digit = false;
        String num = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c)) {
                if(!num.isEmpty()) {
                    digit = false;
                    temp.add(num);
                    num = "";
                }
                temp.add(c + "");
            } else {
                digit = true;
                num += c;
            }
        }
        if(!num.isEmpty()) {
            temp.add(num);
        }
        chunks = temp.toArray(new String[0]);
    }
    
    public int compareTo(Word o) {
        int ret = Integer.compare(this.chunks.length, o.chunks.length);
        int N = ret >= 0 ? o.chunks.length : this.chunks.length;
        for (int i = 0; i < N; i++) {
            String a = this.chunks[i];
            String b = o.chunks[i];
            if (a.equals(b)) {
                continue;
            }
            boolean thisIsDigit = Character.isDigit(a.charAt(0));
            boolean oIsDigit = Character.isDigit(b.charAt(0));
            if (thisIsDigit && oIsDigit) {
                BigInteger aNum = new BigInteger(a);
                BigInteger bNum = new BigInteger(b);
                ret = aNum.compareTo(bNum);
                if (ret == 0) {
                    ret = Integer.compare(a.length(), b.length());
                }
            } else if (!thisIsDigit && !oIsDigit) {
                char aChar = a.charAt(0);
                char bChar = b.charAt(0);
                ret = Character.compare(Character.toLowerCase(aChar), Character.toLowerCase(bChar));
                if (ret == 0) {
                    ret = Character.isLowerCase(aChar) ? 1 : -1;
                }
            } else {
                ret = thisIsDigit ? -1 : 1;
            }
            break;
        }
        return ret;
    }
}