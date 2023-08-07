import java.io.*;
import java.util.*;

public class BOJ8911 {
	static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),Arrays.asList(1,0),
			Arrays.asList(0,1), Arrays.asList(-1,0));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String input = br.readLine().trim();
			System.out.println(getMinimumSize(input)); // 거북이가 움직인 경로를 포함하는 사각형 사이즈를 받아 출력합니다.
		}
	}
	static int getMinimumSize(String input) {
		char[] inputArray = input.toCharArray();
		Set<Integer> xset = new HashSet<>();
		Set<Integer> yset = new HashSet<>();
  // x가 움직인 좌표와, y가 움직인 좌표를 따로 저장합니다.
    
		int currentX = 0, currentY = 0;
		xset.add(currentX);
		yset.add(currentY);
		int currentDirect = 0;
		// 초기 X,Y 좌표를 저장하고, 초기 방향을 저장합니다.
    
		for(char c : inputArray) {
			if(c == 'F') { // 앞으로 갈때, 현재 x, y 를 바꿔줍니다.
				currentX += direction.get(currentDirect).get(0);
				currentY += direction.get(currentDirect).get(1);
			}else if(c == 'B') { // 뒤로 갈 때 현재의 x,y 를 바꿔줍니다.
				currentX -= direction.get(currentDirect).get(0);
				currentY -= direction.get(currentDirect).get(1);
			}else if(c == 'L') { // 왼쪽으로 갈 때의 인덱스를 업데이트합니다.
				currentDirect = (4 + currentDirect - 1) % 4; 
			}else if(c == 'R') { // 오른쪽으로 갈 때의 인덱스를 업데이트합니다.
				currentDirect = (currentDirect + 1) % 4;
			}
			
			xset.add(currentX); // 업데이트된 x,y 좌표를 각 xset, yset에 저장합니다. 중복은 set 특성상 제거됩니다.
			yset.add(currentY);
		}
		
		List<Integer> xBound = getMaxMin(xset); // x의 최대 최소값을 받습니다.
		List<Integer> yBound = getMaxMin(yset); // y의 최대 최소값을 받습니다.
		
		return (int)(Math.abs(xBound.get(1) - xBound.get(0)) * Math.abs(yBound.get(1) - yBound.get(0)));
    // x의 최대에서 최소를 빼고, y의 최대에서 최소를 뺀 값을 곱해서, 사각형의 넓이를 구해 리턴합니다.
	}
	static List<Integer> getMaxMin(Set<Integer> set){
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(Integer element : set) {
			if(max < element) {
				max = element;
			}
			if(min > element) {
				min = element;
      }
		}
    // for문을 돌면서, 받은 set 의 최대 최소값을 구해 Arrays.asList()로 두 개를 같이 줍니다.
		return Arrays.asList(min, max);
	}
}
