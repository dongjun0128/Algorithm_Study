package 인덱스_트리.P11505_구간_곱_구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int MOD = 1000000007;
    static int N, M, K;
    static long[] nums;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P11505_구간_곱_구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];

        S = 1;

        while (S < N) {
            S *= 2;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            nums[i] = Long.parseLong(st.nextToken());
        }

        tree = new long[S * 2];

        for (int i = 0; i < S; i++) {
            if (i < N) {
                tree[i + S] = nums[i];
            } else {
                tree[i + S] = 1;
            }
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % MOD;
        }


        //System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 1) { //b번째 수를 c로 바꾼다.
                long c = Integer.parseInt(st.nextToken());
                update(b, c);
                //System.out.println(Arrays.toString(tree));
            } else { //b부터 c까지 곱 구하기
                int c = Integer.parseInt(st.nextToken());
                System.out.println(query(b, c));
            }
        }

    }

    static void update(int index, long num) {
        index = index + S - 1;
        tree[index] = num;
        index /= 2;

        while (index > 0) {
            tree[index] = (tree[index * 2] * tree[index * 2 + 1]) % MOD;
            index /= 2;
        }
    }

    static long query(int leftQuery, int rightQuery) {
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        long result = 1;

        while (left <= right) {
            if (left % 2 == 1) {
                result = (result * tree[left]) % MOD;
                left++;
            }

            if (right % 2 == 0) {
                result = (result * tree[right]) % MOD;
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return result;
    }
}
