package 인덱스_트리.P2357_최솟값과_최댓값.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int S;
    static int[] arr;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P2357_최솟값과_최댓값/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        }

        init();

//        System.out.println(Arrays.toString(minTree));
//        System.out.println(Arrays.toString(maxTree));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.print(minQuery(a,b) + " ");
            System.out.println(maxQuery(a, b));
        }

    }

    public static int minQuery(int leftQuery, int rightQuery) {
        int num = Integer.MAX_VALUE;
        int left = S + leftQuery - 1;
        int right = S + rightQuery - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                num = Math.min(num, minTree[left]);
                left++;
            }

            if (right % 2 == 0) {
                num = Math.min(num, minTree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return num;
    }

    public static int maxQuery(int leftQuery, int rightQuery) {
        int num = 0;
        int left = S + leftQuery - 1;
        int right = S + rightQuery - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                num = Math.max(num, maxTree[left]);
                left++;
            }

            if (right % 2 == 0) {
                num = Math.max(num, maxTree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return num;
    }

    public static void init() {
        S = 1;
        while (S < N) {
            S *= 2;
        }

        minTree = new int[S * 2];
        maxTree = new int[S * 2];

        Arrays.fill(minTree, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            minTree[S + i] = arr[i];
            maxTree[S + i] = arr[i];
        }

        for (int i = S - 1; i > 0 ; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
    }


}
