package 슬라이딩_윈도우.P2559_수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int[] arr;
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/슬라이딩_윈도우/P2559_수열/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;

        while (end < K) {
            sum += arr[end++];
        }

        answer = sum;

        while(end < N) {
            sum -= arr[start++];
            sum += arr[end++];
            answer = Math.max(answer,sum);
        }

        System.out.println(answer);
    }
}
