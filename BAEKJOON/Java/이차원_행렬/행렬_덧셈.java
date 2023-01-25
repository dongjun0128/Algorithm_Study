package 이차원_행렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 행렬_덧셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] arr1 = new int[N][M];
        int[][] arr2 = new int[N][M];

        for(int i = 0 ; i < N; i++){
            input = br.readLine().split(" ");

            for(int j = 0 ; j < M ; j ++){
                arr1[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0 ; i < N; i++){
            input = br.readLine().split(" ");

            for(int j = 0 ; j < M ; j ++){
                arr2[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0 ; i <N; i++){
            for(int j = 0 ; j < M ; j++){
                System.out.print(arr1[i][j] + arr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
