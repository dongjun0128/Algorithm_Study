package 동적계획법.P11659_구간_합_구하기_4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P11659_구간_합_구하기_4/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if(i == 0) {
                dp[0] = nums[0];
            } else{
                dp[i] = dp[i-1] + nums[i];
            }
        }

        //System.out.println(Arrays.toString(dp));

        for (int m = 0; m < M ; m++) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if(i == 1)
                System.out.println(dp[j - 1]);
            else if(i == j) {
                System.out.println(nums[i - 1]);
            } else{
                System.out.println(dp[j - 1] - dp[i - 2]);
            }
        }
    }
}
