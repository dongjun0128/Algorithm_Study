package 동적계획법.P11660_구간_합_구하기_5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] nums;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P11660_구간_합_구하기_5/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N][N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());

                if (j == 0) {
                    dp[i][j] = nums[i][j];
                } else {
                    dp[i][j] = dp[i][j -1] + nums[i][j];
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(nums[i]));
//        }
//
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        for (int n = 0; n < M; n++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            long answer = 0;

            for (int i = x1; i <= x2; i++) {
                if (y1 == 0) {
                    answer += dp[i][y2];
                } else {
                    answer += dp[i][y2] - dp[i][y1 - 1];
                }
            }

            System.out.println(answer);
        }

    }
}
