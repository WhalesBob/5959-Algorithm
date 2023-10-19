import java.io.*;
import java.util.*;

public class BOJ1389 {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int userCount = Integer.parseInt(st.nextToken());
        int friendCount = Integer.parseInt(st.nextToken());

      // 입력을 토대로, 친구 관계 그래프의 인접 배열을 만듭니다.
        long[][] matrix = makeMatrix(br, userCount, friendCount);
      // Floyd-Warshall 알고리즘을 활용하여, 정답을 도출합니다.
        System.out.println(getAnswer(matrix));
    }
    static long getAnswer(long[][] matrix){
      // Floyd-Warshall 알고리즘을 활용하여 그래프 요소 간의 최단거리를 구합니다.
        for(int k = 1; k < matrix.length; k++){
            for(int s = 1; s < matrix.length; s++){
                for(int d = 1; d < matrix.length; d++){
                    long value = Math.min(matrix[s][d], matrix[s][k] + matrix[k][d]);
                    if(matrix[s][d] > value){
                        matrix[s][d] = matrix[d][s] = value;
                    }
                }
            }
        }

      // 케빈 베이컨 수의 최소값과, 최소값을 가진 사람을 구하기 위해, min, minIndex 를 만들어 줍니다.
        long min = Long.MAX_VALUE;
        int minIndex = -1;

      // 최소값을 구하고, 최소값인 사람의 인덱스를 저장합니다.
        for(int y = 1; y < matrix.length; y++){
            int sum = 0;
            for(int x = 1; x < matrix.length; x++){
                sum += matrix[y][x];
            }

            if(sum < min){
                min = sum;
                minIndex = y;
            }
        }

      // 최소값을 가진 사람의 인덱스를 반환합니다.
        return minIndex;
    }
    static long[][] makeMatrix(BufferedReader br, int size, int friendCount) throws IOException {
        long[][] matrix = new long[size + 1][size + 1];

        for(int y = 1; y < matrix.length; y++){
            Arrays.fill(matrix[y], INF);
            matrix[y][y] = 0;
        }

        for(int i = 0; i < friendCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[a][b] = matrix[b][a] = 1;
            
        }
        return matrix;
    }
}
