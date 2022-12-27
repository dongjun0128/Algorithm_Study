package Level_2.DFSBFS;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class 여행경로 {
    String[] answer;
    ArrayList<String> allRoute;
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length + 1];
        visited = new boolean[tickets.length];
        allRoute = new ArrayList<>();

        dfs(tickets, "ICN", "ICN", 0);

        Collections.sort(allRoute);
        answer = allRoute.get(0).split(" ");

        return answer;
    }

    public void dfs(String[][] tickets, String depart, String route, int depth) {
        if (depth == tickets.length) {
            allRoute.add(route);
            return;
        } // 종료조건

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(depart) && visited[i] == false) { // 방문하지 않은 티켓 중 출발지가 같은 티켓 찾기
                visited[i] = true;
                dfs(tickets, tickets[i][1], route + " " + tickets[i][1], depth + 1);
                visited[i] = false;
            }
        }

    }

    @Test
    public void 정답() {
        Assert.assertEquals(new String[]{"ICN", "JFK", "HND", "IAD"}, solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}));
        Assert.assertEquals(new String[]{"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}, solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}));
    }
}
