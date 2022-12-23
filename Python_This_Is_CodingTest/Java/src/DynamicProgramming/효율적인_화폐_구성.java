package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class 효율적인_화폐_구성 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        // N개의 화폐 단위 정보를 입력받기
        int arr[] = new int[n];
        for (int index = 0; index < n; index++) {
            arr[index] = scan.nextInt();
        }

        int[] d = new int[m + 1]; // DP 테이블 초기화
        Arrays.fill(d, 10001);

        // Dp 진행
        d[0] = 0;

        for (int firstIndex = 0; firstIndex < n; firstIndex++) {
            for (int secondIndex = arr[firstIndex]; secondIndex <= m; secondIndex++) {
                // (firstIndex - k)원을 만드는 방법이 존재하는 경우
                if (d[secondIndex - arr[firstIndex]] != 1001) {
                    d[secondIndex] = Math.min(d[secondIndex], d[secondIndex - arr[firstIndex]] + 1);
                }
            }
        }
        if(d[m] == 10001) System.out.println(-1);
        else System.out.println(d[m]);
    }
}

/*
input
2 15
2
3

output
5

input
3 4
3
5
7

output
-1
 */
