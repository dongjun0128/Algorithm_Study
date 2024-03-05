package 구현.P7682_틱택토;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/구현/P7682_틱택토/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 번째 사람이 X를 두며 게임을 시작
        // 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는데 성공하면 게임은 즉시 끝난다
        // 게임판이 가득 차도 게임이 끝난다.
        // 게임판의 상태가 주어지면, 그 상태가 틱택토 게임에서 발생할 수 있는 최종상태인지를 판별하시오

        Set<String> set = new HashSet<>();


    }
}
