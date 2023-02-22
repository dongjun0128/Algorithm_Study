import java.util.Scanner;

public class Transform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        for(char ch : input.toCharArray()){
            if(ch == '[') System.out.print('{');
            else if (ch == ']') {
                System.out.print('}');
            }else {
                System.out.print(ch);
            }
        }
    }
}
