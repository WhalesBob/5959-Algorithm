import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			String input = br.readLine();
			int pos_x = 0, pos_y = 0, dir = 0,
				max_x = 0, max_y = 0,
				min_x = 0, min_y = 0;
			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) == 'R')
					dir=(dir+1)%4;
				else if (input.charAt(j) == 'L')
					dir=(dir+3)%4;
				else if (input.charAt(j) == 'F') {
					pos_x += dx[dir];
					pos_y += dy[dir];
				} else {
					pos_x -= dx[dir];
					pos_y -= dy[dir];
				}
				max_x = Math.max(max_x, pos_x);
				min_x = Math.min(min_x, pos_x);
				max_y = Math.max(max_y, pos_y);
				min_y = Math.min(min_y, pos_y);
			}
			result.append(Math.abs(max_x-min_x)*Math.abs(max_y-min_y)).append("\n");
		}
		System.out.println(result);
	}
}
