package DynamicProgramming;

public class 피보나치수열_탑다운 {

    // 한 번 계산된 결과를 메모이제이션하기 위한 배열 초기화
    public static long[] dp = new long[100];

    // 피보나치 함수를 재귀함수로 구현 (탑다운 다이나믹 프로그래밍)
    public static long fibo(int x) {
        // 종료 조건(1 or 2일 때 1을 반환)
        if (x == 1 || x == 2) {
            return 1;
        }

        // 이미 계산한 적 있는 문제라면 그대로 반환
        if (dp[x] != 0) {
            return dp[x];
        }

        // 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
        dp[x] = fibo(x - 1) + fibo(x - 2);
        return dp[x];
    }

    public static void main(String[] args) {
        System.out.println(fibo(50));
    }

}
