import java.io.*;
import java.util.*;

public class BOJ1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] input = br.readLine().trim().toCharArray(); 
		
		int n = Integer.parseInt(br.readLine()); // 이후, 몇 번 수행할 것인지에 대한 숫자를 입력받습니다.
		
		Stack<Character> stack = new Stack<>();
		ArrayDeque<Character> queue = new ArrayDeque<>(); // Stack, ArrayDeque 를 준비합니다.
		
		for(char c : input) {
			queue.addLast(c);  // 초기 문자열 입력을 char 배열로 바꿔, ArrayDeque 에 집어넣습니다.
		}
		
		for(int i = 0; i < n; i++) {
			String[] splitInput = br.readLine().trim().split(" ");
			String op = splitInput[0];

      // 입력받는 operation 내역에 따라서, stack 과 ArrayDeque 를 활용해 연산해 줍니다.
			switch(op) { 
				case "L":
					if(!queue.isEmpty()) stack.add(queue.removeLast()); // 커서 한 칸 왼쪽으로 옮길 때는, queue 의 맨 마지막 것을 빼어
          // stack 에 넣어 줍니다.
					break;
				case "D": // 커서 한 칸 오른쪽으로 옮길 때는, 스택에 있는 것을 빼어 queue 에 넣어 줍니다.
					if(!stack.isEmpty()) queue.addLast(stack.pop());
					break;
				case "B": // 커서 한 칸 앞을 지울 때는, 단순히 queue의 맨 마지막 것을 지웁니다.
					if(!queue.isEmpty()) queue.removeLast();
					break;
				case "P": // 삽입할 때는, 단순히 queue 의 맨 마지막에 넣고자 하는 값을 넣습니다.
					char toAdd = splitInput[1].charAt(0);
					queue.addLast(toAdd);
					break;
			}
		}
		
		while(!stack.isEmpty()) { // 스택이 빌 때까지, 스택의 남은 부분을 ArrayDeque 의 마지막 부분에 넣습니다.
			queue.addLast(stack.pop());
		}
		
		while(!queue.isEmpty()) { // Queue 가 빌 때까지, BufferedWriter 에 써 준 뒤, flush() 합니다.
			bw.write(Character.toString(queue.removeFirst()));
		}
		
		bw.flush();
	}
}
