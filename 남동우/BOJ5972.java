import java.io.*;
import java.util.*;

public class Main {
	static final int INFINITY = 999_999_999;
	static int[] cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<Integer, Map<Integer, Integer>> adjacencyMap = makeAdjacencyMap(br, m);
    // 이 문제는, 그래프 탐색 문제입니다. 헛간이 50,000 개인 것을 감안하면, adjacencyMatrix 를 만들면 안됩니다.
    // adjacencyMatrix 를 만들면, 이차원 배열의 요소가 25억 개가 됩니다. 메모리 초과가 날 것이 자명하여, 
    // adjacencyList 를 변형해 만들어 주었습니다.
    
		boolean[] visited = new boolean[n + 1];
    // 문제 특성 상, 저는 다익스트라 알고리즘을 사용하기로 했습니다. 그 준비를 위해, visited 라는 boolean 배열을 만들어 줍니다. 
    
		System.out.println(getMiminumCostByDijkstra(visited, adjacencyMap, n));
    // 다익스트라 알고리즘을 사용해, 최소 비용을 구해 출력합니다.
    
	}
	static Map<Integer,Map<Integer,Integer>> makeAdjacencyMap(BufferedReader br, int size) throws IOException{
    // AdjacencyList 를 변형해, 이중 Map 을 만들어 준 것입니다.
    
		Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
      // start, end, value 를 입력받습니다.
      
			if(!map.containsKey(start)) {
				map.put(start, new HashMap<>());
			}
			if(!map.containsKey(end)) {
				map.put(end, new HashMap<>());
			}

      // 이중 Map 에 집어넣기 위해, start, end 키를 바깥 Map 이 가지고 있지 않을 때, HashMap 을 value 로 넣어주는 것입니다.
			
			if(map.get(start).get(end) == null) {
				map.get(start).put(end, value);
				map.get(end).put(start, value);
			}else {
				int existedValue = map.get(start).get(end);
				if(value < existedValue) {
					map.get(start).replace(end, value);
					map.get(end).replace(start, value);
				}
			}
      // 문제 조건에, "두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있습니다." 라는 정보를 참고하여,
      // 기존에 정보를 가지고 있으면, 새로 받은 길이 더 짧은지 아닌지 확인 후, 더 짧은 값으로 바꿔주는 작업입니다.
			
		}
		return map;
	}
	static int getMiminumCostByDijkstra(boolean[] visited, Map<Integer, Map<Integer, Integer>> map, int n) {
		PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a,b) -> Integer.compare(a.get(1), b.get(1)));
    // 다익스트라 알고리즘에서, 최소값을 비교하다가 시간 초과가 나는 바람에, PriorityQueue 를 적용해 다음 값을 도출하려고 적용했습니다.
    // 0번째 인덱스에는 해당 값의 인덱스를, 1번째 인덱스에서는 값을 넣어 줍니다. 
    // 값끼리 비교해서, 더 작은 값의 인덱스를 가져와 다익스트라를 진행시키기 위해서 만들어 주었습니다.
		
		int start = 1;
		cost = new int[n+1];
		for(int i = 1; i < cost.length; i++) {
			cost[i] = INFINITY;
		}
    // cost 라는 배열을 만들어 줍니다. 다익스트라 알고리즘에서 "1에서 X까지의 최소 거리" 를 업데이트해 주기 위해 만듭니다.
		
		cost[start] = 0;
		queue.add(Arrays.asList(start, cost[start]));
    // 시작점의 cost 를 0 으로 만들어 주고, (start, cost[start]) 정보를 우선순위 큐에 업데이트합니다.

		while(!visited[n]) { // n 번째 헛간이 방문될때까지 계속합니다.
			int currentFocus = queue.remove().get(0);
			if(visited[currentFocus]) {
				continue;
			}
      // 우선순위 큐에서, 가장 작은 값을 가져온 뒤 해당 인덱스를 확인합니다.
      // 만약 이미 방문한 노드가 뽑혔을 시, continue 합니다.
      
			visited[currentFocus] = true;
      // 현재 보고 있는 인덱스의 헛간을 방문 처리합니다.
			
			for (Integer key : map.get(currentFocus).keySet()) {
        // 해당 헛간이 갈 수 있는 다음 헛간의 정보를, map 의 keySet() 정보로 받아 옵니다.
				int compareValue = cost[currentFocus] + map.get(currentFocus).get(key);
        // 기존의 cost[key] 보다, 돌아가는 것이 더 빠른지 아닌지 확인하기 위해 compareValue 를 연산해 줍니다.
        
				if (!visited[key] && cost[key] > compareValue) {
					cost[key] = compareValue;
					queue.add(Arrays.asList(key, compareValue));
				}
        // 해당 헛간을 방문하지 않았으면서, 기존의 루트보다 돌아가는 길이 더 빠르다고 판단될 경우, 
        // cost 배열에 더 최소거리를 업데이트 해 주고, 우선순위 큐에도 이 정보를 업데이트해 줍니다.
        // 같은 인덱스가 겹쳐서 들어갈 수도 있을 것입니다. 하지만 위에서, 이미 방문한 노드의 경우 continue 하므로
        // 중복해서 인덱스를 넣는 경우도 여기서는 배제해 주지 않았습니다.
			}
		}

		return cost[n];
	}
}
