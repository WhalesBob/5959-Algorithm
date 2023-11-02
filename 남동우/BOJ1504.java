import java.io.*;
import java.util.*;

public class BOJ1504 {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        long[][] matrix = makeMatrix(br, vertexCount, edgeCount);
      // 시작점, 종료 지점, weight 정보를 입력받아 2차원 배열을 만듭니다. 
      // 정점 갯수가 최대 800개이므로, 충분히 메모리 초과가 나지 않을 것이라 생각하여
      // 인접 matrix 를 만들어 줍니다. 

      
      // floyd-warshall 알고리즘을 수행해, 모든 임의의 정점에서 다른 정점으로 가는
      // 최소 거리를 만들어 줍니다.
        for(int k = 1; k < matrix.length; k++){
            for(int i = 1; i < matrix.length; i++){
                for(int j = 1; j < matrix.length; j++){
                    if(matrix[i][j] > matrix[i][k] + matrix[k][j]){
                        matrix[i][j] = matrix[j][i] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }

      // 꼭 지나야 하는 지점을 입력받습니다.
        st = new StringTokenizer(br.readLine()," ");
        int passOne = Integer.parseInt(st.nextToken());
        int passTwo = Integer.parseInt(st.nextToken());

      // passOne, passTwo 를 지나 마지막 점으로 가는 점과, passTwo, passOne 순으로 지나 마지막 점으로
      // 가는 거리를 만들어 줍니다.
        long rootOne = matrix[1][passOne] + matrix[passOne][passTwo] + matrix[passTwo][matrix.length - 1];
        long rootTwo = matrix[1][passTwo] + matrix[passTwo][passOne] + matrix[passOne][matrix.length - 1];

      // 둘 중 최소를 result 로 만들어 줍니다. 이때, 최소값이 INF 보다 크거나 같다면, 가는 것이 불가능한
      // 케이스입니다. 
      // 가는 것이 불가능한 케이스는 -1, 아니면 결과를 출력합니다.
        long result = Math.min(rootOne, rootTwo);
        System.out.println(result >= INF ? -1 : result);
    }
    static long[][] makeMatrix(BufferedReader br, int vertexCount, int edgeCount) throws IOException {
        long[][] matrix = new long[vertexCount + 1][vertexCount + 1];
        for(int i = 1; i < matrix.length; i++){
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        for(int i = 0; i < edgeCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(matrix[a][b] > weight){
                matrix[a][b] = matrix[b][a] = weight;
            }
        }
        return matrix;
    }
}
