import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
    static int N,M;
    static int[][] adj;
    static boolean[][] visit;

    static class Edge {
        int s;
        int e;
        int cost;
        public Edge(int s,int e,int cost){
            this.s=s;
            this.e=e;
            this.cost=cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken()); // 유저 수
        M=Integer.parseInt(st.nextToken()); // 관계 수
        adj=new int[N+1][N+1];
        visit=new boolean[N+1][N+1];

        for(int i=1;i<=N;i++){
            visit[i][i]=true;
        }

        Queue<Edge> q=new LinkedList<>();
        while (M-->0){
            st=new StringTokenizer(br.readLine());
            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            adj[s][e]=1;
            adj[e][s]=1;
            visit[s][e]=true;
            visit[e][s]=true;
            q.offer(new Edge(e,s,1));
            q.offer(new Edge(s,e,1));
        }

        while (!q.isEmpty()) {
            Edge now=q.poll();
            for(int i=1;i<=N;i++){
                if(now.e==i) continue;
                if(visit[now.e][i]){ // 현재에서 갈 수 있는곳 탐색
                    if(!visit[now.s][i]){ // 아직 방문안함 -> 가는 방법 없음
                        adj[now.s][i]=adj[now.s][now.e]+adj[now.e][i];
                        visit[now.s][i]=true;
                        q.offer(new Edge(now.s,i,adj[now.s][now.e]+adj[now.e][i]));
                    }
                }
            }
        }

        // 케빈 베이컨 제일 작은 수 찾기
        int ans=1;
        int sum=Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            int s=0;
            for(int j=1;j<=N;j++){
                s+=adj[i][j];
            }
            if(sum>s){
                sum=s;
                ans=i;
            }
        }
        System.out.println(ans);
    }
}
