package 문자열;

import java.io.*;
import java.util.Arrays;

public class 문자열_반복 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] strs = br.readLine().split(" ");
            int R = Integer.parseInt(strs[0]);
            String str = strs[1];

            for (int j = 0; j < str.length(); j++) {
                for(int k = 0 ; k < R ; k++)
                    System.out.print(str.charAt(j));
            }
            System.out.println();
        }

    }
}
