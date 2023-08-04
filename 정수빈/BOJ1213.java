import java.util.*;
import java.io.*;

public class BOJ1213 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Character, Integer> map = new HashMap<>();
		for(char c : br.readLine().toCharArray()) {
			
			if(map.containsKey(c))
				map.put(c, map.get(c)+1);
			else
				map.put(c, 1);
		}
		
		int odd = 0;
		String start = "";
		String last = "";
		
		List<Character> ks = new LinkedList<>(map.keySet());
		Collections.sort(ks);
		
		for(char c : ks) {
			int n = map.get(c);
			
			if(n%2 == 1) {
				if(odd == 1) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
				
				odd += 1;
				
				if(n != 1)
					for(int i=0; i<(n-1)/2; i++)
						start += (c+"");
				
				last += (c+"");
			}
			else {
				for(int i=0; i<n/2; i++)
					start += (c+"");
			}
		}
		
		StringBuffer sb = new StringBuffer(start);
		last += sb.reverse().toString();
		System.out.println(start+last);
	}
}
