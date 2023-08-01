import java.io.*;
import java.*;
import java.util.stream.Collectors;

public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
      // 집의 갯수 n, 공유기의 갯수 c 를 입력받습니다.
      
        List<Integer> inputList = makeList(br, n);
      // 집의 좌표 리스트를 입력받아 정렬한 리스트입니다.
      
        System.out.println(getMaxAdjacencyDistance(inputList, c));
      // 가장 인접한 두 공유기 사이 거리 중 최댓값을, 이진 탐색을 통해서 구합니다.
    }
    static List<Integer> makeList(BufferedReader br, int size) throws IOException{
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        return list.stream().sorted().collect(Collectors.toList());
    }
    static int getMaxAdjacencyDistance(List<Integer> inputList, int numberOfDevice){
        int left = 1;
        int right = inputList.get(inputList.size() -1) - inputList.get(0);
        int answer = left;
      // left 는 가장 짧을 수 있는 1, right 는 두 집 사이 거리 중 가장 먼 거리로 초기화합니다.
      // answer 는 1로 일단 초기화해 둡니다.

      // 이진 탐색을 시작합니다.
        while(left <= right){
            int mid = (left + right) / 2;
            if(isAvailable(inputList, numberOfDevice, mid)){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return answer;
      // 이진 탐색의 결과를 리턴합니다.
    }
    static boolean isAvailable(List<Integer> inputList, int numberOfDevice, int distance){
        int count = 1;
        int currentDevice = inputList.get(0);
      // 이진 탐색의 조건문입니다. 맨 첫번째 집에 무조건 하나 깔아 줍니다.
      // 그리고 마지막으로 설치한 집의 위치를 저장합니다.
      
        for(int i = 1; i < inputList.size(); i++){
            if(inputList.get(i) - currentDevice >= distance){
                count++;
                currentDevice = inputList.get(i);
              // 다음 집과 마지막으로 설치한 집의 거리가, 매개변수로 받는 값보다 클 때, 카운트를 하나씩 증가시키고
              // 다음 디바이스를 설치한 집의 위치를 마지막으로 설치한 집의 위치로 업데이트합니다.
            }
        }
        return count >= numberOfDevice;
      // 이렇게 해서 설치한 공유기의 갯수가, 목표로 하는 공유기의 갯수보다 더 많거나 같게 설치했는지 확인합니다.
      // 목표로 하는 공유기의 갯수보다 같거나 더 많이 설치했다면, distance 의 거리 또한 더 늘려서 테스트해 보아도
      // 좋을 것입니다.
    }
}
