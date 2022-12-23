package Level_2.Stack;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Stack;

public class 올바른_괄호 {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '('){
                stack.add(ch);
            }
            if(ch == ')'){
                if(stack.size() == 0) return false; // 뒤의 괄호가 먼저 들어오면 올바르지 않은 괄호
                stack.pop();
            }
        }

        if(!stack.isEmpty()) return false; // 모두 끝났는데 stack에 괄호가 남아있으면 올바르지 않은 괄호
        return true;
    }

    @Test
    public void 정답(){
        Assert.assertEquals(true, solution("()()"));
        Assert.assertEquals(true, solution("(())()"));
        Assert.assertEquals(false, solution(")()("));
        Assert.assertEquals(false, solution("(()("));
    }
}
