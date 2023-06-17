import java.io.*;
import java.util.regex.Pattern;

public class Main {
    static Pattern pattern = Pattern.compile("^(10(0+)(1+)|01)+$");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String sound = br.readLine();
        System.out.println(pattern.matcher(sound).matches() ? "SUBMARINE" : "NOISE");
    }
}