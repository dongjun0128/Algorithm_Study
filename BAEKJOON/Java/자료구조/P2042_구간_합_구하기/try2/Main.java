package 자료구조.P2042_구간_합_구하기.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static long[] arr;
    static int S;
    static int N,M, K;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/자료구조/P2042_구간_합_구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(new StringTokenizer(br.readLine()).nextToken());
        }

        init();

//        System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int commend = Integer.parseInt(st.nextToken());

            if (commend == 1) { // b번째 수를 c로 바꾸기
                int index = Integer.parseInt(st.nextToken());
                long num = Long.parseLong(st.nextToken());

                update(index, num);
            } else if (commend == 2) { // b 부터 c 까지의 합
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());

                System.out.println(query(left, right));
            }
        }

    }

    public static long query(int leftQuery, int rightQuery) {
        long sum = 0;

        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }

            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }

    public static void update(int index, long num) {
        index = S + index - 1;

        tree[index] = num;

        index /= 2;

        while(index >0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index /= 2;
        }
    }

    public static void init() {
        S = 1;

        while(S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = S; i < S + N; i++) {
            tree[i] = arr[i - S];
        }

        for (int i = S - 1; i > 0 ; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

    }
}
