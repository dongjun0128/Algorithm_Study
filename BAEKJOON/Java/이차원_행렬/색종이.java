package 이차원_행렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 색종이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[][] graph = new int[100][100];

        int N = Integer.parseInt(br.readLine());

        while (N > 0) {
            String[] input = br.readLine().split(" ");
            int a, b;

            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            for (int i = a; i < a + 10; i++) {
                for (int j = b; j < b + 10; j++) {
                    graph[i][j] = 1;
                }
            }

            N--;
        }

        int cnt=0;

        for(int i = 0 ; i < 100; i++){
            for(int j = 0 ; j < 100; j++){
                if(graph[i][j] == 1){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}
