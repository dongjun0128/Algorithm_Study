package 동적계획법.P9252_LCS2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P9252_LCS2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);

        String result = "";

        int x = str1.length();
        int y = str2.length();

        while (x != 0 && y != 0) {
            if(str1.charAt(x - 1) == str2.charAt(y - 1)) result += str1.charAt(x - 1);

            if(dp[x-1][y] == dp[x][y]) { // 왼쪽값과 같다
                x-=1;
            } else if(dp[x][y-1] == dp[x][y]) { // 윗쪽값과 같다.
                y-=1;
            } else { // 왼쪽값과 윗쪽값과 같은 경우가 없다.
                x-=1;
                y-=1;
            }
        }

        StringBuffer sb = new StringBuffer(result);
        String reversedStr = sb.reverse().toString();

        System.out.println(reversedStr);

    }
}
