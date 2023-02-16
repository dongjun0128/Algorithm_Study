package 조합론.P1722_순열의_순서;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] fact = new long[21];
    static boolean[] visited;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/조합론/P1722_순열의_순서/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

        fact[0] = 1;
        for (int i = 1; i <= 20; i++) {
            fact[i] = fact[i - 1] * i;
        }

        st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();

        if(command == 1){
            long target = Long.parseLong(st.nextToken());

            for (int i = 0; i < N; i++) {
                for (int j = 1; j <= N ; j++) {
                    if(visited[j] == true) continue;

                    if(target > fact[N - i - 1]){
                        target -= fact[N - i - 1];
                    } else{
                        sb.append(j);
                        sb.append(" ");
                        visited[j] = true;
                        break;
                    }
                }
            }

        } else if (command == 2) {
            nums = new int[N];

            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            long result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 1; j < nums[i]; j++) {
                    if( visited[j] == false){
                        result += fact[N - i - 1];
                    }
                }
                visited[nums[i]] = true;
            }

            sb.append(result + 1);
        }

        System.out.println(sb);
    }
}
