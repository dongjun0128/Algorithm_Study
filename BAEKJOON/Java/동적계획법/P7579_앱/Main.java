package 동적계획법.P7579_앱;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] memories;
    static int[] costs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P7579_앱/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        memories = new int[N + 1];
        costs = new int[N + 1];
        dp = new int[N + 1][100000 + 1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            memories[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            costs[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 100000; j++) {
                if (costs[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i]] + memories[i]);
                }
            }
        }

        for (int i = 1; i < 100000 ; i++) {
            if(dp[N][i] >= M){
                System.out.println(i);
                return;
            }
        }
    }
}
