import java.io.*;
import java.util.*;

public class BOJ18430 {
	static int maxStrength;
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1),
			Arrays.asList(-1,0), Arrays.asList(0,-1), Arrays.asList(1,0), Arrays.asList(0,1)); // 3개의 블록을 묶어서 체크해야 합니다. 그래서 0,1,2,3,4 와 같은 인덱스로, index, index + 1 와 같이 두 개를 묶어서 
														// 접근할 수 있도록 static 으로 만들었습니다.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int height = Integer.parseInt(st.nextToken());	
		int width = Integer.parseInt(st.nextToken());	// 가로와 세로 정보를 받습니다.
		
		int[][] matrix = makeMatrix(br, height, width);	// 2차원 배열을 입력받아 업데이트합니다.
		maxStrength = 0;	// 무기에서 최대값을 업데이트 하기 전, 기본값을 0으로 세팅합니다.
		
		getMaxWeapon(matrix, new boolean[height][width], 0, 0, 0);	// DFS 를 순회하며, 무기의 최대값을 업데이트 시작하는 부분입니다. DFS method를 void 로 설정해, 이 method 안에서 maxStrength 를 업데이트합니다. 
		System.out.println(maxStrength); // 결과를 출력합니다.
	}
	static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{ // 이차원 배열을 받아오는 method 입니다.
		int[][] matrix = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		return matrix;
	}
	static void getMaxWeapon(int[][] matrix, boolean[][] visited, int y, int x, int currentStrength) { // dfs 를 순회하면서 찾는 method 입니다.
		while(canGo(x,y,matrix)) { // x,y 가 이차원 배열 안의 index 값인지 확인합니다.
			if(!visited[y][x]) { // 기존에 boolean 2차원 배열에서, 이미 그 자리에 무기가 배치되었는지 확인합니다.
				for(int i = 0; i < 4; i++) { // 4개의 부메랑 방향을 확인합니다.
					int weaponSum = getPossibleWeapon(matrix, visited, i, y, x); // 배치할 수 있는 무기를 탐색하는 상세 method 입니다. 배치할 수 없다면 -1 를 리턴하며, 
					// 배치할 수 있다면 boolean[][] visited 배열에서 배치 가능한 자리를 방문했다는 의미로, true 로 이차원 배열 안에서 업데이트합니다. 그리고 무기의 값을 가져옵니다.
					if(weaponSum > 0) { // -1 이 아니라면
						getMaxWeapon(matrix, visited, y, x, currentStrength + weaponSum); // DFS 순회합니다. 기존에 파라미터로 받아온 값에, 새로 무기를 배치하며 추가되는 값도 합산해서 넣어 줍니다.
						getOffWeaponVisited(matrix,visited, i, y, x); // DFS 순회 이후에는, 다시 해당 무기의 방문 여부를 false 로 업데이트하여, DFS 를 순회하는데 있어 지장이 없게 해 줍니다.
					}
				}
			}
			y = x + 1 < matrix[0].length ? y : y + 1; // 무기가 배치되었든, 배치되지 않았든 상관없이 다음 x,y 값을 업데이트합니다.
			x = x + 1 < matrix[0].length ? x + 1 : 0; // 이중 for 문으로 작성하고 싶긴 하나, 어려움이 따른 관계로 x,y 를 수동으로 업데이트합니다. x 가 끝에 다다르면, x = 0, y = y + 1 으로 업데이트 해주는 부분입니다.
		}
		
		if(maxStrength < currentStrength) { // 최종적으로 x,y가 matrix 의 index 범위를 벗어났을 때, 파라미터로 받아온 합산 값을 static 변수와 비교합니다.
			maxStrength = currentStrength; // 기존에 가지고 있던 최대값보다 더 큰 값을 받았을 때, maxStrength 를 업데이트합니다.
		}
	}
	static boolean canGo(int x, int y, int[][] matrix) { // 해당 x,y 가 이차원 배열의 인덱스 범위 내에 있는지 리턴합니다.
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
	
	static int getPossibleWeapon(int[][] matrix, boolean[][] visited, int weaponIndex, int y, int x ) { // 무기를 배치할 수 있는지 없는지를 확인하는 세부 메소드입니다. 
		List<List<Integer>> weaponPointList = new ArrayList<>();
		weaponPointList.add(Arrays.asList(x,y));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex).get(0), y + direction.get(weaponIndex).get(1)));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex+ 1).get(0), y + direction.get(weaponIndex + 1).get(1))); // 해당 x,y 와, 4번 순회하며 받아오는 중앙 이외의 x,y값을 생성합니다.
		
		for(List<Integer> point : weaponPointList) { // weaponPointList 안의 weaponPoint 를 조사합니다.
			int pointX = point.get(0); // x값을 받아옵니다.
			int pointY = point.get(1); // y값을 받아옵니다.
			if(!canGo(pointX, pointY, matrix) || visited[pointY][pointX]) { // 해당 x,y값이 이차원 배열 내에 없다면 -1 를 리턴하며, 혹시 이차원 배열 안에 x,y가 위치할 수 있다 해도 이미 방문했다면 -1를 리턴합니다.
				return -1;
			}
		}
		
		int sum = 0; // 위의 for 문에서 return -1 에 걸리지 않았다면, 무기를 배치할 수 있다는 의미입니다. 값을 합산해 줍니다.
		for(List<Integer> point : weaponPointList) {
			int pointX = point.get(0);
			int pointY = point.get(1);
			sum += matrix[pointY][pointX]; // x,y를 다시 weaponList 에서 받아온 뒤, sum 에 값을 합산해 줍니다.
			visited[pointY][pointX] = true; // 이미 배치하기로 한 부분이므로, 배치한 point 를 visited 이차원 배열에서도 "방문했다" 는 정보를 true 로 업데이트합니다.
		}
		
		sum += matrix[y][x]; // 중앙 값은 한번 더 더해야 합니다.
		return sum; // 무기 값을 합산한 정보를 리턴합니다.
	}
	static void getOffWeaponVisited(int[][] matrix,boolean[][] visited, int weaponIndex, int y, int x) { // 이미 방문한 무기의 방문 정보를 false 로 되돌려 주는 method 입니다.
		List<List<Integer>> weaponPointList = new ArrayList<>();
		weaponPointList.add(Arrays.asList(x,y));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex).get(0), y + direction.get(weaponIndex).get(1)));
		weaponPointList.add(Arrays.asList(x + direction.get(weaponIndex+ 1).get(0), y + direction.get(weaponIndex + 1).get(1))); // 위와 같습니다. weaponIndex 를 받아, 어떤 방향의 무기인지 확인하고, 값을 모아 줍니다.
		
		for(List<Integer> point : weaponPointList) { // 모은 값을 for 문을 돌면서 visited 이차원 배열 내에서 다시 방문 정보를 false 로 되돌려 줍니다.
			int pointX = point.get(0);
			int pointY = point.get(1);
			if(canGo(pointX, pointY, matrix)) { // pointX, pointY 가 이차원 배열 내에 있는지 점검합니다.
				visited[pointY][pointX] = false; // 해당 pointY, pointX 의 visited 를 false 로 되돌립니다.
			}
		}
	}
}
