package 누적합.P1806_부분합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/누적합/P1806_부분합/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int indexLeft = 0;
        int indexRight = 0;
        int sum = 0;
        int length = Integer.MAX_VALUE;

        while (indexLeft <= N && indexRight <= N) {
            if(sum >= S && length > indexRight - indexLeft) length = indexRight - indexLeft;

            if(sum < S){
                sum += arr[indexRight];
                indexRight++;
            } else{
                sum -= arr[indexLeft];
                indexLeft++;
            }
        }

        if(length == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(length);
    }
}
