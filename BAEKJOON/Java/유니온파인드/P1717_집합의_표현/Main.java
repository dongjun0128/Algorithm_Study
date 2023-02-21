package 유니온파인드.P1717_집합의_표현;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/유니온파인드/P1717_집합의_표현/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N +1];

        for (int n = 0; n <= N; n++) {
            parents[n] = n;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0){ // a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다
                union(Math.min(a,b), Math.max(a,b));
            } else{ //command == 1
                // a와 b가 같은 집합인지 확인
                int rootA = find(a);
                int rootB = find(b);

                if (rootA != rootB) System.out.println("NO");
                else System.out.println("YES");
            }
        }
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        parents[rootA] = rootB;
    }

    static int find(int a){
        if(parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }

}
