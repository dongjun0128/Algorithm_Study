package 삼성SW기출.P12100_2024;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] tempMap;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P12100_2024/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 4; i++) {
            combi(i, new ArrayList<>(), 1);
        }

        System.out.println(result);
    }

    static void combi(int index, ArrayList<Integer> combination, int depth) {
        // 1. 체크 인
        combination.add(index);

        // 2. 목적지인가?
        if (depth == 5) {
            tempMap = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tempMap[i][j] = map[i][j];
                }
            }

            for(Integer direction : combination){
                move(direction);
            }

            int temp = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp = Math.max(temp,tempMap[i][j]);
                }
            }

            result = Math.max(result,temp);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                // 4. 갈 수 있는가?
                // 5. 간다.
                combi(i, combination, depth + 1);
            }
        }

        // 6. 체크 아웃
        combination.remove(depth - 1);
    }

    static void move(int direction) {
        if (direction == 0) { // 왼쪽 밀기
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (tempMap[x][y] != 0) {
                        int ny = y;

                        while (true) {
                            ny--;
                            if (ny < 0) break;

                            if (tempMap[x][ny] == 0) {
                                tempMap[x][ny] = tempMap[x][ny + 1];
                                tempMap[x][ny + 1] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N - 1; y++) {
                    if (tempMap[x][y] == tempMap[x][y + 1]) {
                        tempMap[x][y] += tempMap[x][y + 1];
                        tempMap[x][y + 1] = 0;
                        y++;
                    }
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (tempMap[x][y] != 0) {
                        int ny = y;

                        while (true) {
                            ny--;
                            if (ny < 0) break;

                            if (tempMap[x][ny] == 0) {
                                tempMap[x][ny] = tempMap[x][ny + 1];
                                tempMap[x][ny + 1] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        } else if (direction == 1) { // 아래로 밀기
            for (int y = 0; y < N; y++) {
                for (int x = N - 1; x >= 0; x--) {
                    if (tempMap[x][y] != 0) {
                        int nx = x;

                        while (true) {
                            nx++;
                            if (nx >= N) break;

                            if (tempMap[nx][y] == 0) {
                                tempMap[nx][y] = tempMap[nx - 1][y];
                                tempMap[nx - 1][y] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = N - 1; x > 0; x--) {
                    if (tempMap[x][y] == tempMap[x - 1][y]) {
                        tempMap[x][y] += tempMap[x - 1][y];
                        tempMap[x - 1][y] = 0;
                        x--;
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = N - 1; x >= 0; x--) {
                    if (tempMap[x][y] != 0) {
                        int nx = x;

                        while (true) {
                            nx++;
                            if (nx >= N) break;

                            if (tempMap[nx][y] == 0) {
                                tempMap[nx][y] = tempMap[nx - 1][y];
                                tempMap[nx - 1][y] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        } else if (direction == 2) { // 오른쪽으로 밀기

            for (int x = N - 1; x >= 0; x--) {
                for (int y = N - 1; y >= 0; y--) {
                    if (tempMap[x][y] != 0) {
                        int ny = y;

                        while (true) {
                            ny++;
                            if (ny >= N) break;

                            if (tempMap[x][ny] == 0) {
                                tempMap[x][ny] = tempMap[x][ny - 1];
                                tempMap[x][ny - 1] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            for (int x = N - 1; x >= 0; x--) {
                for (int y = N - 1; y > 0; y--) {
                    if (tempMap[x][y] == tempMap[x][y - 1]) {
                        tempMap[x][y] += tempMap[x][y - 1];
                        tempMap[x][y - 1] = 0;
                        y--;
                    }
                }
            }

            for (int x = N - 1; x >= 0; x--) {
                for (int y = N - 1; y >= 0; y--) {
                    if (tempMap[x][y] != 0) {
                        int ny = y;

                        while (true) {
                            ny++;
                            if (ny >= N) break;

                            if (tempMap[x][ny] == 0) {
                                tempMap[x][ny] = tempMap[x][ny - 1];
                                tempMap[x][ny - 1] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        } else if (direction == 3) { // 위로 밀기

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (tempMap[x][y] != 0) {
                        int nx = x;

                        while (true) {
                            nx--;
                            if (nx < 0) break;

                            if (tempMap[nx][y] == 0) {
                                tempMap[nx][y] = tempMap[nx + 1][y];
                                tempMap[nx + 1][y] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N - 1; x++) {
                    if (tempMap[x][y] == tempMap[x + 1][y]) {
                        tempMap[x][y] += tempMap[x + 1][y];
                        tempMap[x + 1][y] = 0;
                        x++;
                    }
                }
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (tempMap[x][y] != 0) {
                        int nx = x;

                        while (true) {
                            nx--;
                            if (nx < 0) break;

                            if (tempMap[nx][y] == 0) {
                                tempMap[nx][y] = tempMap[nx + 1][y];
                                tempMap[nx + 1][y] = 0;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }


    }
}
