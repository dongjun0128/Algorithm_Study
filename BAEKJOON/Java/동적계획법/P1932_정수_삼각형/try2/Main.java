package 동적계획법.P1932_정수_삼각형.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] triangle;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/동적계획법/P1932_정수_삼각형/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        triangle = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0) {
                    triangle[i][j] = Math.max(triangle[i][j], triangle[i][j] + triangle[i - 1][j]);
                } else {
                    triangle[i][j] = Math.max(triangle[i][j] + triangle[i - 1][j - 1], Math.max(triangle[i][j], triangle[i][j] + triangle[i - 1][j]));
                }
            }
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, triangle[N - 1][i]);
        }

        System.out.println(answer);

    }
}
