package Level_1.해시;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 완주하지_못한_선수 {
    public String solution(String[] participant, String[] completion) {

        Arrays.sort(participant);
        Arrays.sort(completion);

        for (int i = 0; i < participant.length; i++) {
            try {
                if (!participant[i].equals(completion[i])) return participant[i];
            } catch (Exception e) {
                return participant[i];
            }
        }


        return "list.get(0)";
    }

    @Test
    void 정답() {
        Assert.assertEquals("leo", solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        Assert.assertEquals("vinko", solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        Assert.assertEquals("mislav", solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }
}
