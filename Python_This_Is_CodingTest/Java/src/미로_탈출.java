import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

public class 미로_탈출 {
    static int N;
    static int M;
    static int[][] graph;

    // 이동할 네 가지 방향 정의 (상, 하, 좌, 우)
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

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

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
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
        return graph[N - 1][M - 1];
    }
}

/*
input
5 6
101010
111111
000001
111111
111111

output
10

 */