package 삼성SW기출.P14888_연산자_끼워넣기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] numbers;
    static int[] signs;
    static ArrayList<ArrayList<Character>> combination;
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14888_연산자_끼워넣기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        signs = new int[4];
        combination = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            signs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < signs[i]; j++) {
                dfs(i,new ArrayList<>());
            }
        }

        for (int i = 0; i < combination.size(); i++) {
            ArrayList<Character> temp = combination.get(i);
            int result = numbers[0];

            for (int j = 0; j < temp.size(); j++) {
                if(temp.get(j) == '+') result += numbers[j+1];
                else if (temp.get(j) == '-') {
                    result -= numbers[j+1];
                } else if(temp.get(j) == '*'){
                    result *= numbers[j+1];
                } else if (temp.get(j) == '/') {
                    result /= numbers[j+1];
                }
            }

            maxValue = Math.max(result,maxValue);
            minValue = Math.min(result,minValue);

        }

        System.out.println(maxValue);
        System.out.println(minValue);

    }

    static void dfs(int sign, ArrayList<Character> combi) {
        // 1. 체크인
        signs[sign]--;
        combi.add(convert(sign));

        // 2. 목적지인가?
        if(combi.size() == N - 1){
            ArrayList<Character> temp = new ArrayList<>();
            for (int i = 0; i < combi.size(); i++) {
                temp.add(combi.get(i));
            }
            combination.add(temp);
        } else{
            // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < signs[i]; j++) {
                    // 4. 갈 수 있는가?

                    // 5. 간다.
                    dfs(i,combi);
                }

            }
        }


        // 6. 체크아웃
        signs[sign]++;
        combi.remove(combi.size() - 1);
    }

    static char convert(int sign) {
        if (sign == 0) return '+';
        else if (sign == 1) {
            return '-';
        } else if (sign == 2) {
            return '*';
        } else {
            return '/';
        }
    }
}
