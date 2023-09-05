import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = makeMatrix(br, n, m); // 세로와 가로를 받아서, 그 정보를 바탕으로 2차원 배열 정보를 받아옵니다.

        System.out.println(getAnswer(matrix));
    }
    static int getAnswer(int[][] matrix){
        int max = 0; // 정사각형 한 변의 길이를 0으로 초기화합니다.
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                if(matrix[y][x] > 0 && canGo(x-1,y-1, matrix)){ // 왼쪽 위를 갈 수 있어야, 그것으로부터 정사각형의 길이를 받아올 수 있습니다. 
                  // 그리고, matrix[y][x] 가 0 보다 큰 것을 기준으로 정사각형의 길이를 받습니다.
                    int slide = matrix[y-1][x-1];
                    int up = matrix[y-1][x];
                    int left = matrix[y][x-1];

                    matrix[y][x] = Math.min(Math.min(slide, up), left) + 1; // matrix[y][x] 는 대각선, 위, 왼쪽에서 가장 작은 값을 기준으로 하여 + 1 합니다.
                  // 하나라도 0이면 1, 제일 작은 값이 1이라면 2, 제일 작은 값이 2라면 3이 되는 구조입니다. 대각선, 왼쪽, 위에서 어떤 정사각형이 존재했는지 미리
                  // 메모해 두는 것입니다.
                }

                if(max < matrix[y][x]){
                    max = matrix[y][x]; // 정사각형 한 변의 길이를 업데이트합니다.
                }
            }
        }
        return max * max;
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < height; y++){
            char[] input = br.readLine().trim().toCharArray();
            for(int x = 0; x < width; x++){
                matrix[y][x] = input[x] - '0';
            }
        }
        return matrix;
    }
    static boolean canGo(int x, int y, int[][] matrix){
        return (0 <= x && x < matrix[0].length) && (0 <= y && y < matrix.length);
    }
}
