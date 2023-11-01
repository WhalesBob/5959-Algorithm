package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		int len = 0;
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now == 1)
				list.add(i);//라이언인 경우만 리스트에 넣기
		}

		if (list.size() >= K) {//라이언 개수가 골라야하는 K수보다 큰경우만 탐색
			for (int i = 0; i <= list.size() - K; i++) {
				min = Math.min(min, list.get(i + K - 1) - list.get(i)+1);//계속 최소 거리 탐색
			}
		}

		if (min == Integer.MAX_VALUE) {//즉 min값이 한번도 갱신되지 않으므로 구할 수 없음!
			System.out.println("-1");
		} else {
			System.out.println(min);
		}

	}

}