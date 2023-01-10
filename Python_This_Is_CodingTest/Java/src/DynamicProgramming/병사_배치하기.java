package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 병사_배치하기 {
    static int n;
    static ArrayList<Integer> v = new ArrayList<Integer>();
    static int[] dp = new int[2000];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            v.add(scan.nextInt());
        }

        // 순서를 뒤집어 '최장 증가 부분 수열'문제로 변환
        Collections.reverse(v);

        // 다이나믹 프로그래밍을 위한 1차원 DP테이블 초기화
        Arrays.fill(dp, 1);

        // 가장 긴 증가하는 부분 수열(LIS) 알고리즘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // i 보다 작은 j번째 수열을 발견하면 dp[j] 까지의 수열에 i 를 붙인 값 과 dp[i]를 비교하여 더 큰 값을 저장
                if (v.get(j) < v.get(i)) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        // 열외해야 하는 병사의 최소 수를 출력
        int maxValue = 0;
        for (int i = 0; i < n; i++) maxValue = Math.max(maxValue, dp[i]);
        System.out.println(n - maxValue);
    }
}

// LIS
// 모든 0 ≤ j < i 에 대하여, D[i] = max(D[i], D[j] + 1) if array[j] < array[I]

/*
input
7
15 11 4 8 5 2 4
output
2
 */