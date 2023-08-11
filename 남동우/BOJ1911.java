import java.io.*;
import java.util.*;

public class BOJ1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int waterCount = Integer.parseInt(st.nextToken());
        int woodLength = Integer.parseInt(st.nextToken());
        List<Node> waters = makeList(br, waterCount);
        System.out.println(minimumLength(waters, woodLength));
    }
    static int minimumLength(List<Node> waters, int woodLength){
        int count = 0;
        int currentIndex = waters.get(0).start;
      // 처음 시작점을 잡아 주고, count 를 0 으로 세팅합니다.
      // 현재 인덱스를, 업데이트된 인덱스와 다음 웅덩이의 시작점 중 더 큰 값으로 잡아 줍니다.
      // 이후, water.end-currentIndex 를 woodLength로 나눈 값의 올림 값을 잡아 줍니다
      // 몇 개 깔아 두었는지 체크하고, 깔아 놓은 나무판자의 끝 부분으로 currentIndex 를 업데이트합니다.

        for (Node water : waters) {
            currentIndex = Math.max(currentIndex, water.start);
            int value = (int) Math.ceil((water.end - currentIndex) / (double) woodLength);
            count += value;
            currentIndex += woodLength * value;
        }

        return count;
      // 위 과정에서 나온 count 를 돌려줍니다.

    }

  // 입력받는 부분
    static List<Node> makeList(BufferedReader br, int size) throws IOException{
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end));
        }

        list.sort(Comparator.comparingInt(a -> a.start));
        return list;
    }
    static class Node{
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
