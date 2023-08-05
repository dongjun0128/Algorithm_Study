package 누적합.P24548_도로정보;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/누적합/P24548_도로정보/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        char[] arr = new char[N];

        st = new StringTokenizer(br.readLine());
        String temp = st.nextToken();
        for (int i = 0; i < N; i++) {
            arr[i] = temp.charAt(i);
        }

        int[] counts = new int[4]; // T, G, F, P의 개수
        int result = 0;

        for (int start = 0, end = 0; end < N; end++) {
            char current = arr[end];
            if (current == 'T') {
                counts[0]++;
            } else if (current == 'G') {
                counts[1]++;
            } else if (current == 'F') {
                counts[2]++;
            } else if (current == 'P') {
                counts[3]++;
            }

            while (counts[0] % 3 == 0 && counts[1] % 3 == 0 &&
                    counts[2] % 3 == 0 && counts[3] % 3 == 0) {
                result += N - end; // 현재 위치부터 끝까지 가능한 모든 구간의 개수를 더합니다.

                // start 위치의 물체 개수를 감소시키고 start를 증가시킵니다.
                char removed = arr[end];
                if (removed == 'T') {
                    counts[0]--;
                } else if (removed == 'G') {
                    counts[1]--;
                } else if (removed == 'F') {
                    counts[2]--;
                } else if (removed == 'P') {
                    counts[3]--;
                }
                start++;
            }
        }

        System.out.println(result);
    }
}
