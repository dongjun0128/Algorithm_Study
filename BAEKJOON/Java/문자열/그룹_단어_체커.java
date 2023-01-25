package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 그룹_단어_체커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.valueOf(br.readLine());
        int cnt=0;

        while(N>0){
            String input = br.readLine();

            if(checkGroupWord(input)) cnt++;
            N--;
        }

        System.out.println(cnt);

    }

    public static boolean checkGroupWord(String str){
        char p = str.charAt(0);
        String convertStr = "";
        String check = "";

        convertStr+=p;
        for(int i = 0 ; i < str.length(); i++){
            if(str.charAt(i) != p){
                p = str.charAt(i);
                convertStr+=p;
            }
        }

        for(char ch : convertStr.toCharArray()){
            if(check.contains(""+ch)) return false;
            check+=ch;
        }

        return true;
    }
}
