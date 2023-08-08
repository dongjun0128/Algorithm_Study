package 슬라이딩_윈도우.P12891_DNA_비밀번호;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int S;
    static int P;
    static String input;
    static int standardA;
    static int standardC;
    static int standardG;
    static int standardT;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/슬라이딩_윈도우/P12891_DNA_비밀번호/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        input = st.nextToken();

        st = new StringTokenizer(br.readLine());

        standardA = Integer.parseInt(st.nextToken());
        standardC = Integer.parseInt(st.nextToken());
        standardG = Integer.parseInt(st.nextToken());
        standardT = Integer.parseInt(st.nextToken());

        int[] cntAlphabet = new int[26];

        int start = 0;
        int end = 0;

        while (end < P) {
            char ch = input.charAt(end++);
            cntAlphabet[ch - 'A']++;
        }
        isSafePwd(cntAlphabet);

        while (end < S) {
            char ch1 = input.charAt(start++);

            cntAlphabet[ch1 - 'A']--;

            char ch2 = input.charAt(end++);

            cntAlphabet[ch2 - 'A']++;
            isSafePwd(cntAlphabet);
        }

        System.out.println(answer);
    }

    public static void isSafePwd(int[] cntAlphabet) {
        boolean flag = true;

        for (int i = 0; i < 26; i++) {
            if (i == 0) {
                if (cntAlphabet[i] < standardA) {
                    flag = false;
                    break;
                }
            } else if (i == 2) {
                if (cntAlphabet[i] < standardC) {
                    flag = false;
                    break;
                }
            } else if (i == 6) {
                if (cntAlphabet[i] < standardG) {
                    flag = false;
                    break;
                }
            } else if (i == 19) {
                if (cntAlphabet[i] < standardT) {
                    flag = false;
                    break;
                }
            } else {
                if (cntAlphabet[i] != 0) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag == true) {
            answer++;
        }
    }
}