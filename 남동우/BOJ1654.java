import java.io.*;
import java.util.*;

public class BOJ1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        List<Integer> list = makeList(br, k);
        list.sort(Comparator.naturalOrder());

        System.out.println(findMaxLine(list, n));
    }
    static List<Integer> makeList(BufferedReader br, int k) throws IOException{
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < k; i++){
            int n = Integer.parseInt(br.readLine());
            list.add(n);
        }
        return list;
    }
    static long findMaxLine(List<Integer> lineList, int n){

        long left = lineList.get(0) / n > 0 ? lineList.get(0) / n : 1;
        long right = lineList.get(lineList.size() - 1);
        long answer = left;

        while(left <= right){
            long mid = (left + right) / 2;
            if(isAvailable(lineList, mid, n)){
                answer = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return answer;
    }
    static boolean isAvailable(List<Integer> list, long length, int need){
        int count = 0;
        for(Integer lineLength : list){
            count += (lineLength / length);
        }
        return count >= need;
    }
}
