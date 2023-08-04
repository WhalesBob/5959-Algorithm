import java.io.*;
import java.util.*;

public class BOJ2002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<String> inQueue = makeList(br, n); // 터널에 들어간 queue, 나온 queue 를 만들어 줍니다.
		Queue<String> outQueue = makeList(br, n);
		
		System.out.println(minimumBypass(inQueue, outQueue)); // 해답을 구해 출력합니다.
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
		Set<String> set = new HashSet<>(); // 이미 터널을 탈출한 차량을 모아 놓은 set 입니다.
		while(inQueue.size() != 0 && outQueue.size() != 0) { // 두 Queue 중 하나라도 size 가 0 일 때, 반복문을 끝냅니다.
			if(!outQueue.peek().equals(inQueue.peek())) { // 두 Queue의 맨 앞을 비교합니다. 
				if(set.contains(inQueue.peek())) { 
					
					inQueue.remove();
					// outQueue의 첫번째 요소와, inQueue의 첫 번째 요소가 다른데, 
					// inQueue의 첫 번째 요소가 이미 터널을 탈출한 set에 들어 있다는 것은, 
					// 해당 차가 이미 추월해서 바깥으로 나왔다는 의미가 됩니다.
					// 단순히 inQueue에서 빼 주고, 다음 요소와 outQueue의 첫 번째 요소를 비교하면 될 것입니다.
				}else {
					count++;
					set.add(outQueue.remove());
					// 만약, 터널에 진입한 요소가, 다른 차량들보다 더 빨리 나왔다면 이 차는 추월한 것입니다.
					// 여기서는, inQueue 의 맨 첫번째 요소가 outQueue의 맨 첫번째 요소와 다른 케이스이면서
					// 그럼에도 불구하고, inQueue의 맨 첫번째 요소가 터널을 탈출한 차량 set에 들어있지 않다는 말은,
					// outQueue의 첫 번째 요소는, inQueue 의 첫 번째 요소를 추월했다는 의미가 됩니다.
					// 그러므로, 추월 카운트를 하나 늘려 주고, 이미 나온 차량들 set에 넣어 줍니다.
				}
			}else {
				set.add(outQueue.remove()); // 두 Queue가 같다면, 추월하지 않은 것입니다. 
				inQueue.remove(); // 단순히 두 Queue에서 맨 앞의 요소를 빼 주고, 
				// 터널을 나온 queue의 맨 앞의 요소는 set 에 추가해 줍니다.
			}
		}
		return count;
	}
}
