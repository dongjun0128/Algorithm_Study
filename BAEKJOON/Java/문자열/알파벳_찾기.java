package 문자열;

import java.util.Scanner;

public class 알파벳_찾기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String str = "abcdefghijklmnopqrstuvwxyz";

        for(char ch : str.toCharArray()){
            System.out.print(input.indexOf(ch) + " ");
        }


    }

}
