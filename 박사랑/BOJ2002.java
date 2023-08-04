import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2002 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		ArrayList<String> car = new ArrayList<>();
		int cnt = 0;
		int idx = 0;

		for (int i = 0; i < n; i++) {
			car.add(br.readLine());
		}

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			if (car.get(idx).equals(input)) { 
				idx++;
			} else if (!car.get(idx).equals(input)) { // 같지 않을 때
				car.remove(input);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
