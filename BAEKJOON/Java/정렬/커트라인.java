package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 커트라인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String [] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int[] arr = new int[N];

        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(arr);

        System.out.println(arr[N-k]);
    }
}
