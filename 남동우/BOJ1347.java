import java.io.*;
import java.util.*;

public class BOJ1347 {
    static int minX, minY, maxX, maxY;
    static List<List<Integer>> direction = Arrays.asList(Arrays.asList(0,1), 
            Arrays.asList(-1,0), Arrays.asList(0,-1), Arrays.asList(1,0));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        char[][] result = getMap(input);
    // 결과가 되는 미로를, input 을 기준으로 만들어 가져옵니다.

    // BufferedWriter 를 이용해 출력합니다.
        for(int y = 0; y < result.length; y++) {
            for(int x = 0; x < result[0].length; x++) {
                bw.write(result[y][x] + "");
            }
            bw.write("\n");
        }

        bw.flush();
    }

  // input 값을 바탕으로 map 을 만들어 반환하는 메소드입니다.
    static char[][] getMap(char[] input){
        Set<Integer> placeSet = new HashSet<>(); 
    // x,y 정보를 Integer 하나로 만들어 저장하기 위한 Set 입니다.
    // 1000 * y + x 값을 Set 에 저장하고, 나중에 활용할 수 있다면, 메모리 초과에 대한 걱정을 확실히 덜 수 있습니다.
    // Integer 값은 Heap 내의 Constant Pool(상수 풀) 에 저장되어 있다고 합니다. int 값은 set 내에서의 메모리 공간을 차지할 수는 있지만,
    // 추가적으로 객체를 생성하는 것은 아니기 때문에 메모리 부분에서의 이점이 있을 것입니다.

        minX = minY = 50;
        maxX = maxY = 50;
        int x = 50, y = 50, d = 0;

      // 초기 시작지점을 (50,50) 으로 둡니다. 이동 길이 최대가 50이므로, 마이너스 방향으로
      // 50 만큼 이동해도 (x,y) 좌표가 모두 양수에 있을 수 있게 하는 것입니다.
      // 1000 * y + x 값에서 x,y 를 원활하게 뽑아내기 위한 장치입니다.

      // 첫 좌표를 placeSet 에 넣습니다.
        placeSet.add(getKey(x,y));

      // input 값을 순차적으로 실행합니다.
        for(char c : input) {
            switch(c) {
                case 'F':
                // 현재 바라보고 있는 방향으로 한 칸 이동합니다.
                    x += direction.get(d).get(0);
                    y += direction.get(d).get(1);

                // 해당 좌표를 기준으로 지금까지 간 좌표 구간의 최대최소값을 갱신합니다.
                    updateMaxMin(x, y);

                // placeSet 에 현재 좌표를 넣어 줍니다.
                    placeSet.add(getKey(x, y));
                    break;
                case 'R':
                // 오른쪽으로 돌아 줍니다.
                // 현재 바라보고 있는 d에 +1 해주면 오른쪽, -1 해 주면 왼쪽 방향이 되게
                // 초기 설정했습니다. 

                    d = (d + 1) % 4;
                    break;
                case 'L':
                // 왼쪽으로 돌아 줍니다. 
                // -1 을 하게 되었을 때 마이너스 값이 되는 것을 방지하기 위해, 
                // + 3으로 바꿔 처리해 줍니다.
                    d = (d + 3) % 4;
                    break;
            }       
        }

      // 현재 가지고 있는 x,y 의 최대최소값을 참고하여 char[][] result 배열을 만들어 줍니다.
      // 이후, result 배열을 모두 '#' 로 채워 줍니다.
        char[][] result = new char[maxY - minY + 1][maxX - minX + 1];
        for(char[] row : result) {
            Arrays.fill(row, '#');
        }

      // placeSet 에 저장해 놓은 좌표 값들을 불러와 방문 처리해 줍니다.
        for(Integer key : placeSet) {
          // key 에서 x,y 값을 추출하고, 최소값들로 빼 줍니다.
            int placeY = (key / 1000) - minY;
            int placeX = (key % 1000) - minX;

          // 방문한 곳을 '#' 에서 '.' 로 바꿔 줍니다.
            result[placeY][placeX] = '.';
        }

      // 결과인 char[][] 배열을 돌려줍니다.
        return result;
    }
    static void updateMaxMin(int x, int y) {
        if(x < minX) {
            minX = x;
        }

        if(x > maxX) {
            maxX = x;
        }

        if(y < minY) {
            minY = y;
        }

        if(y > maxY) {
            maxY = y;
        }
    }

    static int getKey(int x, int y) {
        return 1000 * y + x;
    }
}
