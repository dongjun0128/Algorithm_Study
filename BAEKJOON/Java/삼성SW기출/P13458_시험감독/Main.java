package 삼성SW기출.P13458_시험감독;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] room;
    static int B;
    static int C;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P13458_시험감독/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        room = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            room[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long result = 0;

        for (int i = 0; i < N; i++) {
            int totalNum = room[i];

            // 총 감독관 계산
            totalNum -= B;

            result+=1;

            // 부 감독관 계산
            if(totalNum > 0){
                if(totalNum % C == 0) result += totalNum / C;
                else result += (totalNum / C) + 1 ;
            }
        }

        System.out.println(result);
    }
}
