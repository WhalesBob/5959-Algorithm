import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1389 {

    static int result;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        result = 0;

        // 인접행렬 생성
        int[][] adj = new int[n+1][n+1];
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from][to] = 1;
            adj[to][from] = 1;
        }

        // 각 정점을 기준으로 bfs 탐색한다.
        for(int i = 1; i<=n; i++){
                bfs(i, n, adj);
        }
        System.out.println(result);

    }

    public static void bfs(int index, int n, int[][] adj){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int count = 0;

        q.add(new int[] {index, 0});
        visited[index] = true; // 시작 정점 방문처리

        while(!q.isEmpty()){
            int[] now = q.poll();
            count += now[1];

            for(int i = 1; i<=n; i++){
                if(adj[now[0]][i] == 1 && !visited[i]){
                    q.add(new int[] {i, now[1]+1}); // 인접하고 방문하지 않은 정점을 q에 넣음
                    visited[i] = true;
                }
            }
        }

        if(min > count){
            min = count;
            result = index;
        }
    }
}
