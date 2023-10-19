import java.io.*;
import java.util.*;

class Player implements Comparable<Player> {
	int level;
	String nickname;
	
	public Player(int level, String nickname) {
		super();
		this.level = level;
		this.nickname = nickname;
	}

	@Override
	public int compareTo(Player o) { // 닉네임 순 정렬
		return this.nickname.compareTo(o.nickname);
	}

	@Override
	public String toString() {
		return level + " " + nickname;
	}
}

public class boj20006 {

	static int m;
	static List<List<Player>> players = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int p = Integer.parseInt(st.nextToken()); // 플레이어의 수
		m = Integer.parseInt(st.nextToken()); // 방의 정원
		
		for (int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine());
			enter(new Player(Integer.parseInt(st.nextToken()), st.nextToken()));
		}

		for (List<Player> player: players) {
			if (player.size() >= m) {
				sb.append("Started!\n");
			} else {
				sb.append("Waiting!\n");
			}
			
			Collections.sort(player);
			for (Player pl: player) {
				sb.append(pl.toString()).append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void enter(Player player) {
		// 생성된 순서대로 방을 탐색
		for (List<Player> p: players) {
			// 정원이 다 찬 경우
			if (p.size() >= m) {
				continue;
			}
			
			// 매칭 가능한 방인 경우
			int level = p.get(0).level;
			if (player.level >= level-10 && player.level <= level+10) {
				p.add(player);
				return;
			}
		}

		// 매칭이 가능한 방이 없는 경우 새로운 방을 생성하고 입장시킨다.
		List<Player> p = new ArrayList<>();
		p.add(player);
		players.add(p);
	}

}
