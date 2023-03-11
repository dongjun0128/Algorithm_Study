package 동적계획법.P5582_공통_부분_문자열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P5582_공통_부분_문자열/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int result = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(dp[i][j], result);
                }
            }
        }

        System.out.println(result);

    }
}
