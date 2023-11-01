import java.io.*;
import java.util.*;

public class boj2805 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 나무의 수
		int m = Integer.parseInt(st.nextToken()); // 나무의 길이
		int[] heights = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			heights[i] = Integer.parseInt(st.nextToken()); // 나무의 높이
		}
		Arrays.sort(heights); // 정렬
		
		int start = 0, end = heights[n-1], mid;
		long sum; /*** 타입 잘 생각하자. 제발. ***/
		
		while (start <= end) {
			// 절단기에 설정한 높이
			mid = (start + end) / 2;
			
			// 나무 자르기
			sum = 0;
			for (int h: heights) {
				if (h <= mid) {
					continue;
				}
				sum += h - mid;
			}
			
			// 자른 나무 높이들의 합이 크면 높이 늘리기
			if (sum >= m) {
				start = mid + 1;
			} 
			// 자른 나무 높이들의 합이 작으면 높이 줄이기
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(end);
	}

}
