import java.io.*;
import java.util.*;

public class BOJ1189 {
    static int targetDistance;
    static int targetCount;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        targetDistance = Integer.parseInt(st.nextToken());
        // 세로, 가로, 원하는 거리를 입력받습니다.

        char[][] matrix = makeMatrix(br, height, width); // 2차원 배열을 입력받습니다.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[matrix.length-1][0] = true;// 처음 시작 지점을 방문 체크 해 줍니다.
        dfs(0, matrix.length -1 , matrix, visited, 1); 
        // DFS를 순회하며, 원하는 이동 시간대에 오른쪽 위까지 도착할 수 있는 경우의 수가 몇 개 있는지 카운트합니다.

        // 정답을 출력합니다.
        System.out.println(targetCount);
    }
    static void dfs(int x, int y, char[][] matrix, boolean[][] visited, int count){
        if(count >= targetDistance){ // count 가 목표하는 이동거리에 도착했다면, 바로 해당 케이스를 확인해 줍니다.
            // 오른쪽 위에 도착했다면, targetCount 를 1 증가시킵니다.
            if(x == matrix[0].length-1 && y == 0){
                targetCount++;
            }
            return; // targetDistance 보다 더 이동하지 않도록 return 해줍니다.
        }

        // 위의 조건에 부합하지 않았다면, 4방향으로 이동하는 경우를 만들어 dfs 순회합니다.
        for(int i = 0; i < 4; i++){
            int nextX = x + direction.get(i).get(0);
            int nextY = y + direction.get(i).get(1);

            // 배열 밖으로 빠져나가지 않는지, 이미 방문한것은 아닌지 체크합니다.
            if(canGo(nextX, nextY, matrix) && !visited[nextY][nextX]){
                visited[nextY][nextX] = true; // 방문체크를 해주고, dfs 를 순회합니다.
                dfs(nextX, nextY, matrix, visited, count + 1);
                visited[nextY][nextX] = false; // 방문하지 않고 다른쪽으로 갈 수 있기에 false로 바꿔 줍니다.
            }
        }
    }
    static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException {
        char[][] matrix = new char[height][];
        for(int i = 0; i < height; i++){
            matrix[i] = br.readLine().trim().toCharArray();
        }
        return matrix;
    }
    static boolean canGo(int x, int y, char[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length) && matrix[y][x] != 'T';
    }
}
