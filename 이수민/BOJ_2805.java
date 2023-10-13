package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805 {
	
	static int n;
	static long m, trees[];
	static long min, max, result;
	
	// 나무를 필요한 만큼 자를 수 있는지 확인
	private static boolean check(long h) {
		long sum = 0;
		
		for(long t : trees) {
			sum += t < h ? 0: (t-h); // 자르는 높이보다 나무가 낮다면 자르지 않음
			if (sum >= m) return true; // 필요한 양과 같거나 더 많이 자를 수 있는 경우
		}
		
		return false; // 필요한 양만큼 자를 수 없는 경우
	}
	
	// 이분탐색
	private static void binarySearch() {
		while(min <= max) {
			long mid = (min+max)/2;
			if (check(mid)) { // 자를 수 있다면, 
				min = mid+1; 
				result = mid; // 지금까지 설정할 수 있는 높이의 최댓값
			}
			else { // 자를 수 없는 경우
				max = mid-1; // 높이를 낮춰서 찾으러 가기
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Long.parseLong(st.nextToken());
		
		trees = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			trees[i] = Long.parseLong(st.nextToken());
			if (max < trees[i]) max = trees[i]; // 자를 수 있는 높이의 최댓값 찾기
		}
		
		binarySearch();
		System.out.println(result);
		
	}
}
