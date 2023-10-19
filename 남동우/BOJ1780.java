import java.io.*;
import java.util.StringTokenizer;

public class BOJ1780 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = makeMatrix(br, n); // 입력을 받아옵니다.

        int[] answer = takePaperCount(matrix, 0, 0, matrix.length, matrix.length);
      // 정답을 배열 형태로 받아, 순서대로 출력합니다.
        System.out.printf("%d\n%d\n%d", answer[0], answer[1], answer[2]);
    }

  // 재귀적으로 문제 조건에 맞춰 종이의 갯수를 구하는 메소드입니다. 
  // 모두 같다면, 어떤 종이로 같은지 그 정보를 반환하며, 다른 것이 존재한다면
  // 가로세로 각 3등분하여 9개로 나눠 재귀적으로 정보를 가지고 옵니다.
    static int[] takePaperCount(int[][] matrix, int startX, int startY, int endX, int endY){
      // 모두 같다면, 어떤 것으로 같은지 정보를 가지고 옵니다.
        if(isAllSame(matrix, startX, startY, endX, endY)){
            return takeOne(matrix[startY][startX]);
        }

        int thirdY = (endY - startY) / 3;
        int thirdX = (endX - startX) / 3;

        int[] count = new int[3];

      // 종이를 9등분하여, count 에 정보를 담아 반환합니다.
        for(int takeY = 0; takeY < 3; takeY++){
            for(int takeX = 0; takeX < 3; takeX++){
                int thirdStartX = startX + thirdX * takeX;
                int thirdStartY = startY + thirdY * takeY;
              // 종이의 시작 지점과 종료 지점을 넣어 주어, 재귀를 수행해 결과를 가져옵니다.
                int[] result = takePaperCount(matrix, thirdStartX, thirdStartY, thirdStartX + thirdX, thirdStartY + thirdY);

              // 가져온 결과 정보를 count 에 기록합니다.
                for(int i = 0; i < 3; i++){
                    count[i] += result[i];
                }
            }
        }
      // 정보를 반환합니다.
        return count;
    }
  // 어떤 종이로 같은지 switch-case 문으로 체크하고 그에 맞게 리턴합니다.
    static int[] takeOne(int element){
        switch(element){
            case -1 : return new int[]{1,0,0};
            case 0 : return new int[]{0,1,0};
            case 1 : return new int[]{0,0,1};
        }
        return null;
    }
  // 왼쪽 위의 숫자와 나머지 숫자가 모두 같은지 확인합니다. 같지 않다면 false, 같다면 true 를
  // 리턴합니다.
    static boolean isAllSame(int[][] matrix, int startX, int startY, int endX, int endY){
        int firstElement = matrix[startY][startX];
        for(int y = startY; y < endY; y++){
            for(int x = startX; x < endX; x++){
                if(matrix[y][x] != firstElement){
                    return false;
                }
            }
        }
        return true;
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int y = 0; y < matrix.length; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int x = 0; x < matrix.length; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
}
