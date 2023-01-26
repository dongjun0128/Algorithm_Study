package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 대표값2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[5];
        int num = 0;

        for(int i = 0 ; i <5; i++){
            arr[i] = Integer.parseInt(br.readLine());
            num += arr[i];
        }

        Arrays.sort(arr);

        System.out.println(num / 5);
        System.out.println(arr[2]);
    }
}
