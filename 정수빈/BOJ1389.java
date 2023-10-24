import java.util.*;
import java.io.*;

public class BOJ1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for(int i=0; i<N; i++)
            Arrays.fill(map[i], Integer.MAX_VALUE);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;

            map[n1][n2] = 1;
            map[n2][n1] = 1;
        }

        for(int k=0; k<N; k++) { // 경유지
            for(int i=0; i<N; i++) { // 출발지
                for(int j=0; j<N; j++) { // 도착지
                    if(i == j || i == k || j == k) continue;

                    // 서로 연결될 수 있을 때, 진행한다
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue;

                    // 기존의 값이 더 작은지, 경유지를 거쳤을 때의 값이 더 작은지 비교한다
                    map[i][j] = map[i][j] > map[i][k]+map[k][j]+1 ? map[i][k]+map[k][j]+1 : map[i][j];
                }
            }
        }

        // 출력한다
        int person = 0;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            int count = 0;

            for(int j=0; j<N; j++)
                // 제자리 값은 포함하지 않는다
                if (i != j) count += map[i][j];

            if(min > count) {
                person = i;
                min = count;
            }
        }

        // 케빈 베이컨 수가 가장 작은 사람을 출력한다
        System.out.println(person+1);
    }
}
