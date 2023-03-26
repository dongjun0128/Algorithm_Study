package 삼성SW기출.P14890_경사로;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14890_경사로/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        LToR();
        UToD();

        System.out.println(result);
    }

    static void LToR() {
        Loop1:
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];

            Loop2:
            for (int j = 1; j < N; j++) {

                if (Math.abs(map[i][j - 1] - map[i][j]) > 1) break;
                else if (Math.abs(map[i][j - 1] - map[i][j]) == 1) {
                    if (map[i][j - 1] - map[i][j] > 0) { // 내려가는 경우

                        for (int k = j + 1; k <= j + L - 1; k++) {
                            if (k < 0 || k >= N) break Loop2;
                            if (map[i][j] != map[i][k]) break Loop2;
                            if (visited[k] == true) break Loop2;
                        }

                        for (int k = j; k <= j + L - 1; k++) {
                            visited[k] = true;
                        }

                        //j += L - 1;
                    } else { // 올라가는 경우

                        for (int k = j - 1; k >= j - L; k--) {
                            if (k < 0 || k >= N) break Loop2;
                            if (map[i][j - 1] != map[i][k]) break Loop2;
                            if (visited[k] == true) break Loop2;
                        }

                        for (int k = j - 1; k >= j - L; k--) {
                            visited[k] = true;
                        }
                    }
                }

                if (j == N - 1) {
                    result++;
                    //System.out.println(i);
                }
            }
        }
    }

    static void UToD(){
        Loop1:
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];

            Loop2:
            for (int j = 1; j < N; j++) {

                if (Math.abs(map[j - 1][i] - map[j][i]) > 1) break;
                else if (Math.abs(map[j - 1][i] - map[j][i]) == 1) {
                    if (map[j - 1][i] - map[j][i] > 0) { // 내려가는 경우

                        for (int k = j + 1; k <= j + L - 1; k++) {
                            if (k < 0 || k >= N) break Loop2;
                            if (map[j][i] != map[k][i]) break Loop2;
                            if (visited[k] == true) break Loop2;
                        }

                        for (int k = j; k <= j + L - 1; k++) {
                            visited[k] = true;
                        }

                        //j += L - 1;
                    } else { // 올라가는 경우

                        for (int k = j - 1; k >= j - L; k--) {
                            if (k < 0 || k >= N) break Loop2;
                            if (map[j - 1][i] != map[k][i]) break Loop2;
                            if (visited[k] == true) break Loop2;
                        }

                        for (int k = j - 1; k >= j - L; k--) {
                            visited[k] = true;
                        }
                    }
                }

                if (j == N - 1) {
                    result++;
                    //System.out.println(i);
                }
            }
        }
    }
}
