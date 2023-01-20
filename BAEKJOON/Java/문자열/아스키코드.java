package 문자열;

import java.util.Scanner;

public class 아스키코드 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char number = scan.next().charAt(0);

        System.out.println((int)number);
    }
}
