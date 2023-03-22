package 삼성SW기출.P20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] A;
    static boolean[] robots;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P20055_컨베이어_벨트_위의_로봇/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N * 2];
        robots = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N * 2; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;

        while (true) {
            time++;
            move();
            moveRobots();

            for (int i = N - 2; i >= 0; i--) {
                if (robots[i] == true && robots[i+1] == false && A[i + 1] != 0) { // 로봇이 다음 칸으로 이동할 수 있으면
                    robots[i] = false;
                    robots[i + 1] = true;
                    A[i + 1]--;
                }
            }

            robots[N - 1] = false;

            if (A[0] != 0) {
                A[0]--;
                robots[0] = true;
            }

            int cnt = 0;
            for (int i = 0; i < N * 2; i++) {
                if(A[i] == 0) cnt++;
            }

            if(cnt >= K) break;
        }

        System.out.println(time);
    }

    public static void move() {
        int[] temp = new int[N * 2];

        for (int i = 0; i < N * 2 - 1; i++) {
            temp[i + 1] = A[i];
        }

        temp[0] = A[N * 2 - 1];

        A = temp;
    }

    public static void moveRobots() {
        boolean[] temp = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            temp[i + 1] = robots[i];
        }

        temp[0] = robots[N - 1];
        // 로봇이 내리는 위치에 닿으면 즉시 내리기
        temp[N - 1] = false;

        robots = temp;
    }
}
