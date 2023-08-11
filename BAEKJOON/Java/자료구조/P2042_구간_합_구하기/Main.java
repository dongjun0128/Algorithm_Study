package 자료구조.P2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int S;
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/자료구조/P2042_구간_합_구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        init();
        System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            long a, b, c;

            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if (a == 1) { // b번째 수를 c로 바꾸기
                update(b, c);
            } else if (a == 2) { // b번째 수 부터 c번째 수 까지 합 구하기
                System.out.println(query(b, c));
            }

        }

    }

    static void init() {
        S = 1;

        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = 0; i < N; i++) {
            tree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(long leftQuery, long rightQuery) {
        long sum = 0;

        long left = S + leftQuery - 1;
        long right = S + rightQuery - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[(int) left];
                left++;
            }

            if (right % 2 == 0) {
                sum += tree[(int) right];
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }

    static void update(long index, long num) {
        index = S + index - 1;

        tree[(int) index] = num;
        index /= 2;

        while (index > 0) {
            tree[(int) index] = tree[(int) (index * 2)] + tree[(int) (index * 2 + 1)];
            index /= 2;
        }

    }
}
