package 삼성SW기출.P21608_상어_초등학교;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static ArrayList<ArrayList<Integer>> totalLikePersons;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21608_상어_초등학교/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        totalLikePersons = new ArrayList<>();

        for (int i = 0; i <= N * N; i++) {
            totalLikePersons.add(new ArrayList<>());
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            ArrayList<Integer> likePersons = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                likePersons.add(Integer.parseInt(st.nextToken()));
                totalLikePersons.get(num).add(likePersons.get(j));
            }

            int[][] subMap = new int[N + 1][N + 1];
            int maxLikePerson = 0;
            ArrayList<Node> maxLikeList = new ArrayList<>();

            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (map[j][k] == 0) {
                        for (int l = 0; l < 4; l++) {
                            int nx = j + dx[l];
                            int ny = k + dy[l];

                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (likePersons.contains(map[nx][ny])) {
                                    subMap[j][k]++;
                                }
                            }
                        }


                        if (subMap[j][k] > maxLikePerson) {
                            maxLikeList = new ArrayList<>();
                            maxLikePerson = subMap[j][k];
                            maxLikeList.add(new Node(j, k));
                        } else if (subMap[j][k] == maxLikePerson) {
                            maxLikeList.add(new Node(j, k));
                        }
                    }
                }
            }

            if (maxLikeList.size() == 1) {
                int x = maxLikeList.get(0).x;
                int y = maxLikeList.get(0).y;

                map[x][y] = num;
                visited[x][y] = true;
            } else {
                int[][] blankMap = new int[N + 1][N + 1];
                int maxBlankCnt = 0;
                ArrayList<Node> maxBlankList = new ArrayList<>();

                for (int j = 0; j < maxLikeList.size(); j++) {
                    int x = maxLikeList.get(j).x;
                    int y = maxLikeList.get(j).y;

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                            if (map[nx][ny] == 0) {
                                blankMap[x][y]++;
                            }
                        }
                    }

                    if (blankMap[x][y] > maxBlankCnt) {
                        maxBlankList = new ArrayList<>();
                        maxBlankList.add(new Node(x, y));
                        maxBlankCnt = blankMap[x][y];
                    } else if (blankMap[x][y] == maxBlankCnt) {
                        maxBlankList.add(new Node(x, y));
                    }
                }

                if (maxBlankList.size() == 1) {
                    map[maxBlankList.get(0).x][maxBlankList.get(0).y] = num;
                    visited[maxBlankList.get(0).x][maxBlankList.get(0).y] = true;
                } else {
                    Collections.sort(maxBlankList);
                    map[maxBlankList.get(0).x][maxBlankList.get(0).y] = num;
                    visited[maxBlankList.get(0).x][maxBlankList.get(0).y] = true;
                }

            }

        }

        long result = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                        if (totalLikePersons.get(map[i][j]).contains(map[nx][ny])) {
                            cnt++;
                        }
                    }
                }

                if (cnt == 1) result += 1;
                else if (cnt == 2) result += 10;
                else if (cnt == 3) result += 100;
                else if (cnt == 4) result += 1000;
            }
        }

        System.out.println(result);
    }

    static boolean isNearby(int x, int y, int a, int b) {
        if (Math.abs(x - a) + Math.abs(y - b) == 1) return true;
        else return false;
    }
}

class Node implements Comparable<Node> {
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
        } else return cmp1;
    }
}