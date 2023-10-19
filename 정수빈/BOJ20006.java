import java.util.*;
import java.io.*;

public class BOJ20006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [] playerslv = new int[p];
        String [] playersname = new String[p];
        ArrayList<Integer>[] rooms = new ArrayList[p];

        for(int i=0; i<p; i++)
            rooms[i] = new ArrayList<>();

        for(int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            playerslv[i] = Integer.parseInt(st.nextToken());
            playersname[i] = st.nextToken();
        }

        int start = 0;
        
        for(int i=0; i<p; i++) {
            // 해당 룸이 꽉 찼을 경우 start 를 1 늘린다
            if(rooms[start].size() == m) ++start;

            // 해당 룸이 비었을 경우 그냥 넣는다
            if(rooms[start].size() == 0)
                rooms[start].add(i);

            // 해당 룸이 비어 있지 않을 경우 맨 첫 값에 대해 조건을 만족하면 넣는다
            else {
                int add = 0;

                while(true) {
                    if(rooms[start+add].size() == m) {
                        ++add;
                        continue;
                    }
                    if(rooms[start+add].size() == 0) {
                        rooms[start+add].add(i);
                        break;
                    }

                    int level1 = playerslv[i];
                    int level2 = playerslv[rooms[start+add].get(0)];

                    // 첫 플레이어의 -10 ~ +10가 아니면 방을 새로 생성, 방이 꽉 찼어도 새로 생성
                    if (level2-10<=level1 && level2+10>=level1) {
                        rooms[start+add].add(i);
                        break;
                    }
                    // 만족하지 않으면 넣을 수 있을 때까지 뒤로 간다
                    else ++add;
                }
            }
        }

        // 출력한다
        for(int i=0; i<p; i++) {
            if(rooms[i].size() == 0) continue;
            
            if(rooms[i].size() == m) {
                sb.append("Started!\n");
                Collections.sort(rooms[i], (o1,o2)->playersname[o1].compareTo(playersname[o2]));
                
                for(int j=0; j<m; j++) {
                    int player = rooms[i].get(j);
                    sb.append(playerslv[player] + " " + playersname[player]+ "\n");
                }
            }
            else {
                sb.append("Waiting!\n");
                Collections.sort(rooms[i], (o1,o2)->playersname[o1].compareTo(playersname[o2]));

                for(int j=0; j<rooms[i].size(); j++) {
                    int player = rooms[i].get(j);
                    sb.append(playerslv[player] + " " + playersname[player]+ "\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}
