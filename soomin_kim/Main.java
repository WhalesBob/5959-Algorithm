import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 15565번: 귀여운 라이언
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 입력
		int[] cute = new int[n];
		List<Integer> lion = new ArrayList<>(); // 라이언의 위치를 저장하는 리스트
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			cute[i] = Integer.parseInt(st.nextToken());
			if(cute[i] == 1) lion.add(i); // 라이언의 위치를 저장한다.
		}
		
		int start = 0; // 슬라이딩 윈도우 start 지점
		int end = k-1; // 슬라이딩 윈도우 end 지점
		int len = Integer.MAX_VALUE; // 연속적인 길이를 저장하는 변수
		
		// 라이언의 위치를 보면서 슬라이딩 윈도우 적용
		while(end < lion.size()) {
			System.out.println();
			len = Math.min(lion.get(end) - lion.get(start) + 1, len);
			start++;
			end++;
		}
		
		if(lion.size() < k) System.out.println(-1);
		else System.out.println(len);
	}
}
