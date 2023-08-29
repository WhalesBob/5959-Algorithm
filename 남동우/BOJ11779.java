import java.io.*;
import java.util.*;

public class BOJ11779 {
	static final int INF = Integer.MAX_VALUE;
	static int[] from;
	static int[] cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		Map<Integer, Map<Integer, Integer>> adjacencyMap =  getAdjacencyMap(br, m); // 인접 리스트를 맵 형태로 받아옵니다.
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
// 시작과 끝 부분을 가져옵니다.
    
		getMinimumCost(adjacencyMap, start, dest, n);
		// 시작과 끝을 기준으로, 다익스트라 방식으로 최소 거리를 찾습니다. 최소 거리는, cost[dest] 에 들어갈 것입니다.
    // 이후, cost[dest] 에 적힌, 도착점까지의 최소 거리를 찾습니다.
		System.out.println(cost[dest]);

  // getMinimumCost 에서 갱신했던 from 배열을 활용해, 시작점부터 끝점까지의 경로를 찾습니다.
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int travel = dest;

    // travel 을 통해서, 끝 부분부터 시작 부분까지 역추적합니다. 이후, 추적하는 내용을 ArrayDeque에 집어넣습니다.
		while(travel != start) {
			queue.addFirst(travel);
			travel = from[travel];
		}
		
		queue.addFirst(start);

    // 시작점부터 끝점까지 담긴 queue의 size 를 출력하고, StringJoiner를 활용해 경로를 하나의 String 으로 만들어 출력합니다.
		System.out.println(queue.size());
		StringJoiner joiner = new StringJoiner(" ");
		while(!queue.isEmpty()) {
			joiner.add(Integer.toString(queue.removeFirst()));
		}
		
		System.out.println(joiner.toString());
	}
	static Map<Integer, Map<Integer, Integer>> getAdjacencyMap(BufferedReader br, int busCount) throws IOException{
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		for(int i = 0; i < busCount; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(start)) {
				map.put(start, new HashMap<>());
			} // start 를 map 이 key 로 가지고 있지 않다면, map 에 새로 start라는 key를 추가합니다. 
			
			if(map.get(start).containsKey(end)) { // map.get(start).containsKey(end) 는, 이미 start-end 로 이어지는
        // 연결이 map 에 존재하는지를 확인하는 것입니다. map 내에 존재하면서, 이미 존재하는 값이 value 보다 큰 값이라면
        // 새로운 value 로 값을 바꿔 줍니다. 
				int have = map.get(start).get(end);
				if(have > value) {
					map.get(start).replace(end, value);
				}
			}else { // 들어 있지 않다면, start-end key 쌍으로 value 를 안에 집어넣어 줍니다.
				map.get(start).put(end, value);
			}
		}
		return map;
	}
	
	static void getMinimumCost(Map<Integer, Map<Integer, Integer>> map, int start, int dest, int cityCount) {
		boolean[] visited = new boolean[cityCount + 1];
		cost = new int[cityCount + 1];
		from = new int[cityCount + 1];
		for(int i = 1; i < cost.length; i++) {
			cost[i] = INF;
		}
	  // cost 와 from 전역 변수 배열을 초기화 해 주고, 초기 cost를 모두 무한대로 저장합니다.
    // 이후, start 의 cost는 0으로 만들어 줍니다.
		cost[start] = 0;
		PriorityQueue<List<Integer>> queue = new PriorityQueue<List<Integer>>((o1,o2) -> Integer.compare(o1.get(1), o2.get(1)));
		queue.add(Arrays.asList(start, cost[start]));

    // 다익스트라 방법에서 사용할 PriorityQueue 를 초기화해 줍니다. 이후, queue 에 시작 지점과 그 cost 를 넣어 줍니다.

    // 이후, queue 가 비어 있기 전까지, 그리고 dest를 방문하기 전까지 while 문을 순회합니다.
		while(!queue.isEmpty() && !visited[dest]) {
			List<Integer> element = queue.remove();
			int point = element.get(0);
			visited[point] = true;
			if(!map.containsKey(point)) {
				continue;
			}

      // while 문 내에서, 가장 현재 cost가 작은 값을 꺼내와서 방문한 다음, 최소거리를 업데이트합니다.
			for(Integer toGo : map.get(point).keySet()) {
				int value = cost[point] + map.get(point).get(toGo);
				if(!visited[toGo] && cost[toGo] > value) {
					cost[toGo] = value;
					from[toGo] = point;
					queue.add(Arrays.asList(toGo, cost[toGo]));
				}
			}
		}
	}
}
