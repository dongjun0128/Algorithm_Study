package 이분탐색.P1072_게임;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long X; //게임 횟수
    static long Y; //이긴 횟수
    static long percent;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/이분탐색/P1072_게임/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());


        percent = (Y * 100) / X;

        //System.out.println(percent);

        long Z = 100 * Y / X;

        if (Z >= 99) {
            System.out.println(-1);
        } else {

            long start = 0;
            long end = 2000000000;

            while (start < end) {
                long mid = (start + end) / 2;

                long ret = 100 * (Y + mid) / (X + mid);

                if (ret> Z) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println(end);
        }
    }
}
