package 삼성SW기출.P20058_마법사_상어_파이어스톰;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P20058_마법사_상어_파이어스톰/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        n = 1;
        for (int i = 0; i < N; i++) {
            n *= 2;
        }

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int l = 1;

            for (int i = 0; i < L; i++) {
                l *= 2;
            }

            rotation(l);
        }


    }

    static void rotation(int l) {
        int[][] rotationMap = new int[l][l];

        for (int i = 0; i < n; i+=l) {
            for (int j = 0; j < n; j+=l) {
                rotationMap = rotationProcess(i,j,l,rotationMap);
            }
        }

        map = rotationMap;
    }

    static int[][] rotationProcess(int x, int y, int l, int[][] rotationMap) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                rotationMap[y + j][x + l - i - 1] = map[y + i][x + j];
            }
        }

        return rotationMap;
    }
}
