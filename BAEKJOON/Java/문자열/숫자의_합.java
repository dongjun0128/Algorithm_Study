package 문자열;

import java.util.Scanner;

public class 숫자의_합 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        String str = scan.next();
        int result = 0;

        for(char num : str.toCharArray()){
            result += (int)num - (int)'0';
        }

        System.out.println(result);
    }
}
