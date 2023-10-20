import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
	static int N, adjMatrix[][], baconIdx, baconNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 유저 수
		int M = Integer.parseInt(st.nextToken());	// 관계 수
		// 유저 관계 인접 행렬 및 입력
		adjMatrix = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = adjMatrix[b][a] = 1;
		}
		baconIdx = -1;					// 케빈 베이컨의 수가 가장 적은 사람
		baconNum = Integer.MAX_VALUE;	// 위에 해당하는 케빈 베이컨의 수
		// 사람 마다 BFS를 통해 케빈 베이컨 수 검토
		for(int i=1;i<=N;i++)
			BFS(i);
		// BFS 검토 마친 후 최종 케빈 베이컨 사람 출력
		System.out.println(baconIdx);
	}

	// 케빈 베이컨 수 검토하는 BFS
	static void BFS(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visited[] = new boolean[N+1];
		int sum=0;	// 케빈 베이컨의 수
		int level=1;// 친구 단계
		
		q.offer(idx);
		visited[idx]=true;
		
		while(!q.isEmpty()) {
			int size=q.size();
			for(int i=0;i<size;i++) {
				int cur = q.poll();
				for(int j=1;j<=N;j++) {
					if(adjMatrix[cur][j]==0 || visited[j])
						continue;
					q.offer(j);
					visited[j]=true;
					// 관계 발견할 때마다 단계 더하기
					sum+=level;
				}
			}
			// 다음 단계로 넘어감
			level++;
		}
		// 케빈 베이컨 제일 적은 사람 및 수 갱신
		if(sum<baconNum) {
			baconIdx=idx;
			baconNum=sum;
		}
	}
}
