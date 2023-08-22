import java.io.*;
import java.util.*;

public class BOJ17144 {
    static List<Point> conditioner; // 공기청정기의 위치를 확인할 List 입니다.
    static Queue<Integer> queue; // 공기 순환에 사용할 Queue 입니다.
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(1,0),
            Arrays.asList(0,1), Arrays.asList(-1,0), Arrays.asList(0,-1)); // 사방으로 공기가 확산될 때 사용하는 부분입니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        conditioner = new ArrayList<>();
        queue = new ArrayDeque<>();
      // conditioner, queue 를 초기화해 줍니다.

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());

        int[][] matrix = makeMatrix(br,height, width);

      // time 만큼 반복문을 돕니다. 미세먼지를 확산시키고, 공기청정기로 1회 미세먼지 전체를 돌립니다.
        for(int i = 0; i < time; i++){
            // 먼저, 존재하는 미세먼지를 확산시킵니다.
            spread(matrix);
            Point up = conditioner.get(0);
            Point down = conditioner.get(1);
          // 공기청정기 윗부분과 아랫부분을 받아옵니다.
          // 이후, 위의 부분은 반시계방향, 아랫부분은 시계방향으로 가장 가장자리에 있는 미세먼지들을 회전시킵니다.
            rotate(matrix, 0,0, matrix[0].length - 1, up.y, true);
            rotate(matrix, 0, down.y, matrix[0].length - 1, matrix.length - 1, false);
            // 이후 회전시키기
        }

      // 이후, 남아 있는 모든 미세먼지의 수를 세어서, 출력합니다.
        int count = 0;
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] != -1){
                    count += matrix[y][x];
                }
            }
        }
        System.out.println(count);
    }
  // 미세먼지를 퍼뜨리는 메소드입니다.
      static void spread(int[][] matrix){
        int[][] spreadMatrix = new int[matrix.length][matrix[0].length];
        // 먼저, 겉으로 퍼지는 미세먼지를 기록할 2차원 배열을 초기화합니다.

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] > 0){ // matrix 2차원 배열을 순회하며, matrix[y][x] 가 0보다 클 때(-1,0 둘다 아닐 때) 그 지점의 미세먼지를 퍼뜨립니다.
                    spreadOne(matrix, spreadMatrix, x,y);
                }
            }
        }

        // 겉으로 퍼지는 미세먼지를 기록한 matrix 와, 퍼지고 난 후 감소한 matrix 를 합산해 그 결과를 만들어 줍니다.
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                matrix[y][x] += spreadMatrix[y][x];
            }
        }
    }

  // 하나의 미세먼지를 퍼뜨리는 메소드입니다. 
    static void spreadOne(int[][] matrix, int[][] resultMatrix, int x, int y){
        int origin = matrix[y][x]; // 기존에 그 자리에 얼마만큼의 미세먼지가 존재했는지 저장합니다. 
        for(int i = 0; i < 4; i++){
            int newX = x + direction.get(i).get(0);
            int newY = y + direction.get(i).get(1);
          // 사방으로 퍼질때의 새로운 x,y 를 만들어 주고 나서, 2차원 배열 내부에 있는지, 공기청정기가 위치하지는 않는지 체크합니다.
            if(canGo(newX, newY, matrix) && matrix[newY][newX] != -1){ 
              // 위의 조건을 통과한다면, 퍼뜨리는 작업을 합니다. 퍼지는 것을 기록하는 것에 value 를 더해 주고, 거꾸로 기존의 matrix 에서는 퍼진 만큼 빼 줍니다.
                int value = origin / 5;
                resultMatrix[newY][newX] += value;
                matrix[y][x] -= value;
            }
        }
    }
  // 공기청정기를 만나기 전까지 미세먼지를 회전시키는 메소드입니다.
    static void rotate(int[][] matrix, int startX, int startY, int endX, int endY, boolean up){
        queue.clear(); // 혹여나 모르므로, queue를 비워 줍니다. 
        int dirIndex = 0; // 처음에는 오른쪽으로 갑니다. 그래서 0 으로 초기화 해 줍니다.
        int x = startX + 1,y = up ? endY : startY; // 위쪽인지 아래쪽인지에 따라, 초기 x,y 를 세팅해 줍니다.
        int addIndex = up ? -1 : 1; // 위쪽인지 아래쪽인지에 따라, 시계방향으로 돌릴 것인지 반시계방향으로 돌릴 것인지 값을 만들어 줍니다. (1이면 시계방향, -1 이면 반시계방향입니다)
        queue.add(matrix[y][x]); // 초기 x,y 의 2차원 배열 값을 queue 에 집어넣고, 해당 자리를 0으로 만들어 줍니다. 이동시킬 것이기 때문입니다.
      // 초기 x,y 는 공기청정기 바로 오른쪽 자리입니다.
        matrix[y][x] = 0;

      // 공기청정기 바로 오른쪽 자리에서부터, 다시 공기청정기를 만날 때까지 순회합니다.
        while(true){
            int newX = x + direction.get(dirIndex).get(0);
            int newY = y + direction.get(dirIndex).get(1);
          // dirIndex 의 값에 따라 새로운 x,y 를 찾습니다.

          // 새로운 x,y 가 bound 범위에 있는지 체크 해 줍니다. 
          // 범위 내에 있으면서 matrix 에서의 해당 좌표 값이 -1 이면 공기청정기를 만난 것입니다. while 문을 멈춥니다.
          // 그것이 아니라면, 새로운 x,y 에서 존재하던 값을 queue에 집어넣고, 이전에 queue 에 존재하던 값을 그 자리에 넣어 줍니다. 이후, x,y 를 업데이트합니다.
            if(inBound(newX, newY, startX, startY, endX, endY)){
                if(matrix[newY][newX] == -1){
                    break;
                }
                queue.add(matrix[newY][newX]);
                matrix[newY][newX] = queue.remove();
                x = newX; y = newY;
            }
            else { // 만약, bound 범위 바깥에 새로운 x,y 가 존재한다면, 다음 방향으로 dirIndex 값을 바꿔 줍니다.
                dirIndex = (dirIndex + addIndex + 4) % 4;
            }
        }
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < height; y++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < width; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == -1){
                    conditioner.add(new Point(x,y));
                }
            }
        }
        return matrix;
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
    static boolean inBound(int x, int y, int startX, int startY, int endX, int endY){
        return (startX <= x && x <= endX) && (startY <= y && y <= endY);
    }
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
