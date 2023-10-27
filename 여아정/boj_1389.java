import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1389 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<List<Integer>> list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(list.get(x).contains(y) ||list.get(y).contains(x))continue;
			//양 방향 관계를 list에 넣어준다
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		int[] result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (list.get(i) == null)
				continue;
			Deque<Integer> q = new ArrayDeque<Integer>();
			boolean[] chk = new boolean[N + 1];
			q.add(i);
			chk[i] = true;
			
			int cnt = 0;

			//번호마다 큐를 돌려서 모두 방문한다
			while (!q.isEmpty()) {
				int size = q.size();

				while (size-- > 0) {
					int now = q.poll();

					result[i] += cnt;//도착시 단계 카운드

					for (int k = 0; k < list.get(now).size(); k++) {
						if (chk[list.get(now).get(k)])
							continue;
						q.add(list.get(now).get(k));
						chk[list.get(now).get(k)] = true;
					}
				}
				cnt++;//방문한 단계를 카운트 하여 계산에 사용한다
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		for (int i = 1; i <= N; i++) {
			if (result[i] == 0)
				continue;
			if (min > result[i]) {
				min = result[i];
				minIdx = i;
			}
		}
		System.out.println(minIdx);//최소의 값을 가지는 사람(인덱스)를 출력
	}
}