package Level_2.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

class Node {

    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class 게임_맵_최단거리 {

    // 이동할 네 가지 방향 정의 (상, 하, 좌, 우)
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};
    public static int N;
    public static int M;

    public static void main(String[] args) {
        String input = "[[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]";
        System.out.println(change(input));
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }

    static public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        int answer = bfs(0,0,maps);
        return answer;
    }

    static public int bfs(int x, int y, int[][] graph){
        // 큐(Queue) 구현을 위해 queue 라이브러리 사용
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        // 큐가 빌 때까지 반복하기
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            x = node.getX();
            y = node.getY();

            // 현재 위치에서 4가지 방향으로의 위치 확인
            for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
                int nx = x + dx[directionIndex];
                int ny = y + dy[directionIndex];

                // 미로 찾기 공간을 벗어난 경우 무시
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 벽인 경우 무시
                if (graph[nx][ny] == 0) continue;

                // 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if (graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[x][y] + 1;
                    queue.offer(new Node(nx, ny));
                }
            }

        }

        // 가장 오른쪽 아래까지의 최단 거리 반환
        if(graph[N - 1][M - 1] == 1) return -1;
        return graph[N - 1][M - 1];
    }

    static public String change(String input) {
        String changeInput = "";

        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) == '[') changeInput += '{';
            else if (input.charAt(index) == ']') changeInput += '}';
            else {
                changeInput += input.charAt(index);
            }
        }

        return changeInput;
    }
}
