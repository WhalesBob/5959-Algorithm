import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1389 {

    static int n, m;
    static ArrayList<Integer>[] graph;

    public static int bfs(int start){
        int[] visited = new int[n+1];
        int sum = 0; // start의 케빈 베이컨의 수
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = 1;
        queue.add(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(Integer v : graph[cur]){
                if (visited[v] == 0){
                    visited[v] = visited[cur] + 1;
                    queue.add(v);
                    // 시작 시 1을 더해서 입력하고 있기 때문에 1을 빼줌
                    sum += visited[v] - 1;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int min = Integer.MAX_VALUE;
        int min_idx = 0; // 케빈 베이컨의 수가 가장 작은 사람

        // 모든 사람을 시작으로 케빈 베이컨 수가 가장 작은 사람을 찾기
        for(int i=1; i<=n; i++){
            int curSum = bfs(i);

            if (curSum < min){
                min = curSum;
                min_idx = i;
            }

        }

        System.out.println(min_idx);
    }
}
