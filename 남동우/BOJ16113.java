import java.util.*;
import java.io.*;

public class BOJ16113 {
    static List<char[][]> numberInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] matrix = makeMatrix(br, 5, n / 5); // 줄의 길이와 정보를 입력받습니다.

        StringBuilder answer = new StringBuilder();
        int xIndex = 0; // 보고자 하는 x의 정보를 0으로 초기화합니다.
        putNumberInfo(); // 0~9까지의 2차원 char[][] 배열 정보를 수동으로 넣어 주었습니다.
 
        while(xIndex < matrix[0].length){ // 2차원 배열의 x의 길이보다 작을 때,
            if(matrix[0][xIndex] == '#'){ // 보고자 하는 2차원 배열 정보 왼쪽 위가 # 이면
                int result = getNumberInfo(matrix, xIndex); // 다음으로 보아야 하는 xIndex 배열의 정보와, 현재 인식하는 숫자 정보를 같이 입력받습니다.
                int number = result / 1_000_000; // result 에서 인식한 숫자 정보와 다음 인덱스를 분리합니다.
                int nextIndex = result % 1_000_000;

                answer.append(number); // result 에서 추출한 숫자 정보와 다음 인덱스를 답에 적용해 주고, xIndex 를 업데이트합니다.
                xIndex = nextIndex;
            }else{ 보고자 하는 2차원 배열 정보 왼쪽 위가 # 가 아니면, xIndex 를 하나 증가시킵니다.
                xIndex ++;
            }
        }

        System.out.println(answer); // 정답을 출력합니다.
    }
    static int getNumberInfo(char[][] matrix, int firstIndex){
      // firstIndex 와 matrix의 x 축 길이 차이가 2보다 클 때는, 0, 2~9 사이의 정보를 봅니다. 
      // firstIndex 와 matrix의 x 축 길이 차이가 2보다 크거나 같지 않다면, 1만 볼 수 있습니다.
        if(matrix[0].length - firstIndex >= 2){ 
            for(int i = 2; i <= 9; i++){ // 2부터 9까지 순회하며 지금 보고자 하는 숫자가 그 숫자가 맞는지 체크합니다.
                if(isNumberMatches(matrix, firstIndex, i)){
                    return makeInfo(i, firstIndex + 3); // 매칭되는 숫자가 나온다면, makeInfo() 에 넣어 다음 인덱스와 정보를 리턴합니다.
                }
            }

            if (isNumberMatches(matrix, firstIndex, 0)) return makeInfo(0, firstIndex + 3); // 0과 매칭된다면, makeInfo 에 넣어 다음 인덱스와 정보를 리턴합니다.
        }
        if (isNumberMatches(matrix, firstIndex, 1)) return makeInfo(1, firstIndex + 1); // 1 과 매칭된다면, makeInfo 에 넣어 다음 인덱스와 정보를 리턴합니다.
        return -1; // 아무숫자와도 리턴도지 않는다면, -1 을 리턴합니다.
    }
    static int makeInfo(int info, int nextIndex){
        return info * 1_000_000 + nextIndex; // 숫자 정보와 다음 인덱스 정보를 같이 주기 위해, 이런 장치를 마련합니다.
    }
    static boolean isNumberMatches(char[][] matrix, int firstIndex, int targetIndex){
        char[][] target = numberInfo.get(targetIndex); // 타겟 인덱스를 넣어, 보고자 하는 숫자 char[][] 배열을 가져옵니다.
      // 이후, 2중 for문을 순회하면서 그 숫자가 맞는지 체크합니다.
        for(int y = 0; y < target.length; y++){
            for(int x = 0; x < target[0].length; x++){
                if(target[y][x] != matrix[y][firstIndex + x]){
                    return false;
                }
            }
        }
        return true;
    }
    static void putNumberInfo(){ // 0~9까지 의 char[][] 정보를 수동으로 넣어 주는 메소드입니다.
        numberInfo = new ArrayList<>();

        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','.','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#'},{'#'},{'#'},{'#'},{'#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'#','#','#'}, {'#','.','.'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','.','#'}, {'#','.','#'}, {'#','#','#'}, {'.','.','#'}, {'.','.','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','.'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','.'}, {'#','#','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'.','.','#'}, {'.','.','#'}, {'.','.','#'}, {'.','.','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','#','#'}, {'#','.','#'}, {'#','#','#'}});
        numberInfo.add(new char[][]{{'#','#','#'}, {'#','.','#'}, {'#','#','#'}, {'.','.','#'}, {'#','#','#'}});
    } 
  // 정보를 입력받는 메소드입니다.
    static char[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        char[][] array = new char[height][width];
        char[] input = br.readLine().trim().toCharArray();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int index = y * width + x; // 1차원 배열에서 보아야 하는 인덱스를, x,y 정보를 통해 가져옵니다.
                array[y][x] = input[index];
            }
        }
        return array;
    }
}
