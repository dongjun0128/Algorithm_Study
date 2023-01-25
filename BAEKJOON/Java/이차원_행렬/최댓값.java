package 이차원_행렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 최댓값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int a = 0, b=0;

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                if (max < Integer.parseInt(input[j])) {
                    max = Integer.parseInt(input[j]);
                    a = i;
                    b = j;
                }
            }
        }

        System.out.println(max);
        System.out.println(++a + " " + ++b);
    }
}
