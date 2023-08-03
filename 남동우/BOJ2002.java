import java.io.*;
import java.util.*;

public class BOJ2002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<String> inQueue = makeList(br, n);
		Queue<String> outQueue = makeList(br, n);
		
		System.out.println(minimumBypass(inQueue, outQueue));
	}
	static Queue<String> makeList(BufferedReader br, int size) throws IOException{
		Queue<String> queue = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			queue.add(br.readLine().trim());
		}
		return queue;
	}
	static int minimumBypass(Queue<String> inQueue, Queue<String> outQueue) {
		int count = 0;
		Set<String> set = new HashSet<>();
		while(inQueue.size() != 0 && outQueue.size() != 0) {
			if(!outQueue.peek().equals(inQueue.peek())) {
				if(set.contains(inQueue.peek())) {
					inQueue.remove();
				}else {
					count++;
					set.add(outQueue.remove());
				}
			}else {
				set.add(outQueue.remove());
				inQueue.remove();
			}
		}
		return count;
	}
}
