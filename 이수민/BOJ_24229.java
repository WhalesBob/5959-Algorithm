import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Board implements Comparable<Board>{

    int start;
    int end;
    public Board(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Board o) {
        if (this.start == o.start)
            return this.end - o.end;
        else
            return this.start - o.start;
    }
}
public class BOJ_24229 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        ArrayList<Board> temp = new ArrayList<>();
        Queue<Board> boards = new LinkedList<>(); // 겹치는 판자 제거

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            temp.add(new Board(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Collections.sort(temp);

        // 겹치는 판자 부분이 제거된 새로운 판자 정보
        int curS = temp.get(0).start;
        int curE = temp.get(0).end;
        for(Board board : temp){
            // 겹치는 판자 부분이 있다면!
            if (curE >= board.start){
                if (curE < board.end)
                    curE = board.end;
            }
            else {
                // 현재 판자는 이전 판자와 겹치지 않는다면!
                boards.add(new Board(curS, curE));
                curS = board.start;
                curE = board.end;
            }
        }
        boards.add(new Board(curS, curE)); // 마지막 판자 추가


        int maxJump = 2*boards.peek().end - boards.peek().start; // 현재 점프한다면 도달할 수 있는 최대 위치
        int max = boards.poll().end; // 현재 최종 지점의 좌표

        while(!boards.isEmpty()){
            // 점프로 도달할 수 있는 최대위치보다, 다음 판자가 더 멀리 있다면 더 이상 진행 불가
            if (maxJump < boards.peek().start) break;

            Board board = boards.poll();
            maxJump = Math.max(maxJump, 2*board.end- board.start);
            max = Math.max(max, board.end);

        }

        System.out.println(max);
    }
}