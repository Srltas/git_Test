/**
 *
 * URL : https://www.acmicpc.net/problem/1058
 *
 * 친구
 *
 * 문제
 * 지민이는 세계에서 가장 유명한 사람이 누구인지 궁금해졌다. 가장 유명한 사람을 구하는 방법은 각 사람의 2-친구를 구하면 된다. 어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해선, 두 사람이 친구이거나, A와 친구이고, B와 친구인 C가 존재해야 된다. 여기서 가장 유명한 사람은 2-친구의 수가 가장 많은 사람이다. 가장 유명한 사람의 2-친구의 수를 출력하는 프로그램을 작성하시오.
 *
 * A와 B가 친구면, B와 A도 친구이고, A와 A는 친구가 아니다.
 *
 * 입력
 * 첫째 줄에 사람의 수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 각 사람이 친구이면 Y, 아니면 N이 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 유명한 사람의 2-친구의 수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * NYY
 * YNY
 * YYN
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 3
 * NNN
 * NNN
 * NNN
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 5
 * NYNNN
 * YNYNN
 * NYNYN
 * NNYNY
 * NNNYN
 * 예제 출력 3
 * 4
 * 예제 입력 4
 * 10
 * NNNNYNNNNN
 * NNNNYNYYNN
 * NNNYYYNNNN
 * NNYNNNNNNN
 * YYYNNNNNNY
 * NNYNNNNNYN
 * NYNNNNNYNN
 * NYNNNNYNNN
 * NNNNNYNNNN
 * NNNNYNNNNN
 * 예제 출력 4
 * 8
 * 예제 입력 5
 * 15
 * NNNNNNNNNNNNNNY
 * NNNNNNNNNNNNNNN
 * NNNNNNNYNNNNNNN
 * NNNNNNNYNNNNNNY
 * NNNNNNNNNNNNNNY
 * NNNNNNNNYNNNNNN
 * NNNNNNNNNNNNNNN
 * NNYYNNNNNNNNNNN
 * NNNNNYNNNNNYNNN
 * NNNNNNNNNNNNNNY
 * NNNNNNNNNNNNNNN
 * NNNNNNNNYNNNNNN
 * NNNNNNNNNNNNNNN
 * NNNNNNNNNNNNNNN
 * YNNYYNNNNYNNNNN
 * 예제 출력 5
 * 6
 */

package silver;

import java.io.*;

public class P1058 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new P1058().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[][] distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            char[] friends = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                distance[i][j + 1] = friends[j] == 'Y' ? 1 : Integer.MAX_VALUE >> 2;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int maxValue = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                if (distance[i][j] <= 2) {
                    sum++;
                }
            }
            maxValue = Math.max(maxValue, sum);
        }

        bw.write(maxValue + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
