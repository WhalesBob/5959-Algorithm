import java.io.*;
import java.util.*;

public class BOJ4485 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), Arrays.asList(0,1), 
			Arrays.asList(-1,0), Arrays.asList(0,-1));
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 0;
		Queue<Node> queue = new ArrayDeque<>(); // 좌표를 저장하기 위해, Node 를 저장하는 Queue를 생성합니다.
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) { // n 을 받아, 0이면 while 문을 탈출합니다.
				break;
			}
			
			int[][] matrix = makeMatrix(br, n);
			System.out.printf("Problem %d: %d\n", ++count, getMinimum(queue,matrix));
      // 2차원 배열을 입력받고, 문제에서 원하는 대로 출력합니다.
		}
	}

  // 2차원 배열을 입력받는 메소드입니다.
	static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
		int[][] matrix = new int[size][size];
		for(int y = 0;  y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}

  // BFS 를 사용해서, [n-1][n-1] 에서의 최소를 찾는 메소드입니다.
	static int getMinimum(Queue<Node> queue,int[][] matrix) {
		int[][] record = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < record.length; i++) {
			Arrays.fill(record[i], INF);
		}
    // 해당 지점들의 최소값들을 찾기 위해, record 라는 이차원 배열을 만들고, INFINITY 값으로 모두 채웁니다.
		record[0][0] = matrix[0][0];
		queue.add(new Node(0,0));
    // 0,0에서 시작하는 것을 고려하여, record[0][0] 에 matrix[0][0] 값을 넣고, queue 에도 넣어 줍니다.

    // BFS 탐색을 하는 while 문입니다.
		while(!queue.isEmpty()) {
			Node element = queue.remove();
			for(int i = 0; i < 4; i++) {
				int newX = element.x + direction.get(i).get(0);
				int newY = element.y + direction.get(i).get(1);
				if(canGo(newX, newY, matrix)) { 
					boolean less = matrix[newY][newX] + record[element.y][element.x] < record[newY][newX];
          // matrix 범위 내에 있고, 새로 업데이트하고자 하는 record 값이 기존의 record 값보다 작다면, 
          // record[newY][newX] 를 업데이트하고, queue 에 넣어 줍니다. 
					if(less) {
						record[newY][newX] = matrix[newY][newX] + record[element.y][element.x];
						queue.add(new Node(newX, newY));
					}
				}
			}
		}
		return record[record.length-1][record[0].length-1];
    // 위의 BFS가 완료되었다면, 기록하던 이차원 배열의 오른쪽 아래 값을 가져옵니다.
	}

  // matrix 내에 있는 범위의 x,y 인지 검사합니다.
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}

  // 메모리 초과를 예방하기 위한 static class 입니다. 
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return this.x * 10000 + this.y;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Node) {
				Node o = (Node)obj;
				return (this.x == o.x) && (this.y == o.y);
			}
			return false;
		}
	}
}
