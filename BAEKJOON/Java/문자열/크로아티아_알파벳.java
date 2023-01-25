package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크로아티아_알파벳 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        String[] replaceWord = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for(String i : replaceWord){
            input = input.replaceAll(i,"*");
        }
        System.out.println(input.length());

    }
}
