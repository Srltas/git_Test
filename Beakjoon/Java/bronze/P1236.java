/**
 *
 * URL : https://www.acmicpc.net/problem/1236
 *
 * 성 지키기
 *
 * 문제
 * 영식이는 직사각형 모양의 성을 가지고 있다. 성의 1층은 몇 명의 경비원에 의해서 보호되고 있다. 영식이는 모든 행과 모든 열에 한 명 이상의 경비원이 있으면 좋겠다고 생각했다.
 *
 * 성의 크기와 경비원이 어디있는지 주어졌을 때, 몇 명의 경비원을 최소로 추가해야 영식이를 만족시키는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 성의 세로 크기 N과 가로 크기 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 성의 상태가 주어진다. 성의 상태는 .은 빈칸, X는 경비원이 있는 칸이다.
 *
 * 출력
 * 첫째 줄에 추가해야 하는 경비원의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 4 4
 * ....
 * ....
 * ....
 * ....
 * 예제 출력 1
 * 4
 * 예제 입력 2
 * 3 5
 * XX...
 * .XX..
 * ...XX
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 5 8
 * ....XXXX
 * ........
 * XX.X.XX.
 * ........
 * ........
 * 예제 출력 3
 * 3
 */

package bronze;

import java.util.Scanner;

public class P1236 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        char[][] castle = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                castle[i][j] = str.charAt(j);
            }
        }

        // 행 확인
        int row = 0;
        for (int i = 0; i < N; i++) {
            boolean XCheck = true;
            for (int j = 0; j < M; j++) {
                if (castle[i][j] == 'X') {
                    XCheck = false;
                    break;
                }
            }

            if (XCheck) {
                row++;
            }
        }

        // 열 확인
        int column = 0;
        for (int i = 0; i < M; i++) {
            boolean XCheck = true;
            for (int j = 0; j < N; j++) {
                if (castle[j][i] == 'X') {
                    XCheck = false;
                    break;
                }
            }

            if (XCheck) {
                column++;
            }
        }

        System.out.println(Math.max(row, column));
    }
}
