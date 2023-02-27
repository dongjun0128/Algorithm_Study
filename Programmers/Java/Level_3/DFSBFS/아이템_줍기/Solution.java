package Level_3.DFSBFS.아이템_줍기;

import java.util.*;

class Solution {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        map = new int[102][102];

        for(int i = 0 ; i<rectangle.length; i++){
            drawing(rectangle[i]);
        }

        for(int i = 0 ; i<rectangle.length; i++){
            erase(rectangle[i]);
        }

        map[itemX * 2][itemY * 2] = -1;

        // for(int i = 0 ; i < map.length ; i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(characterX * 2, characterY * 2));

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(map[nx][ny] == 1){
                    map[nx][ny] = map[node.x][node.y] + 1;
                    queue.add(new Node(nx,ny));
                }

                if(map[nx][ny] == -1){
                    answer = (map[node.x][node.y] + 1) / 2;
                    for(int k = 0 ; k < map.length ; k++){
                        System.out.println(Arrays.toString(map[k]));
                    }

                    return answer;
                }
            }
        }


        return answer;
    }

    public void drawing(int[] rectangle){
        int x1 = rectangle[0] * 2;
        int y1 = rectangle[1] * 2;
        int x2 = rectangle[2] * 2;
        int y2 = rectangle[3] * 2;

        for(int i = x1 ; i <= x2; i++){
            for(int j = y1 ; j <= y2 ; j++){
                if(i == x1 || i == x2 || j == y1 || j == y2){
                    map[i][j] = 1;
                } else{
                    map[i][j] = 0;
                }
            }
        }

        // for(int i = 0 ; i < 51 ; i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }

    }

    public void erase(int[] rectangle){
        int x1 = rectangle[0] * 2;
        int y1 = rectangle[1] * 2;
        int x2 = rectangle[2] * 2;
        int y2 = rectangle[3] * 2;

        for(int i = x1 + 1 ; i < x2; i++){
            for(int j = y1 + 1 ; j < y2 ; j++){
                map[i][j] = 0;
            }
        }
    }
}

class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}