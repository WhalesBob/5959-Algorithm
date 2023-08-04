import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17615 {
    static int n;
    static String[] arr;
    static int min, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int n=Integer.parseInt(br.readLine());
        arr=br.readLine().split("");
        min=Integer.MAX_VALUE;
        cnt=0;

        leftgo("R");
        min=Math.min(min,cnt);

        leftgo("B");
        min=Math.min(min,cnt);

        rightgo("R");
        min=Math.min(min,cnt);

        rightgo("B");
        min=Math.min(min,cnt);

        System.out.println(min);

    }
    public static void leftgo(String flag){//왼쪽에서부터 탐색을 시작할 경우
        cnt=0;
        String last=null;
        for(int i=0;i<arr.length;i++){
            if(i==0 && arr[0].equals(flag)){
                last=arr[0];
            }
            if(last!=null && last.equals(flag) && !(arr[i].equals(flag))) {
                last=null;
            }
            if(i!=0 && arr[i].equals(flag) && last==null){
                cnt++;
            }
        }
    }

    public static void rightgo(String flag){// 오른쪽으로 탐색을 할 때
        cnt=0;
        String last=null;
        for(int i=arr.length-1;i>=0;i--){
            if(i==arr.length-1 && arr[arr.length-1].equals(flag)){
                last=arr[arr.length-1];
            }
            if(last!=null && last.equals(flag) && !(arr[i].equals(flag))) {
                last=null;
            }
            if(i!=arr.length-1 && arr[i].equals(flag) && last==null){
                cnt++;
            }
        }
    }
}
