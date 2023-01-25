package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 단어_공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int [] alphabet = new int[26];
        int max = 0;

        input = input.toLowerCase();

        for(char c : input.toCharArray()){
            alphabet[(int) c - 'a']++;
        }

        max = findMax(alphabet);

        if(checkDuplicate(max,alphabet)) System.out.println('?');
        else System.out.println( (char) (findMaxIndex(max,alphabet) + 'A'));
    }

    public static int findMax(int[] alphabet){
        int max = 0;

        for(int i = 0 ; i < alphabet.length ;i++){
            if(max < alphabet[i]) max = alphabet[i];
        }

        return max;
    }

    public static boolean checkDuplicate(int max, int[] alphabet){
        int cnt=0;

        for(int num : alphabet){
            if(max == num) cnt++;
        }

        if(cnt != 1) return true;
        return false;
    }

    public static int findMaxIndex(int max, int[] alphabet){
        for(int i = 0 ; i < alphabet.length; i++){
            if(max == alphabet[i]) return i;
        }

        return -1;
    }
}
