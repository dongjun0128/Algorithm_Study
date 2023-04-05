package 삼성SW기출.P17143_낚시왕;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<Shark>[][] map;
    static int R, C, M;
    static int result = 0;
    // 위 아래 오른 왼
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P17143_낚시왕/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Queue[R][C];

        for (int r = 0; r <R ; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            map[r][c].add(new Shark(r, c, s, d, z));
        }

        int kingIndex = -1;

        while (kingIndex++ < C - 1) { // 1. 낚시왕이 오른쪽 으로 한 칸 이동

            // 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
            for (int i = 0; i < R; i++) {
                if (map[i][kingIndex].size() > 0) {
                    Shark shark = map[i][kingIndex].poll();
                    result += shark.z;
                    break;
                }
            }

            // 3. 상어가 이동한다.
            sharksMove();
        }

        System.out.println(result);
    }

    static void sharksMove() {
        Queue<Shark>[][] tempMap = new Queue[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                tempMap[r][c] = new LinkedList<>();
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!map[r][c].isEmpty()) {
                    Shark shark = map[r][c].poll();

                    int moveCnt = shark.s;

                    while (moveCnt > 0) {
                        if (shark.d == 0) { // 위쪽 이동
                            if (moveCnt <= shark.r) {
                                shark.r -= moveCnt;
                                moveCnt = 0;
                            } else {
                                moveCnt -= shark.r;
                                shark.r = 0;
                                shark.d = 1; // 아래로 방향 바꾸기
                            }
                        } else if (shark.d == 1) { // 아래로 이동
                            if (moveCnt <= (R - 1) - shark.r) {
                                shark.r += moveCnt;
                                moveCnt = 0;
                            } else {
                                moveCnt -= (R - 1) - shark.r;
                                shark.r = R - 1;
                                shark.d = 0; // 위로 방향 바꾸기
                            }
                        } else if (shark.d == 2) { // 오른 쪽 이동
                            if (moveCnt <= (C - 1) - shark.c) {
                                shark.c += moveCnt;
                                moveCnt = 0;
                            } else {
                                moveCnt -= (C - 1) - shark.c;
                                shark.c = C - 1;
                                shark.d = 3;
                            }
                        } else if (shark.d == 3) { // 왼 쪽 이동
                            if (moveCnt <= (shark.c)) {
                                shark.c -= moveCnt;
                                moveCnt = 0;
                            } else {
                                moveCnt -= shark.c;
                                shark.c = 0;
                                shark.d = 2;
                            }
                        }
                    }

                    if (!tempMap[shark.r][shark.c].isEmpty()) {
                        Shark shark1 = tempMap[shark.r][shark.c].poll();
                        if (shark1.z > shark.z) { // 기존의 상어의 크기가 더 크면
                            tempMap[shark1.r][shark1.c].add(shark1);
                        } else { // 새로 들어온 상어의 크기가 더 크면
                            tempMap[shark.r][shark.c].add(shark);
                        }
                    } else {
                        tempMap[shark.r][shark.c].add(shark);
                    }
                }
            }
        }

        map = tempMap;
    }

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
