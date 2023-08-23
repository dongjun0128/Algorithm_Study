package 이분탐색.P7453_합이_0인_네_정수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/이분탐색/P7453_합이_0인_네_정수/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sumAB = 0;
                sumAB += A[i];
                sumAB += B[j];
                AB[cnt] = sumAB;

                int sumCD = 0;
                sumCD += C[i];
                sumCD += D[j];
                CD[cnt++] = sumCD * -1;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long answer = 0;

        for (int i = 0; i < AB.length; i++) {
            int sumAB = AB[i];

            int left = 0;
            int right = CD.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                int sumCD = CD[mid];

                if (sumCD > sumAB) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            int upper = right;

            left = 0;
            right = CD.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                int sumCD = CD[mid];

                if (sumCD >= sumAB) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            int lower = right;

            answer += (upper - lower);

        }

        System.out.println(answer);
    }
}
