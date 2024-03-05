package 구현.P2292_벌집;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/구현/P2292_벌집/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        if(N == 1) {
            System.out.println(1);
            return;
        }

        int num = 7;
        int cnt = 2;
        int answer = 2;

        while (true) {
            if (N <= num) {
                System.out.println(answer);
                return;
            } else {
                num += 6 * cnt++;
                answer++;
            }
        }




    }
}
