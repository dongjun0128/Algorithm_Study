package DFSBFS;

import java.util.Arrays;
import java.util.Scanner;

public class 음료수_얼려_먹기 {
    static int N;
    static int M;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int result = 0;

        // N, M을 공백을 기준으로 구분하여 입력 받기
        N = scan.nextInt();
        M = scan.nextInt();
        scan.nextLine(); // 버퍼 지우기

        graph = new int[N][M];

        // 2차원 리스트의 맵 정보 입력 받기
        for (int firstIndex = 0; firstIndex < N; firstIndex++) {
            String str = scan.nextLine();
            for (int secondIndex = 0; secondIndex < M; secondIndex++) {
                graph[firstIndex][secondIndex] = str.charAt(secondIndex) - '0';
            }
        }

        // 모든 노드(위치)에 대하여 음료수 채우기
        for (int firstIndex = 0; firstIndex < N; firstIndex++) {
            for (int secondIndex = 0; secondIndex < M; secondIndex++) {
                // 현재 위치에서 DFS 수행
                if (dfs(firstIndex, secondIndex) == true) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    public static boolean dfs(int x, int y) {
        // 주어진 범위를 벗어나는 경우에는 즉시 종료
        if (x <= -1 || x >= N || y <= -1 || y >= M) {
            return false;
        }

        // 현재 노드를 아직 방문하지 않았다면
        if (graph[x][y] == 0) {
            // 해당 노드 방문 처리
            graph[x][y] = 1;
            // 상, 하, 좌, 우의 위치들도 모두 재귀적으로 호출
            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);
            return true;
        }

        return false;
    }
}

/*
input
4 5
00110
00011
11111
00000

output
3

input
15 14
00000111100000
11111101111110
11011101101110
11011101100000
11011111111111
11011111111100
11000000011111
01111111111111
00000000011111
01111111111000
00011111111000
00000001111000
11111111110011
11100011111111
11100011111111

output
8
 */