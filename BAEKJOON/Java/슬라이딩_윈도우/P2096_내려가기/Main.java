package 슬라이딩_윈도우.P2096_내려가기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] map;
    static int[] maxMap;
    static int[] minMap;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/슬라이딩_윈도우/P2096_내려가기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[3];
        maxMap = new int[3];
        minMap = new int[3];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int maxLeft = 0;
            int minLeft = 0;

            for (int i = 0; i < 3; i++) {
                map[i] = Integer.parseInt(st.nextToken());

                if (n == 0) {
                    maxMap[i] = map[i];
                    minMap[i] = map[i];
                } else {
                    if (i == 0) {
                        maxLeft = maxMap[i];
                        maxMap[i] = Math.max(maxMap[i], maxMap[i + 1]) + map[i];

                        minLeft = minMap[i];
                        minMap[i] = Math.min(minMap[i], minMap[i + 1]) + map[i];
                    } else if (i == 1) {
                        int tempMaxLeft = maxMap[i];
                        maxMap[i] = Math.max(Math.max(maxLeft, maxMap[i]), maxMap[i + 1]) + map[i];
                        maxLeft = tempMaxLeft;

                        int tempMinLeft = minMap[i];
                        minMap[i] = Math.min(Math.min(minLeft, minMap[i]), minMap[i + 1]) + map[i];
                        minLeft = tempMinLeft;
                    } else if (i == 2) {
                        maxMap[i] = Math.max(maxLeft, maxMap[i]) + map[i];

                        minMap[i] = Math.min(minLeft, minMap[i]) + map[i];
                    }

                }
            }
        }

//        System.out.println(Arrays.toString(maxMap));
//        System.out.println(Arrays.toString(minMap));


        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxMap[i]);
        }

        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minMap[i]);
        }

        System.out.println(max + " " + min);

    }
}
