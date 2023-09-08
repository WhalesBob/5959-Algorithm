import java.io.*;
import java.util.*;

public class BOJ16236 {
    static int currentX, currentY;
    static int currentLevel, currentEat;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,-1),
            Arrays.asList(-1,0), Arrays.asList(1,0), Arrays.asList(0,1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] matrix = makeMatrix(br,size);

        currentLevel = 2;
        System.out.println(getAnswer(matrix));
    }
    static int getAnswer(int[][] matrix){
        // 문제 접근 방식
        // 크기가 같은 물고기가 있는 칸은 지나갈 수 있으며, 큰 물고기가 있는 칸은 못 지나갑니다.

        // 1.1 그 자리에서 BFS를 이용해서 먹을 수 있는 가장 짧은 곳의 먹이를 찾습니다.
        // 1.2 같은 time 에 여러 개를 찾았다면, 더 위에 있는 먹이, 같은 y 값을 가질 때는 더 왼쪽 먹이를 먹습니다.
        // --> BFS를 순회하며, 같은 time 에서 먹을 수 있는 먹이 좌표를 y * 1000 + x 와 같은 방식으로 
        // 저장한다면, set에 저장되어 있는 값 중 가장 작은 먹이 좌표 변환값을 먹으면 될 것입니다.

        // 2. 위에서 저장한 값의 최소를 먹습니다.
        // 3. 전역 변수로 몇 개 먹는지 카운팅해서, 레벨을 업데이트합니다.
        // 4. 위의 과정을 한 사이클로 하여, 초를 셉니다.
      
        int time = 0;
        Queue<Long> queue = new ArrayDeque<>();
        Set<Long> set = new HashSet<>();
      // queue와 set을 선언해 주고, findFoodTime 에서 위의 과정을 거칩니다.
        while(true){
            int foodTime = findFoodTime(matrix, queue, set);
            if(foodTime == -1){ // 먹이를 결국 찾지 못하는 케이스에서는, 시간(초) 대신 -1 을 리턴하도록 했습니다.
                break; // 먹이를 못찾았으면, 엄마 상어에게 도움을 요청해야 합니다. while 문을 빠져나가고 정답을
              // 도출합니다.
            }

            time += foodTime; // 먹이를 찾았다면, 먹이를 찾으러 간 시간을 누적 time 에 더해 줍니다.
        }
        return time;
    }
    static int findFoodTime(int[][] matrix, Queue<Long> queue, Set<Long> set){
        queue.clear();
        set.clear(); // queue와 set을 clear 해서 비워 줍니다.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length]; // 중복해서 방문하지 않도록 
      // 2차원 boolean 배열을 만들어 줍니다.
        queue.add(getKey(currentX, currentY)); // 1000 * y + x를 일일이 치기 보다는, 함수로 따로 만들어 관리합니다.
      // queue 에 초기 좌표를 넣어 줍니다.
        visited[currentY][currentX] = true;
        matrix[currentY][currentX] = 0;
      // 본격적으로 움직이기 전에, 상어가 속한 좌표를 0으로 만들어 줍니다.

        int time = 0;
        while(!queue.isEmpty()){
            time++; // 시간을 1초 늘려 주고, 현재 queue의 크기만큼 for문을 돌면서 1초 동안 갈 수 있는 좌표를 BFS 순회합니다.
            int queueSize = queue.size();
            for(int s = 0; s < queueSize; s++) {
                long position = queue.remove(); // queue에서 long 값으로 저장되어 있는 좌표를 빼고, x,y 를 추출합니다.
                int x = (int)(position % 1000);
                int y = (int)(position / 1000);

                for(int i = 0; i < 4; i++){
                    int nextX = x + direction.get(i).get(0);
                    int nextY = y + direction.get(i).get(1);

                    if(canGo(nextX, nextY, matrix)){ // for문으로 4방향을 돌며, 범위 밖으로 나가지 않을 때 
                      // 조건을 체크합니다.
                        if(visited[nextY][nextX]){
                            continue;
                        }

                       // 먹을 수 있다면, set 에 해당 좌표를 long 으로 변환해 저장합니다.
                        if(canEat(nextX, nextY, matrix)){
                            set.add(getKey(nextX, nextY));
                        }

                      // 지나갈 수 있다면, visited 해당 지점을 true로 바꾸고, queue에 넣어 줍니다.
                        if(canBypass(nextX, nextY, matrix)){
                            visited[nextY][nextX] = true;
                            queue.add(getKey(nextX, nextY));
                        }
                    }
                }
            }
          // set이 비어 있지 않다는 이야기는, 해당 time 에서 먹을 수 있는 먹이가 존재한다는 의미입니다.
          // 가장 위쪽, 혹은 가장 위쪽의 왼쪽 좌표로 현재 x, y 를 업데이트해 주고, 레벨과 먹은 먹이 갯수를
          // 업데이트한 후 time 을 return 합니다.
            if(!set.isEmpty()){
                long element = set.stream().min(Long::compare).get();

                currentY = (int)(element / 1000);
                currentX = (int)(element % 1000);
                currentEat ++;
                matrix[currentY][currentX] = 9;
                if(currentEat == currentLevel){
                    currentLevel++;
                    currentEat = 0;
                }
                return time;
            }
        }

        return -1; // queue가 빌 때 까지 return 이 되지 않았다는 것은 먹을 수 있는 먹이가 없다는 의미입니다.
      // -1 을 리턴해 줍니다.
    }
  // 먹을 수 있는지 확인하는 메소드입니다. 0은 단순히 길이므로 0보다 커야 하며, 현재 레벨보다 작은 크기여야 합니다.
    static boolean canEat(int x, int y, int[][] matrix){
        return 0 < matrix[y][x] && matrix[y][x] < currentLevel;
    }
  // 지나갈 수 있는지 확인하는 메소드입니다. 0을 포함해, 단순히 현 level 보다 작거나 같기만 하면 됩니다.
    static boolean canBypass(int x, int y, int[][] matrix){
        return matrix[y][x] <= currentLevel;
    }
  // x,y 를 long 값으로 바꾸는 메소드입니다.
    static long getKey(int x, int y){
        return 1000L * y + x;
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < size; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < size; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == 9){
                    currentY = y;
                    currentX = x;
                }
            }
        }
        return matrix;
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
}
