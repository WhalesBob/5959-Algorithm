import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12761{
    
	static int[] list;
	static int[] answer;
	static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new int[M + 1];
		answer = new int[100001];

		bfs(A, B, N, M);

	}

	private static void bfs(int A, int B, int N, int M) {

		Queue<Integer> q = new LinkedList<>();

		q.add(N);

		int[] move = { 1, -1, -A, A, -B, B};
		int[] jump = {A, B};
		cnt = 0;
		
		while (!q.isEmpty()) {

			int size = q.size();

			for (int s = 0; s < size; s++) {

				int current = q.poll();
                
                // + 이동
				for (int i = 0; i < 6; i++) {

					int next = current + move[i];

					if (next < 0 || next >= 100001 || answer[next] != 0) {
						continue;
					}
					
					if (next == M) {
						System.out.println(cnt + 1);
						return;
					}
					answer[next] = answer[current] + 1;
					q.add(next);

				}
				
                // 몇배
				for (int i = 0; i < 2; i++) {

					int next = current * jump[i];

					if (next < 0 || next >= 100001 || answer[next] != 0) {
						continue;
					}
					
					if (next == M) {
						System.out.println(cnt + 1);
						return;
					}
					answer[next] = answer[current] + 1;
					q.add(next);

				}


			}
			cnt++;

		}

	}
}
