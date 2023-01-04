package 그리디;

import java.util.Scanner;

public class 일이_될_때까지 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int k = scan.nextInt();
        int cnt = 0;

        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때 까지 빼기
            int target = (n / k) * k; // k로 나눌 수 있는 최대의 수
            cnt += (n - target); // k로 나눌 수 있는 최대의 수 까지 1씩 뻈다고 가정
            n = target;

            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;

            // K로 나누기
            cnt += 1;
            n /= k;
        }

        cnt += (n - 1);
        System.out.println(cnt);

    }

}

/*
input
25 5
output
2
 */
