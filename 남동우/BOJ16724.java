import java.io.*;
import java.util.*;

public class BOJ16724 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0), 
			Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
    // 높이와 너비를 받아옵니다.
    
		int[][] matrix = makeMatrix(br, height, width); // 원래 char 2차원 배열로 되어 있지만
    // 위의 "direction" 의 인덱스에 대응될 수 있게 int 2차원 배열로 바꿔 저장합니다.
    
		parent = new int[height * width];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
    // union-find 를 사용하기 위해 parent 배열을 만들어 초기화합니다.
    // 2차원 배열을 1차원 배열로 변환하여 parent 배열을 저장합니다.
    
		System.out.println(getCircleUnionCount(matrix));
	}

  // 2차원 배열을 순회하며, 요소가 가지고 있는 방향의
	static int getCircleUnionCount(int[][] matrix) {
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				int newX = x + direction.get(matrix[y][x]).get(0);
				int newY = y + direction.get(matrix[y][x]).get(1);
				
				union(getKey(x, y, matrix), getKey(newX, newY, matrix));
        // matrix[y][x] 가 가리키고 있는 새로운 (newX, newY) 와 현재 (x,y) 를 union 처리해 줍니다. 
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i  < parent.length; i++) {
			set.add(find(i));
		}
    // parent 를 순회하며, 각 요소의 parent 를 set에 모두 모아, set의 크기를 반환합니다.
		
		return set.size();
	}
  // 2차원 배열 요소를 1차원으로 변환해 주는 메소드입니다. parent 인덱스르 찾을 때 사용합니다.
	static int getKey(int x, int y, int[][] matrix) {
		return y * matrix[0].length + x;
	}

  // union-find를 위한 메소드입니다.
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		parent[x] = find(parent[x]); // 경로 압축을 진행하는 부분입니다.
		return parent[x];
	}

  // union-find를 위한 메소드입니다.
	static void union(int a, int b) {
		a = find(a); b = find(b);
		
		parent[b] = a;
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
		int[][] matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			char[] inputArray = br.readLine().trim().toCharArray();
			
			for(int x = 0; x < width; x++) {
				int intInput = -1;
				switch(inputArray[x]) {
					case 'R': intInput = 0; break;
					case 'D': intInput = 1; break;
					case 'L': intInput = 2; break;
					case 'U': intInput = 3; break;
				}
				
				matrix[y][x] = intInput;
			}
		}
		
		return matrix;
	}
}
