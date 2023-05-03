package 동적계획법.P2193_이친수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P2193_이친수/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] dp = new long[90];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < 90; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int num = Integer.parseInt(st.nextToken());

        System.out.println(dp[num - 1]);
    }
}
