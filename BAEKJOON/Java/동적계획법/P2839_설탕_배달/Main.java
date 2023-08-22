package 동적계획법.P2839_설탕_배달;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P2839_설탕_배달/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int fiveTimes = N / 5;
        int answer = -1;

        for (int i = fiveTimes; i >= 0 ; i--) {
            int num = N - 5 * i;

            if (num % 3 == 0) {
                answer = i + (num / 3);
                break;
            }
        }

        System.out.println(answer);
    }
}
