import java.io.*;
import java.util.*;

public class boj2002 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 차의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 대근이가 적은 차량 번호 목록 -> 순서대로 저장
		Map<String, Integer> map = new HashMap<>();
		for (int i=0; i<n; i++) {
			map.put(br.readLine(), i);
		}

		// 영식이가 적은 차량 번호 목록 -> 번호로 저장
		Integer[] out = new Integer[n];
		for (int i=0; i<n; i++) {
			out[i] = map.get(br.readLine());
		}
		
		boolean[] visited = new boolean[n];
		int cnt = 0;
		int answer = 0;
		
		while (cnt < n) {
			// 나온 차량 목록에서 0부터 순서대로 인덱스 찾기
			int idx = Arrays.asList(out).indexOf(cnt);
			
			for (int i=0; i<idx; i++) {
				// 해당 인덱스보다 먼저 나온 차량들 중에 순서가 바뀐 차량이 있으면 정답 추가
				if (out[i] > cnt && !visited[i]) {
					answer++;
					visited[i] = true;
				}
			}
			
			cnt++;
		}
		
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
	}

}

