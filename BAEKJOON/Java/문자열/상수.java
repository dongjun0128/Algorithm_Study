package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 상수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        int A = Integer.valueOf(reverseString(input[0]));
        int B = Integer.valueOf(reverseString(input[1]));

        System.out.println(Math.max(A,B));
    }

    static public String reverseString(String str){
        String reverse = "";

        for(int i = str.length() - 1; i >= 0 ; i--){
            reverse+=str.charAt(i);
        }

        return reverse;
    }
}
