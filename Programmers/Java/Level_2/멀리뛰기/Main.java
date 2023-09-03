package Level_2.멀리뛰기;

public class Main {
    public static void main(String[] args) {
        int[] dp = new int[2001];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < 2001; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
        }
    }
}
