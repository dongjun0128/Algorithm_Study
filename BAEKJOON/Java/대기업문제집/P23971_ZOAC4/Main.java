package 대기업문제집.P23971_ZOAC4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, N, M;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/대기업문제집/P23971_ZOAC4/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int x = 0;
        int y = 0;
        int result = 0;

        while(true) {
            while (true) {
                result++;
                y += M + 1;
                if (y >= W) break;
            }
            x += N + 1;
            y = 0;
            if(x >= H) break;
        }

        System.out.println(result);
    }
}
