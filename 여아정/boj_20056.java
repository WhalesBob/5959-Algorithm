package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class boj_20056 {

	static class FireBall implements Comparable<FireBall> {
		int x;
		int y;
		int weight;
		int speed;
		int way;
		int cnt = 1;
		int evenOdd = 0;

		public FireBall(int x, int y, int weight, int speed, int way) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.speed = speed;
			this.way = way;
			int cnt = 1;
			int evenOdd = 0;
		}

		@Override
		public int compareTo(FireBall o) {
			if (x == o.x) {
				if (y == o.y) {
					return weight - o.weight;
				}
				return y - o.y;
			}
			return x - o.x;
		}
	}

	static int N, M, K;
	static List<FireBall> list;
	static Deque<FireBall> q;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] evoX = { 0, 2, 4, 6 };// 0, 2, 4, 6
	static int[] mixX = { 1, 3, 5, 7 };// 1, 3, 5, 7

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list=new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// r,c,질량,속력,방향
			list.add(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		for (int i = 0; i < K; i++) {
			bfs();
		}

		System.out.println(chkFireBall());
	}

	private static int chkFireBall() {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).weight;
		}
		return sum;
	}

	private static void bfs() {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			FireBall fire = list.get(i);
			int nx = (N + fire.x + (dx[fire.way] * (fire.speed % N))) % N;
			int ny = (N + fire.y + (dy[fire.way] * (fire.speed % N))) % N;

			list.add(new FireBall(nx, ny, fire.weight, fire.speed, fire.way));
		}
		
		for (int i = 0; i < size; i++) {
			list.remove(0);
		}
		fireChange();
	}

	private static void fireChange() {
		Collections.sort(list);

		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).weight == 0) {
				list.remove(i);
				i--;
				continue;
			}
			// 위치가 같을 때
			if (list.get(i).x == list.get(i + 1).x && list.get(i).y == list.get(i + 1).y) {
				list.get(i).cnt++;
				list.get(i).speed += list.get(i + 1).speed;
				list.get(i).weight += list.get(i + 1).weight;
				if (list.get(i).evenOdd >= 0) {// 홀짝같음
					list.get(i).evenOdd = list.get(i).way % 2 == list.get(i + 1).way % 2 ? list.get(i).way % 2 : -1;
				}
				list.remove(i + 1);
				i--;
			}
		}

		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).cnt > 1) {// 합쳐짐
				int[] useWay = new int[4];
				if (list.get(i).evenOdd < 0) {// 홀짝 다름
					useWay = mixX.clone();
				} else {
					useWay = evoX.clone();
				}

				FireBall now = list.get(i);
				int wei = now.weight / 5;
				if (wei <= 0)
					continue;
				int sped = now.speed / now.cnt;
				for (int j = 0; j < 4; j++) {
					list.add(new FireBall(now.x, now.y, wei, sped, useWay[j]));
				}
			} else {
				list.add(list.get(i));
			}
		}

		for (int i = 0; i < size; i++) {
			list.remove(0);
		}
	}

}