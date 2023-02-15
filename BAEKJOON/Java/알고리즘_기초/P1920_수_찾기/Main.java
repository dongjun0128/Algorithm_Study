package 알고리즘_기초.P1920_수_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    static int M;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/알고리즘_기초/P1920_수_찾기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            System.out.println(binarySearch(0,A.length - 1,num));
        }

    }

    static int binarySearch(int start, int end, int target) {
        if (start > end) {
            return 0;
        }

        int mid = (start + end) / 2;

        if (A[mid] == target) {
            return 1;
        } else if (A[mid] > target) {
            return binarySearch(start, mid - 1, target);
        } else { //A[mid] < target
            return binarySearch(mid + 1, end, target);
        }
    }
}
