package 삼성SW기출.P21608_상어_초등학교.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static List<List<Integer>> totalLikePerson;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21608_상어_초등학교/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        totalLikePerson = new ArrayList<>();

        for (int i = 0; i <= N * N; i++) {
            totalLikePerson.add(new ArrayList<>());
        }

        for (int t = 0; t < N * N; t++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                int person = Integer.parseInt(st.nextToken());

                totalLikePerson.get(num).add(person);
            }

            int likePerson = -1;
            int blankSpace = -1;
            PriorityQueue<Node> site = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    int likePersonCnt = 0;
                    int blankSpaceCnt = 0;

                    if (map[i][j] == 0) { // 비어있는 칸
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                if (totalLikePerson.get(num).contains(map[nx][ny])) {
                                    likePersonCnt++;
                                } else if (map[nx][ny] == 0) {
                                    blankSpaceCnt++;
                                }
                            }
                        }

                        if (likePerson < likePersonCnt) {
                            likePerson = likePersonCnt;

                            blankSpace = blankSpaceCnt;

                            site = new PriorityQueue<>();
                            site.add(new Node(i, j));
                        } else if (likePerson == likePersonCnt) {
                            if (blankSpace < blankSpaceCnt) {
                                blankSpace = blankSpaceCnt;
                                site = new PriorityQueue<>();
                                site.add(new Node(i, j));
                            }
                        }

                    }
                }
            }

            Node node = site.poll();

            map[node.x][node.y] = num;

        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
        // 이 때 인접한 칸은 |r1 - r2| + |c1 - c2| = 1

        // 2. 1 을 만족하는 칸이 여러개라면, 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 자리를 정한다.

        // 3. 2 를 만족하는 칸이 여러개라면, 행,열 번호가 가장 작은 자리를 정한다.

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int cnt = 0;
                int personNum = map[i][j];

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (totalLikePerson.get(personNum).contains(map[nx][ny])) {
                            cnt++;
                        }
                    }
                }

                if (cnt == 1) {
                    answer += 1;
                } else if (cnt == 2) {
                    answer += 10;
                } else if (cnt == 3) {
                    answer += 100;
                } else if (cnt == 4) {
                    answer += 1000;
                }
            }
        }

        System.out.println(answer);

    }


}

class Node implements Comparable<Node>{
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        int cmp1 = this.x - o.x;

        if (cmp1 == 0) {
            return this.y - o.y;
        }

        return cmp1;
    }
}
