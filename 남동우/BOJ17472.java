import java.io.*;
import java.util.*;

public class BOJ17472 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), 
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static List<Island> islandArray;
	static int[] parent;
	static PriorityQueue<Integer> priorityQueue;

	static final int INF = 100_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

    // 섬 정보에 대한 2차원 배열 정보를 입력받습니다.
		int[][] matrix = makeMatrix(br, height, width);

    // 아래에 Island 라는 class 를 만들고, 내부에서는 BFS 를 통해 
    // 섬의 영역을 체크하며, Island 객체에서는 섬의 "가장자리" 부분을
    // set 으로 저장합니다. 

    // 추가적으로, 단순히 0과 1로써만 표시되어 있던 섬 영역을
    // 섬마다 고유의 숫자(islandArray 의 index 로 표시) 를 2차원 배열에 붙여 처리합니다.
		islandArray = getAllIsland(matrix);

    // 전역변수 priorityQueue 를 만들어 줍니다.
    // 섬 간의 거리 정보를 넣어 주고, 이후 제일 짧은 거리부터 하나씩 빼어
    // 섬끼리 연결하기 위해 우선순위 큐를 만들어 주었습니다. 

    // 이후 이 priorityQueue 내에 섬 간의 거리 정보가 모두 들어오면, 이후 짧은 거리부터 섬끼리 이어주기 위해 활용할 것입니다.
		priorityQueue = new PriorityQueue<>();

    // 각 섬 간의 최소 거리 인접 matrix를 이 메소드에서 받아옵니다. 
    // 내부에서는, 해당 최소 거리를 받아오는 로직이 구현되어 있습니다.
		int[][] minLengthMatrix = getMinimumLength(matrix);

    // 위의 메소드를 실행시켰는데도 불구하고, priorityQueue 의 크기가 0이라는 말은,
    // 조건에 만족하는, 이어줄 수 있는 다리가 하나도 없다는 말이 됩니다. 
    // -1 을 출력하고 리턴합니다.
		if(priorityQueue.size() == 0) {
			System.out.println(-1);
			return;
		}

    // 본격적으로, priorityQueue 를 활용해서 섬끼리 최소 거리로 이어줄 것입니다.
    // Kruskal 알고리즘을 활용하기 위해, Union-Find 에 활용할 수 있는
    // 준비물들을 준비해 줍니다. 

    // Union-Find 에 사용할 parent 배열을 초기화합니다.
		parent = new int[islandArray.size()];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

    // 정답으로 사용할 sum, 그리고 다리를 몇 개 이었는지 알 수 있는 count 를 초기화합니다.
		int sum = 0;
		int count = 0;

		try {
      // 다리를 최소한 (섬 갯수 -1 ) 개 만큼 이어야 모든 섬을 연결할 수 있습니다.
			while(count < parent.length - 1) {
        // element 에는 섬 간의 거리, 시작 섬, 도착 섬 정보가 Integer 형태로 들어 있습니다.
        // Integer 로 저장하게 된다면, 이미 모든 프로그램에서 활용하는 숫자 정보로 데이터를 처리하는 것이기 때문에
        // Heap 메모리에 추가적인 메모리를 사용하지 않을 수 있다는 장점이 있습니다. 
        // 혹시나 메모리 초과가 날 것을 우려하여, int 형태로 정보를 저장했고, 그 정보를 바탕으로 다시
        // 시작 섬, 도착 섬, 섬 간의 거리 정보를 추출합니다.
        
				int element = priorityQueue.poll(); // element = (섬 간의 거리) * 1000 + (시작 섬) * 10 + (도착 섬) 으로 저장됌.
				int length = element / 1000; // length 추출
				element %= 1000; // 시작 섬과 도착 섬 정보만 element 에 남김
				
				int start = element / 10;
				int end = element % 10; // start, end 섬 정보 추출합니다.

        // union-find 로 start 섬과 end 섬이 연결되어 있는지 확인합니다
        // rootA 와 rootB 가 같다면, 같은 부모를 가지고 있는 것, 즉 이미 같이 이어져 있다는 정보를 받을 수 있습니다.
				int rootA = find(start); 
				int rootB = find(end);


        // start 섬과 end 섬의 부모가 다르다면, sum 에 해당 섬 간의 거리를 더해 주고, 
        // start, end 를 union 해 줍니다. 이후 다리가 하나 더 이어졌다는 의미로
        // count 를 하나 올려 줍니다.
				if(rootA != rootB) {
					sum += length;
					union(start, end);
					count++;
				}
			}
      // 다리를 다 이었다면, 다리 길이의 합계를 출력합니다. 
			System.out.println(sum);
		}catch(NullPointerException e) {
      // 만약 priorityQueue 가 다 비었음에도 불구하고 count 가 (섬 갯수 - 1) 보다 작은 상황이 온다면
      // NullPointerException 이 발생하게 됩니다. 

      // 모든 섬을 다 이을 수 없다고 판단, -1 을 출력합니다.
			System.out.println(-1);
		}
	}

  // 위의 main 메소드에서, BFS를 통해 모든 섬에 숫자 마킹을 진행하며
  // 섬의 가장자리 정보를 Island 객체에 모두 담아 
  // List 로 리턴하는 메소드입니다.
  static List<Island> getAllIsland(int[][] matrix) {
		
		List<Island> islandList = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length]; // 방문 여부를 표시하는 boolean 2차원 배열입니다.
		Queue<Integer> queue = new ArrayDeque<>(); // BFS 에서 사용할 queue 입니다.
		int count = 0; // 숫자 마킹을 진행할 때 활용할 count 를 0으로 초기화합니다.

    // 2차원 배열을 순회하며, 섬을 찾습니다.
    // 방문 처리 되지 않았으며 matrix[y][x] 가 1이라는 조건을 만족한다면,
    // "방문하지 않은 새로운 섬을 찾았다" 는 이야기가 됩니다.
    // queue 에 하나 넣어 주고, visited[y][x] 를 true 로 바꾸며, 
    // getOneIsland() 메소드를 활용해서 섬 정보를 모두 받아와 list 에 담아 줍니다.
    // 해당 섬에 고유한 숫자를 마킹하는 것도, 아래의 getOneIsland() 메소드에서 진행합니다.
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				if(!visited[y][x] && matrix[y][x] == 1) {
					queue.add(getKey(x, y)); // 해당 x,y 를 Integer 하나로 변환하여 queue 에 미리 담아 줍니다.
          // 역시 메모리 초과를 우려하여, x,y 를 담아내는 객체를 생성하는 대신, (1000 * y + x) 를 queue 에 담아
          // x,y 를 추출 할 수 있게 만들어 주었습니다.
          
					visited[y][x] = true;
					islandList.add(getOneIsland(matrix, visited, queue, ++count));
				}
			}
		}

    // 섬 정보를 모두 받아왔다면 받아온 섬 정보의 리스트를 반환합니다.
		return islandList;
	}

  // 호출되었을 시, 찾은 땅을 기준으로 BFS 순회하며 주변에 연결된 땅에 고유한 숫자를 붙여 주고
  // island 객체를 만들어 그 섬의 "가장자리 땅" 정보를 담아 반환하는 메소드입니다.
	static Island getOneIsland(int[][] matrix, boolean[][] visited, Queue<Integer> queue, int islandCount) {
		
		Island island = new Island();
		
		while(!queue.isEmpty()) {
			int element = queue.remove(); 
      // x,y 를 element 라는 숫자 하나에 정보를 담아 리턴했습니다.
			// element = 1000 * y + x 로 저장되어 있으므로, x,y 정보를 element 에서 추출해 줄 수 있습니다.
      
      int y = element / 1000;
			int x = element % 1000;

      // BFS 순회하며 만나는 (x,y) 좌표의 1을, 섬이 가질 수 있는 고유한 숫자로 마킹해 줍니다.
      // 섬이 가질 수 있는 고유한 숫자란, 위에서 사용하는 islandArray 의 index 정보이기도 합니다.
			matrix[y][x] = islandCount;

      // 이 땅이 "가장자리 땅" 인지 체크하기 위해, count 를 0으로 초기화합니다.
      // count 가 4가 되면, 상하좌우에 모두 땅이 있거나, 어떻게든 그 땅이 안쪽에 있다는 이야기가 됩니다.
      // 만약, 왼쪽 위에 크기 4짜리 정사각형 땅이 있다고 가정하면, 인접한 땅은 2개일 것이지만
      // 이미 왼쪽과 위에 갈 수 있는 부분이 없기 때문에, "가장자리 땅" 이라고는 부를 수 없을 것입니다.
      // "가장자리 땅" 은, "다른 섬으로 다리를 이을 수 있는 땅" 이라고 이해하시면 될 것 같습니다.
			int count = 0;

      // 상하좌우를 순회하며, 옆에 땅이 있는지 확인해 줍니다.
			for(int i = 0; i < 4; i++) {
				int newX = x + direction.get(i).get(0);
				int newY = y + direction.get(i).get(1);

        // 보고자 하는 땅이 갈 수 없는 땅(2차원 배열을 벗어남)이라면, count 를 하나 증가시켜 줍니다. 
				if(!canGo(newX, newY, matrix)) {
					count++;

          // 보고자 하는 땅 정보가 0 이상이라면, 그 자리에 땅이 존재한다는 이야기가 됩니다. 
          // count 를 하나 증가시켜 주며, 정말 그 땅이 방문하지 않은 땅이었다면
          // 방문 처리 이후 queue 에 (x,y) 정보를 담아 줍니다.
				}else if(matrix[newY][newX] > 0) {
					count++;
					if(!visited[newY][newX]) {
						visited[newY][newX] = true;
						queue.add(getKey(newX, newY)); // 역시 객체를 만들어 queue 에 담는 것이 아니라, (x,y) 정보를
            // 1000 * y + x 라는 Integer 로 변환해 담아 줍니다.
						
					}
				}
			}

      // count 정보가 0보다 크거나 같고 4보다 작다면, "가장자리 땅" 이라는 이야기가 됩니다.
      // island 의 edgeLand(HashSet) 에 땅 좌표 정보를 담아 줍니다.
			if(0 <= count && count < 4) {
				island.edgeLand.add(getKey(x, y));
			}
		}

    // BFS 순회를 마치며 섬의 모든 땅에 정보를 마킹하고, 모든 가장자리 땅 정보를 받아왔다면, 
    // 결과가 되는 island 를 리턴합니다.
		return island;
	}

  // matrix 에 있는 정보와, 전역 변수 islandArray 에 있는 정보를 바탕으로
  // 섬 간의 adjacency matrix(인접 matrix) 를 만들어 줍니다
  // 단순했던 섬 정보 2차원 배열을 하나의 그래프 처럼 만들어, "그래프 문제" 로 문제를 변환할 것입니다.
	static int[][] getMinimumLength(int[][] matrix){
		int[][] minimumMatrix = new int[islandArray.size()][islandArray.size()];

    // 최소 거리 2차원 배열을 islandArray 의 size 크기로 초기화하고, 
    // 최소 거리 matrix 에 INF(100_000) 를 모두 집어넣고, 본인 섬이라면 0을 넣어 줍니다.
		for(int i = 0; i < minimumMatrix.length; i++) {
			Arrays.fill(minimumMatrix[i], INF);
			minimumMatrix[i][i] = 0;
		}

    // 이미 가지고 있던 섬 정보(islandArray) 배열을 순회합니다.
    // 섬의 가장자리 땅을 기준으로, 상하좌우를 탐색하며 갈 수 있는 땅을 모두 찾습니다.
    // 상하좌우 중 땅이 아닌 곳(바다)를 찾았다면, 직선으로 탐색해 어떤 땅이 나오는지 보고, 
    // 그 땅이 어떤 땅인지, 기존 거리보다 더 짧은지 확인한 뒤, 더 짧다면 최소 거리를 업데이트 합니다.
		for(int l = 0; l < islandArray.size(); l++) {
			Island land = islandArray.get(l);
			for(int element : land.edgeLand) { // 해당 섬의 가장자리 땅을 순회하는 for 문입니다.
				int x = element % 1000;
				int y = element / 1000;
				for(int i = 0; i < 4; i++) { // 받아온 x,y 를 기준으로 상하좌우를 탐색합니다.
					int newX = x + direction.get(i).get(0);
					int newY = y + direction.get(i).get(1);
					
					if(canGo(newX, newY, matrix) && matrix[newY][newX] == 0) { // 물리적으로 갈 수 있으며, 새로 받아온 x,y 좌표가 바다라면
						int result = getTourResult(x, y, i, matrix);
            // getTourResult 를 통해, 어떤 땅이었는지, 그리고 거리는 얼마나 되는지 정보를 받아옵니다. 

            // getTourResult 결과가 -1 이라면, 탐색한 방향으로 일자형으로 갔을 때 찾은 땅이 없다는 결과가 나왔다는 의미입니다.
            // Result 가 0보다 크다면, target, length 정보를 받아올 수 있습니다.
						if(result > 0) {
							int target = (result / 1000) - 1; // result = target * 1000 + length 로 이루어져 있습니다.
							int length = result % 1000; // target 과 거리 정보를 result 로부터 추출합니다.

              // 조건에 부합하며(다리길이 최소 2 이상), 기존에 갖고 있었던 길이보다 찾은 길이 가 더 짧다면
              // 정보를 priorityQueue 에 업데이트 해 주고, 최소 거리 matrix 에서도 업데이트 합니다.
							if(length >= 2 && minimumMatrix[l][target] > length) { 
								minimumMatrix[l][target] = minimumMatrix[target][l] = length;
								priorityQueue.add(makeDistanceInfo(l, target, minimumMatrix[l][target]));

                // 거리 정보 = (거리) * 1000 + (시작섬 숫자) * 10 + (도착점 숫자) 
							}
						}
					}
				}
			}
		}

    // 다 찾은 최소 거리 2차원 배열 정보를 리턴합니다.
		return minimumMatrix;
	}

  // 원하는 점에서, 원하는 방향으로 일직선상으로 진행했을 때 나오는 섬 정보와 거리 정보를 합쳐서 반환하는
  // 메소드입니다.
	static int getTourResult(int x, int y, int directionIdx, int[][] matrix) {
		int count = 0;
		while(true) { // 더 이상 갈 수 없거나, 땅을 찾을 때까지 진행합니다.

      // x,y 정보를 계속 원하는 방향으로 진행시켜 줍니다.
			x += direction.get(directionIdx).get(0);
			y += direction.get(directionIdx).get(1);

      // 못 가게 되었다면, while 문을 종료하고 -1 을 리턴합니다.
			if(!canGo(x, y, matrix)) {
				break;
      // 땅을 찾았다면, (마킹된 섬 정보 숫자) * 1000 + (거리 정보(count)) 를 반환합니다.
			}else if(matrix[y][x] != 0) {
				return getKey(count, matrix[y][x]);
			}


      // 땅을 찾지 못했고, 여전히 바다라면 count 를 1 증가시켜 줍니다.
			count++;
		}
		
		return -1;
	}
  
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	
	static int getKey(int x, int y) {
		return 1000 * y + x;
	}
	static int makeDistanceInfo(int start, int end, int distance) {
		return distance * 1000 + start * 10 + end;
	}
	
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static class Island{
		Set<Integer> edgeLand;

		public Island() {
			this.edgeLand = new HashSet<>();
		}
	}
}
