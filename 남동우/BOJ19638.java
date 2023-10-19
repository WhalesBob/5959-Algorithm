import java.io.*;
import java.util.*;

public class BIJ19638 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int population = Integer.parseInt(st.nextToken());
		int centiHeight = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
    // 거인들의 키를 우선순위 큐에 넣고, 가장 큰 거인들을 뽑아냅니다.
		
		for(int i = 0; i < population; i++) {
			queue.add(Integer.parseInt(br.readLine()));
		}
    // 거인들의 키를 입력받고, 우선순위 큐에 넣어 줍니다.
		
		for(int i = 0; i < limit; i++) {
      // 큐에서 제일 큰 사람의 키가 센티보다 작으면, 몇 번 망치를 쳤는지 정보와 함께 정답을 출력합니다.
			if(queue.peek() < centiHeight) {
				System.out.printf("YES\n%d", i);
				return;
			}

      // 위의 조건문에 부합하지 않는다면, 큐에서 제일 큰 사람의 정보를 받아오고, 
      // 해당 사람의 키가 1이라면 단순히 queue 에 다시 집어 넣고 끝냅니다. 
      // 우선순위 큐에서 제일 큰 사람의 키가 1이라면, 큐에 들어 있는 모든 사람들의 키가 1이라는 의미이므로, 더 이상 
      // for 문을 수행하지 않습니다.
			int height = queue.poll();
			
			if(height == 1) {
				queue.add(1);
				break;
			}

      // 우선순위 큐에서 뽑은 거인의 키를 반으로 만들고, 다시 queue 에 집어넣습니다.
			queue.add(height >> 1);
		}

    // 결과를 참고해 정답을 출력합니다.
		if(queue.peek() < centiHeight) {
			System.out.printf("YES\n%d", limit);
		}else {
			System.out.printf("NO\n%d", queue.peek());
		}
	}
}
