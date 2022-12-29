package Level_2.해시;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 전화번호_목록 {
    public boolean solution(String[] phone_book) {
        // 1. phoneBook을 sorting한다.
        Arrays.sort(phone_book);

        // 2. 1중 Loop을 돌며 앞 번호가 뒷 번호의 접두어인지 확인한다.
        for (int i = 0; i < phone_book.length - 1; i++)
            if (phone_book[i + 1].startsWith(phone_book[i]))
                return false;

        // 3. 여기까지 오면 접두어가 없다는 것이다.
        return true;
    }

    @Test
    void 정답() {
        Assert.assertEquals(false, solution(new String[]{"119", "97674223", "1195524421"}));
        Assert.assertEquals(true, solution(new String[]{"123", "456", "789"}));
        Assert.assertEquals(false, solution(new String[]{"12", "123", "1235", "567", "88"}));
    }
}
