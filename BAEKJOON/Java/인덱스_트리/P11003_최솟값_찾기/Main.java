package 인덱스_트리.P11003_최솟값_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[] A;
    static int[] tree;
    static int S;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/구현/P11003_최솟값_찾기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        A = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        init();

        //System.out.println(Arrays.toString(tree));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int left = (i + 1) - L + 1;
            if(left < 1) left = 1;
            int right = i + 1;

            sb.append(query(left, right));
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static int query(int left, int right) {
        left = left + S - 1;
        right = right + S - 1;
        int ret = Integer.MAX_VALUE;

        while (left <= right) {
            if (left % 2 == 1) {
                ret = Math.min(ret, tree[left]);
                left++;
            }

            if (right % 2 == 0) {
                ret = Math.min(ret, tree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return ret;
    }

    public static void init() {
        S = 1;
        while (S < N) {
            S *= 2;
        }

        tree = new int[S * 2];

        Arrays.fill(tree, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            tree[i + S] = A[i];
        }

        for (int i = S - 1; i > 0 ; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }
}
