package 동적계획법.P1003_피보나치_함수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P1003_피보나치_함수/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        int[] dp0 = new int[41];
        int[] dp1 = new int[41];

        dp0[0] = 1;
        dp0[1] = 0;
        dp0[2] = 1;
        dp0[3] = 1;

        dp1[0] = 0;
        dp1[1] = 1;
        dp1[2] = 1;
        dp1[3] = 2;

        for (int i = 2; i <= 40; i++) {
            dp0[i] = dp0[i - 1] + dp0[i - 2];
            dp1[i] = dp1[i - 1] + dp1[i - 2];
        }


        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            System.out.print(dp0[num] + " ");
            System.out.println(dp1[num]);
        }

    }
}
