import java.util.*;

class Solution {
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1), Arrays.asList(1,0), Arrays.asList(-1,-1)); 
    public int[] solution(int n) {
        int[][] matrix = new int[n][n]; 
		    int[] array = new int[(n*(n+1))/2];
        int turnCount = 0, index = 0, addValue = 1;
        int x = 0, y = -1;
        // 2차원 배열과 정답을 입력할 1차원 배열, 그리고 달팽이 이동을 체크하고 값을 삽입할 준비를 해 줍니다. 
        // 첫 nextX, nextY 를 0,0 으로 만들어 주고 싶어, x = 0, y = -1 로 초기화 해주었습니다.
      
        while(true) {
        	int nextX = x + direction.get(index).get(0);
        	int nextY = y + direction.get(index).get(1);
        	// direction 인덱스가 바라보고 있는 다음 x,y를 만들어 줍니다.
          
        	if(canGo(nextX, nextY, matrix) && matrix[nextY][nextX] == 0) { 
            // 다음 x,y 로 이동할 수 있으면서, 2차원 배열에 아직 0이 적혀 있으면, 아직 달팽이가 이동하지 않았다는 뜻입니다. 
            // turnCount 를 0으로 다시 만들어 주고, 2차원 배열에 값을 입력하며, x, y 를 다음 x,y 로 바꿔 줍니다.
            
        		turnCount = 0;
        		matrix[nextY][nextX] = addValue++;
        		x = nextX;
        		y = nextY;
        	  
        	}else {
            // 위의 조건에 부합하지 않으면, 아예 2차원 배열의 범위 바깥에 있거나, 이미 방문했다는 뜻입니다. 
        		if(turnCount++ == 3) {
              // 만약 turn 을 3번 이상 했는데도 else 에 들어온다면, 방문을 끝마쳤다는 뜻입니다. while 문을 탈출해 줍니다.
        			break;
        		}
            // 위의 turnCount 조건에 들어가지 않는다면, 방향 인덱스를 다음 인덱스로 업데이트 해 줍니다.
        		index = (index + 1) % 3;
        	}
        }

      // 위의 while 문을 탈출했다면, 달팽이가 2차원 배열을 모두 방문한 것입니다. 출력을 위해, 요구되는 1차원 배열로 바꿔 return 해 줍니다.
        index = 0;
        for(y = 0; y < matrix.length; y++) {
        	for(x = 0; x <= y; x++) {
        		array[index++] = matrix[y][x];
        	}
        }
        return array;
	}
  
  // 2차원 배열 내부에 x,y 가 있는지 체크하는 boolean method 입니다.
	static boolean canGo(int x, int y, int[][] matrix) {
		return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
	}
}
