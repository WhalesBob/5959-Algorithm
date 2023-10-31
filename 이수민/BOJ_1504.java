import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int idx;
    int cost;
    Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class BOJ_1504 {
    static int N, E;
    static ArrayList<Node>[] graph;
    static int v1, v2;

    static int inf = 200000 * 1000; // Integer.MAX_VALUE로 설정 시, 경로 탐색(findRoute)에서 오버플로우 나는 것을 방지

    // 다익스트라.
    static int Dijkstra(int start, int end){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, inf);

        dist[start] = 0;
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            // 이동했던 정점, 간선 다시 볼 수도 있음
            if (cur.cost > dist[cur.idx]) continue;

            for(Node node : graph[cur.idx]){

                if (dist[node.idx] > dist[cur.idx] + node.cost){
                    dist[node.idx] = dist[cur.idx] + node.cost;
                    queue.offer(new Node(node.idx, dist[node.idx]));
                }
            }
        }
        return dist[end];
    }

    static int findRoute(){
        // 1->v1->v2->n과 1->v2->v1->n 중 더 빠른 경로를 찾는다.
        long route1 = Dijkstra(1, v1) + Dijkstra(v1, v2) + Dijkstra(v2, N);
        long route2 = Dijkstra(1, v2) + Dijkstra(v2, v1) + Dijkstra(v1, N);

        if (route1 >= inf && route2 >= inf)
            return -1;

        if (route1 < route2)
            return (int)route1;
        else
            return (int)route2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        System.out.println(findRoute());
    }
}
