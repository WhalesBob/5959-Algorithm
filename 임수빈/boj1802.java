import java.io.*;

public class boj1802 {
	
	static char[] paper;
	static int size;
	static int i, j;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			paper = br.readLine().toCharArray();			
			solution();
		}
		
		System.out.print(sb);
	}
	
	static void solution() {
		// 종이의 길이
		size = paper.length;
		// 비교할 인덱스
		i = size / 2 - 1;
		j = size / 2 + 1;
		
		while (size > 1) {
			// 대칭되는 인덱스가 같은 값이면 접을 수 없다.
			if (paper[i] == paper[j]) {
				sb.append("NO\n");
				return;
			}
			
			i--;
			j++;
			
			// 다시 접기
			if (i < 0 || j >= size) {
				size /= 2;
				i = size / 2 - 1;
				j = size / 2 + 1;
			}
		}
		
		sb.append("YES\n");
	}

}
