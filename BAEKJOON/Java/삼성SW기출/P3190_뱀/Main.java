package 삼성SW기출.P3190_뱀;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static HashMap<Integer, String> hash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P3190_뱀/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        // 1은 뱀
        map[0][0] = 1;

        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            // -1은 사과
            map[x][y] = -1;
        }

        st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            hash.put(x, c);
        }

        int result = 0;
        int direction = 0;
        int x = 0;
        int y = 0;
        LinkedList<Node> snake = new LinkedList<>();
        snake.add(new Node(0,0));

        while(true){
            result++;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 ) { //벽에 부딪히거나 내 몸에 맞으면 종료
                System.out.println(result);
                return;
            }

            if (map[nx][ny] == -1) {
                map[nx][ny] = 1;
                x = nx;
                y = ny;
                snake.addLast(new Node(x,y));
            } else {
                map[nx][ny] = 1;
                Node node = snake.getFirst();
                snake.removeFirst();
                int backX = node.x;
                int backY = node.y;
                map[backX][backY] = 0;
                x = nx;
                y = ny;
                snake.addLast(new Node(x,y));
            }

            if (hash.containsKey(result)) {
                if (hash.get(result).equals("D")) {
                    direction += 1;
                    if (direction == 4)
                        direction = 0;
                } else {
                    direction -= 1;
                    if (direction == -1)
                        direction = 3;
                }
            }
        }



        //System.out.println(result);
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
