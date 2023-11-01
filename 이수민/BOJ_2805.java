package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805 {
	
	static int n;
	static long m, trees[];
	static long min, max, result;
	
	private static boolean check(long h) {
		long sum = 0;
		
		for(long t : trees) {
			sum += t < h ? 0: (t-h); // 현재 나무의 높이가 자르려는 높이보다 낮다면 0, 높다면 자를 수 있다. (마이너스 값 방지)
			if (sum >= m) return true; // 현재 높이로 필요한 만큼 나무를 자를 수 있다면 true
		}
		
		return false; // 현재 높이로 필요한 만큼 나무를 자를 수 없음
	}
	
	private static void binarySearch() {
		while(min <= max) {
			long mid = (min+max)/2;
			if (check(mid)) {
				min = mid+1;
				result = mid; // 지금 mid가 h의 최댓값
			}
			else { 
				max = mid-1;
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
			if (max < trees[i]) max = trees[i]; // 나무의 최댓값으로 절단기의 최대 높이 설정
		}
		
		
		binarySearch();
		System.out.println(result);
		
	}
}
