package dodo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1347 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String n = br.readLine();//입력은 받지만 사실 사용하지 않았음
        char[] input = br.readLine().toCharArray();//요거로 입력받음

        // 남-서-북-동
        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        char[][] map = new char[101][101];// 전체 크기 지도 설정 (0~100)까지 가능
        int x = 50, y = 50;
        map[x][y] = '.';
        int see = 0;// 처음 기본 위치를 남쪽
        // 기본값을 50으로(중간값) 설정
        int minX = 50, minY = 50;
        int maxX = 50, maxY = 50;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'F') {// 앞으로 전진
                // 현재 바라보는 방향으로 한칸 나간다
                x += dx[see];
                y += dy[see];
                // 현재 값을 기존의 최소, 최대 값과 비교하여 갱신해줌(출력시 사용)
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
                map[x][y] = '.';// 지나간 곳은 .으로 표시
            } else if (input[i] == 'R') {// 오른쪽 방향전환
                // 방향전환으로 바라보는 방향을 바꿔줌
                if (see + 1 >= 4)
                    see = 0;
                else
                    see++;
            } else if (input[i] == 'L') {// 왼쪽 방향 전환
                // 방향전환으로 바라보는 방향을 바꿔줌
                if (see - 1 < 0)
                    see = 3;
                else
                    see--;
            }
        }

        // 출력
        for (int i = minX; i <= maxX; i++) {// 최소 x~최대x까지만 탐색
            for (int j = minY; j <= maxY; j++) {// 최소 y에서 최대 y까지만 탐색
                if (map[i][j] == 0)// 범위 안에 속하지만 지나가지 않은 곳
                    sb.append("#");// #으로 출력
                else// 지나간 곳이라면
                    sb.append(map[i][j]);// 점 출력
            }
            sb.append("\n");// 줄 바꿈
        }
        System.out.println(sb);// 출력
    }
}