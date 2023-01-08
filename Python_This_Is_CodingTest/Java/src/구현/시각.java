package 구현;

import java.util.Scanner;

public class 시각 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int cnt = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    if ((String.valueOf(i) + String.valueOf(j) + String.valueOf(k)).contains("3")) {
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}

/*
input
5
output
11475
 */