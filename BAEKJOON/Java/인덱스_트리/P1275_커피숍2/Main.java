package 인덱스_트리.P1275_커피숍2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int[] arr;
    static int S;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P1275_커피숍2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        initTree();

        //System.out.println(Arrays.toString(tree));

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(query(Math.min(x, y), Math.max(x, y)));
            update(a, b);
        }

    }

    public static void initTree() {
        S = 1;

        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = S; i < S + arr.length; i++) {
            tree[i] = arr[i - S];
        }

        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public static long query(int leftQuery, int rightQuery) {
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;

        long sumValue = 0;

        while (left <= right) {
            if (left % 2 == 1) {
                sumValue += tree[left];
                left++;
            }

            if (right % 2 == 0) {
                sumValue += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return sumValue;
    }

    public static void update(int index, int value) {
        long chai;
        index = index + S - 1;

        chai = value - tree[index];
        tree[index] = value;

        while (index > 0) {
            index /= 2;
            tree[index] += chai;
        }
    }
}
