import java.io.*;
import java.util.*;

public class BOJ5397 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
    // 마지막에 출력해야 할 것이 많을까 싶어 BufferedWriter 를 사용합니다.
    
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			char[] charArray = br.readLine().trim().toCharArray(); // 키보드 입력을 배열로 받습니다.
			getAndPrintPassword(bw, charArray);
		}
		bw.flush(); // 최종적으로 출력합니다.
	}
	static void getAndPrintPassword(BufferedWriter bw,char[] charArray) throws IOException{
		ArrayDeque<Character> front = new ArrayDeque<>();
		Stack<Character> stack = new Stack<>();
    // ArrayDeque 와 Stack 을 응용해서, 문제를 풀어 보았습니다.
    
		for(char c : charArray) {
			switch(c) {
				case '>':
					if(!stack.isEmpty()) front.addLast(stack.pop()); // 오른쪽 키를 눌렀다면, Stack 에서 빼어 ArrayDeque 의 마지막에 붙여 줍니다.
					break;
				case '<':
					if(!front.isEmpty()) stack.push(front.removeLast()); // 왼쪽 키를 눌렀다면, ArrayDeque 의 마지막을 빼어 stack 에 넣어 줍니다.
					break;
				case '-':
					if(!front.isEmpty()) front.removeLast(); // 백스페이스를 눌렀다면, ArrayDeque 의 마지막을 삭제해 줍니다.
					break;
				default:
					front.addLast(c); // 일반 입력값을 입력하면, ArrayDeque 의 맨 마지막에 넣어 줍니다.
			}
		}
		
		while(!stack.isEmpty()) front.add(stack.pop()); // 스택에 넣은 값들을 위에서부터 하나씩 빼어 ArrayDeque 에 넣어 줍니다.
		while(!front.isEmpty()) bw.write(Character.toString(front.removeFirst())); // ArrayDeque 가 빌 때 까지, BufferedWriter 에 앞에서부터 하나씩 빼어 적어 줍니다.
		bw.write("\n"); // 해당 케이스가 끝났다면, 개행 문자를 붙입니다.
	}
}
