package DynamicProgramming;

public class 피보나치수열_보텀업 {

    public static long[] dp = new long[100];

    public static void main(String[] args) {
        // 첫 번째 피보나치 수와 두 번째 피보나치 수는 1
        dp[1] = 1;
        dp[2] = 1;
        int n = 50; // 50번째 피보나치 수를 계산

        // 피보나치 함수를 반복문으로 구현
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}
