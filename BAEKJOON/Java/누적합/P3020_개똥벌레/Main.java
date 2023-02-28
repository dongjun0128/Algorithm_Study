package 누적합.P3020_개똥벌레;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] up;
    static int[] down;
    static int[] sumUp;
    static int[] sumDown;
    static int minValue = Integer.MAX_VALUE;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/구현/P3020_개똥벌레/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[H + 1];
        down = new int[H + 1];
        sumUp = new int[H + 1];
        sumDown = new int[H + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            if (i % 2 == 0) { //석순
                down[Integer.parseInt(st.nextToken())]++;
            } else { // 종유석
                up[Integer.parseInt(st.nextToken())]++;
            }
        }

        for (int i = 1; i < up.length; i++) {
            sumUp[i] = sumUp[i - 1] + up[i];
            sumDown[i] = sumDown[i - 1] + down[i];
        }

        for (int i = 1; i <= H; i++) {
            int crush = 0;
            int upIndex = i;
            int downIndex = H - i;

            crush += sumUp[H] - sumUp[upIndex - 1];
            crush += sumDown[H] - sumDown[downIndex];

            if(crush < minValue){
                minValue = crush;
                cnt = 1;
            } else if(crush == minValue){
                cnt++;
            }
        }

        System.out.println(minValue + " " + cnt);
    }
}
