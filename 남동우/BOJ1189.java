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

        char[][] matrix = makeMatrix(br, height, width);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[matrix.length-1][0] = true;
        dfs(0, matrix.length -1 , matrix, visited, 1);

        System.out.println(targetCount);
    }
    static void dfs(int x, int y, char[][] matrix, boolean[][] visited, int count){
        if(count >= targetDistance){
            if(x == matrix[0].length-1 && y == 0){
                targetCount++;
            }
            return;
        }

        for(int i = 0; i < 4; i++){
            int nextX = x + direction.get(i).get(0);
            int nextY = y + direction.get(i).get(1);

            if(canGo(nextX, nextY, matrix) && !visited[nextY][nextX]){
                visited[nextY][nextX] = true;
                dfs(nextX, nextY, matrix, visited, count + 1);
                visited[nextY][nextX] = false;
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
