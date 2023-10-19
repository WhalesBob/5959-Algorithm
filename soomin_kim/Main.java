package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String str = br.readLine();

		// 오른쪽으로 90도씩 회전
		int[][] move = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

		int d = 0; // 밑으로

		int y = 49;
		int x = 49;

		// 최소한의 범위를 출력하기 위함
		int miny = y;
		int minx = x;
		int maxy = y;
		int maxx = x;

		// 맵 선언 및 초기화
		char[][] map = new char[100][100];

		for (int i = 0; i < 100; i++)
			Arrays.fill(map[i], '#');

		// 방문처리
		map[y][x] = '.';

		for (int i = 0; i < str.length(); i++) {

			int now = str.charAt(i);

			switch (now) {
			case 'R':
				d++;
				if (d > 3)
					d = 0;
				break;
			case 'L':
				d--;
				if (d < 0)
					d = 3;
				break;
			case 'F':
				int ny = y + move[d][0];
				int nx = x + move[d][1];

				map[ny][nx] = '.';

				y = ny;
				x = nx;

				miny = Math.min(miny, y);
				minx = Math.min(minx, x);

				maxy = Math.max(maxy, y);
				maxx = Math.max(maxx, x);
			}
		}

		for (int i = miny; i <= maxy; i++) {
			for (int j = minx; j <= maxx; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
