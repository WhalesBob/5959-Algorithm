import java.io.*;
import java.util.*;

public class BOJ1743 {
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
      // 높이, 길이, 쓰레기 갯수를 입력받습니다. 

        char[][] matrix = makeMatrix(br, height, width, count);
      // 입력을 바탕으로 2차원 char 배열에 쓰레기를 배치해 가져옵니다.
      
        System.out.println(solution(matrix));
      // 정답을 가져와 출력합니다.
    }
    static int solution(char[][] matrix){
        Queue<Integer> queue = new ArrayDeque<>(); 
      // 반복적으로 Queue 를 선언하는 것을 방지하고자, 밖에서 선언해 안에서 재활용합니다.
        int max = 0;
      // max 를 0으로 초기화 합니다.

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] == '#'){
                  // 2차원 배열을 탐색하다가, 쓰레기인 '#' 을 만나면 BFS 를 처리해
                  // 쓰레기를 모두 '.' 으로 바꾸고, 모여 있던 쓰레기 갯수를 가져옵니다.
                    int value = getTrashWeight(queue, matrix, x, y);
                    if(max < value){
                      // 가져온 쓰레기 갯수가 max 보다 크면, max 를 업데이트합니다.
                        max = value;
                    }
                }
            }
        }
        return max; // 도출한 max 값을 리턴합니다.
    }
    static int getTrashWeight(Queue<Integer> queue ,char[][] matrix, int inputX, int inputY){
        queue.clear(); // 재활용하는 queue 를 한번 더 clear 해 줍니다.
        queue.add(getKey(inputY, inputX));
      // x,y 의 경계 크기가 100 정도밖에 되지 않기 때문에,
      // 굳이 새로운 객체를 선언하거나 int[] 배열을 선언해 메모리를 잡아먹는 대신
      // 이미 시스템에 저장된 Integer 값을 활용하기로 합니다.
      // 1000 * y + x 를 queue 에 key 로 저장하고, 
      // queue 에서 key 를 받아와 x, y 를 추출할 수 있을 것입니다. 
      // x, y 를 추출하는 방법은 아래의 코드에서 설명합니다. 

      // 카운트를 1로 초기화하고, 해당 자리 쓰레기를 치웁니다.
        int count = 1;
        matrix[inputY][inputX] = '.';

      // BFS를 순회합니다.
        while(!queue.isEmpty()){
          // queue 에서 key를 가져와서, x,y 를 추출합니다.
          // 위에서 getKey() 메소드를 통해 x,y 를 1000 * y + x 로 변환해 key 로 queue 에 
          // 저장했습니다. 그렇다면, 다시 x, y 를 추출하는 방법은, 
          // 아래 코드와 같이 할 수 있을 것입니다.

            int key = queue.remove();
            int x = key % 1000;
            int y = key / 1000;

          // 받아온 x, y 의 상하좌우를 탐색합니다. 
            for(int i = 0; i < 4; i++){
                int newX = x + direction.get(i).get(0);
                int newY = y + direction.get(i).get(1);
              // 새로운 x,y 를 상하좌우 탐색하면서 찾아 줍니다. 
              // static List<List<Integer>> 로 저장한 direction 에 
              // 상하좌우의 정보를 넣어 두었습니다. 

              // 물리적으로 갈 수 있고, 해당 자리에 쓰레기가 있다면 
              // count 를 1 증가시키고 쓰레기를 "치워 줍니다".
              // 그리고 queue 에 다시 key 값으로 저장해 순회합니다.
                if(canGo(newX, newY, matrix) && matrix[newY][newX] == '#'){
                    count++;
                    matrix[newY][newX] = '.';
                    queue.add(getKey(newY, newX));
                }
            }
        }
        return count;
      // 도출한 count 를 리턴합니다.
    }
  // 물리적으로 갈 수 있는지 여부를 체크합니다. 받아온 x,y 가 배열 내에 있는지 체크하는 것입니다.
    static boolean canGo(int x, int y, char[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
  // 키 값을 생성해 주는 간단한 메소드입니다.
    static int getKey(int y, int x){
        return 1000 * y + x;
    }
  // 2차원 char 배열을 생성해 주는 메소드입니다. 
    static char[][] makeMatrix(BufferedReader br, int height, int width, int count) throws IOException {
        char[][] matrix = new char[height][width];
        for (char[] array : matrix) {
            Arrays.fill(array, '.');
        }
      // 처음에는 모든 자리를 '.' 로 채워줍니다.

      // 그리고, 입력으로부터 x,y 값을 받아와 그 자리에 쓰레기 '#' 를 배치합니다.
        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            matrix[y][x] = '#';
        }
      // 만들어진 matrix 를 반환합니다.
        return matrix;
    }
}
