import java.io.*;
import java.util.*;

public class boj1389 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 유저의 수
        int m = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        // 최단 경로 그래프
        long[][] graph = new long[n+1][n+1];

        // 초기화
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i == j) { // 자기 자신으로 가는 비용은 0
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 직접 연결된 노드들의 비용은 1
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        // 최솟값 갱신
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = 0;
        int sum = Integer.MAX_VALUE;

        for (int i=1; i<=n; i++) {
            // 각 노드의 케빈 베이컨 수 구하기
            int kb = 0;
            for (int j=1; j<=n; j++) {
                kb += graph[i][j];
            }

            // 최솟값일 때 정답 갱신
            if (sum > kb) {
                sum = kb;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}
