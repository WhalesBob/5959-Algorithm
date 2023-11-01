import java.io.*;
import java.util.*;

public class BOJ15565 {
	static int N, K, result = Integer.MAX_VALUE; // 인형 개수, 라이언인형 개수, 결과(최소집합크기)
	static int[] input, doll;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N];
		doll = new int[3]; // 라이언, 어피치 인형 개수 배열(1,2만 사용)
		st = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		getSmall(); // K개 이상 라이언 포함하는 가장 작은 연속된 인형들의 집합 구하기
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1); // 집합이 없는 경우
		}
		else {
			System.out.println(result);
		}
	}
	
	static void getSmall() {
		int low = 0, high = 0;
		
		while(high < N) {
			// 1. 1이 K개 이상 포함될 때 까지 high 늘림
			if(doll[1] < K) {
				doll[input[high]]++;
				high++;
			}
			else {
				// 2. 1이 K개 이상 포함되면?
				// => low 늘리면서 범위에 1이 K개 포함되는지 확인, 포함 안될때까지 low증가
				while(doll[1] >= K) {
					result = Math.min(result, high-low);
					doll[input[low]]--;
					low++;
				}
			}
		}
		// 3. high가 배열 끝까지 모두 도달했고, 현재 1이 K개 이상이면?
		// => 1이 K개 포함 안될때까지 low 늘리며 최소 집합 크기 찾기
		while(doll[1] >= K) {
			result = Math.min(result, high-low);
			doll[input[low]]--;
			low++;
		}
	}
}
