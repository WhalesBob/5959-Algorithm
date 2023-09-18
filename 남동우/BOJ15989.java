import java.io.*;

public class BOJ15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[][] matrix = new long[4][10_001]; // 1,2,3이 각 포함되어 있는 경우의 수를
        // 직관적으로 구하기 위해, 그리고 혹시나 오버플로우가 날 것을 대비하기 위해 long 2차원
        // 배열을 만듭니다.
        for(int i = 0; i <= 10000; i++){
            matrix[1][i] = 1; // 1로만 만들 수 있는 해당 수의 경우의 수를 1로 다 초기화해줍니다.

        }
        // 2를 2로 만들수 있는 경우의 수, 3을 3으로 만들 수 있는 경우의 수,
        // 3을 2로 만들 수 있는 경우의 수를 각 1로 초기화해줍니다.
        matrix[2][2] = 1; matrix[3][3] = 1; matrix[2][3] = 1;

        // 4부터 10,000까지 일단 모두 구해 준 뒤, 나중에 입력을 받으면 그때
        // 답만 출력할 수 있도록 합니다.
        for(int i = 4; i <= 10000; i++){
            matrix[2][i] = matrix[1][i-2] + matrix[2][i-2];
            // i 를 1 과 2로 만들 수 있는 경우의 수는, 1만 들어간 것에 +2 하는 방법,
            // 그리고 2가 포함되어 있는 것에 +2 해준 방법입니다.
            matrix[3][i] = (matrix[1][i-3] + matrix[2][i-3] + matrix[3][i-3]);
            // i 을 1, 2, 3 으로 만들 수 있는 경우의 수는 위와 비슷하게 해 주면 됩니다.
        }


        // 케이스를 입력받고, 전체 경우의 수를 출력합니다.
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            int c = Integer.parseInt(br.readLine());
            bw.write((matrix[1][c] + matrix[2][c] + matrix[3][c]) + "\n");
        }
        bw.flush();
    }
}
