package DynamicProgramming;

import java.util.Scanner;

public class 개미_전사 {
    public static int[] d = new int[100];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 정수 N을 입력받기
        int n = scan.nextInt();

        // 모든 식량 정보 입력받기
        int[] arr = new int[n];
        for (int index = 0; index < n; index++) {
            arr[index] = scan.nextInt();
        }

        // DP 진행
        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);
        for (int index = 2; index < n; index++) {
            d[index] = Math.max(d[index - 1], d[index - 2] + arr[index]);
        }

        System.out.println(d[n - 1]);
    }
}

/*
input
4
1 3 1 5

output
8
 */
