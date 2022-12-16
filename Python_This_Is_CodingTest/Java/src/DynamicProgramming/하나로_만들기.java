package DynamicProgramming;

import java.util.Scanner;

public class 하나로_만들기 {

    // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
    public static int[] d = new int[30001];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x = scan.nextInt();

        // DP 진행 (보텀업)
        for (int index = 2; index <= x; index++) {
            // 현재의 수에서 1을 빼는 경우
            d[index] = d[index - 1] + 1;

            // 현재의 수가 2로 나누어 떨어지는 경우
            if (index % 2 == 0)
                d[index] = Math.min(d[index], d[index / 2] + 1);

            // 현재의 수가 3으로 나누어 떨어지는 경우
            if (index % 3 == 0)
                d[index] = Math.min(d[index], d[index / 3] + 1);

            // 현재의 수가 5로 나누어 떨어지는 경우
            if (index % 5 == 0)
                d[index] = Math.min(d[index], d[index / 5] + 1);
        }

        System.out.println(d[x]);
    }

}

/*
input
26
output
3
 */