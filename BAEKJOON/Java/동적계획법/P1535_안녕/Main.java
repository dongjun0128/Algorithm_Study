package 동적계획법.P1535_안녕;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] W, V;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P1535_안녕/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        W = new int[N + 1];
        V = new int[N + 1];

        dp = new int[N + 1][101];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            W[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            V[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 101; j++) {
                if (W[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);

                }
            }
        }

        System.out.println(dp[N][99]);
    }
}
