import java.io.*;
import java.util.StringTokenizer;

public class BOJ14889 {
    static int min;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] matrix = makeMatrix(br, n);
        min = Integer.MAX_VALUE;

        combination(matrix,new boolean[n+1], 1,1); // combination 을 순회하며, start 팀을 뽑고,
      // 팀 간 격차의 최소값을 안에서 업데이트합니다.
        System.out.println(min); // 최소값을 출력합니다.
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];

        for(int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
    static void combination(int[][] matrix, boolean[] visited, int startIndex, int size) {
        if(size > n/2){ // 팀원이 반수를 넘으면, 스타트 팀과 링크 팀을 true, false 를 기준으로 분류합니다.
            int start = 0, link = 0;
          // 이중 for문을 돌면서, 뽑은 두명 다 true 면 start 팀에 수치를 집어넣고, 둘 다 false면 link 팀에 더합니다.
            for(int pickIndex = 0; pickIndex < n; pickIndex++) {
                for(int playerIndex = 0; playerIndex < n; playerIndex++) {
                    if (visited[pickIndex] && visited[playerIndex]) {
                        start += matrix[pickIndex][playerIndex];
                    }
                    if (!visited[pickIndex] && !visited[playerIndex]) {
                        link += matrix[pickIndex][playerIndex];
                    }
                }
            }
          
// 연산을 해 준 다음, min 을 업데이트해 줍니다.
            if(min > Math.abs(start - link)){
                min = Math.abs(start- link);
            }
            return;
        }
// 조합을 진행합니다.
        for(int i = startIndex; i < n; i++) {
            visited[i] = true;
            combination(matrix,visited, i + 1, size + 1);
            visited[i] = false;
        }
    }
}
