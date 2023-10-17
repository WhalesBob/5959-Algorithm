package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Point {
	int x; // 가로 - 열 방향
	int y; // 세로 - 행 방향

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_1347 {

	// 서 남 동 북 순서
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point lt, rb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		br.readLine();

		char[] moves = br.readLine().toCharArray();

		// 상하좌우 네 방향으로 50 씩 나갈 수 있으므로 크기를 넉넉하게 잡고, (50, 50)에서 시작
		boolean[][] maze = new boolean[105][105];
		lt = new Point(50, 50);
		rb = new Point(50, 50);
		Point curPos = new Point(50, 50);

		// 초기 홍준이의 상태 설정
		maze[curPos.y][curPos.x] = true; // 출발 위치 이동할 수 있는 칸으로 설정
		int direction = 1; // 남쪽을 바라본다.

		for (char m : moves) {
			switch (m) {
			case 'R':
				direction = direction == 0 ? 3 : direction - 1;
				break;
			case 'L':
				direction = direction == 3 ? 0 : direction + 1;
				break;
			default:
				// 이동
				curPos = new Point(curPos.x + dx[direction], curPos.y + dy[direction]);
				maze[curPos.y][curPos.x] = true; // 이동할 수 있는 칸으로 표시

				// 미로 범위 설정
				if (lt.x > curPos.x) {
					lt.x = curPos.x;
				}
				if (lt.y > curPos.y) {
					lt.y = curPos.y;
				}
				if (rb.x < curPos.x) {
					rb.x = curPos.x;
				}
				if (rb.y < curPos.y) {
					rb.y = curPos.y;
				}
			}

		}

		for (int i = lt.y; i <= rb.y; i++) {
			for (int j = lt.x; j <= rb.x; j++) {
				if (maze[i][j])
					sb.append('.'); // 범위 내에서 홍준이가 이동했던 칸
				else
					sb.append('#'); // 범위 내에서 홍준이가 돌아다니지 않은 곳은 벽.
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
