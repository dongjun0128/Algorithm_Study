package 누적합.P2559_수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/누적합/P2559_수열/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = K - 1;
        int sum = 0;

        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        maxValue = sum;

        for (int i = end + 1; i < N; i++) {
            sum -= arr[i - K];
            sum += arr[i];

            maxValue = Math.max(maxValue, sum);
        }

        System.out.println(maxValue);
    }
}
