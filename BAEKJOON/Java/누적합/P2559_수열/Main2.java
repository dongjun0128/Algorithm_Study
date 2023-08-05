package 누적합.P2559_수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/누적합/P2559_수열/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int pointer1 = 0;
        int pointer2 = 0;

        while(pointer2 < K) {
            sum += arr[pointer2++];
        }

        max = Math.max(max,sum);

        while(pointer2 < N) {
            sum -= arr[pointer1++];
            sum += arr[pointer2++];

            max = Math.max(max,sum);
        }

        System.out.println(max);
    }
}
