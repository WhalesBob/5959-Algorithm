import java.io.*;
import java.util.Arrays;

public class BOJ1347 {
	static int N; // 문자열 길이
	static int top=50, bottom=50, left=50, right=50; // 실제 지도 크기
	static char[][] map = new char[100][100];
	static String input;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, -1, 0, 1}; // 이동방향 R이면 인덱스 배열 +1, L이면 -1 이동
	static int idx = 0; // dr, dc 배열 인덱스 저장
	static int[] pos = {50, 50}; // 현재 위치
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		input = br.readLine();
		for(int i=0; i<100; i++) { // 배열 초기화
			Arrays.fill(map[i], '#');
		}
		move();
		for(int i=top; i<=bottom; i++) {
			for(int j=left; j<=right; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	static void move() {
		// 방문한 곳은 .으로 배열 바꿔주기
		// 이동할 때마다 가장 큰/작은 인덱스 기록해주기 (아래위, 양옆) -> 실제 지도크기 알아내야함
		map[pos[0]][pos[1]] = '.';
		for(int i=0; i<N; i++) {
			if(input.charAt(i) == 'R') {
				// 오른쪽으로 방향 전환
				idx++;
				if(idx >= 4) { // 방향 인덱스 범위 체크
					idx -= 4;
				}
			}
			else if(input.charAt(i) == 'L') {
				// 왼쪽으로 방향 전환
				idx--;
				if(idx < 0) { // 방향 인덱스 범위 체크
					idx += 4;
				}
			}
			else {
				// 앞으로 이동
				pos[0] += dr[idx];
				pos[1] += dc[idx];
				map[pos[0]][pos[1]] = '.'; // 방문 처리
				top = Math.min(pos[0], top); // 실제 이동범위 확인
				bottom = Math.max(pos[0], bottom);
				left = Math.min(pos[1], left);
				right = Math.max(pos[1], right);
			}
		}
	}
}
