/**
 *
 * URL : https://www.acmicpc.net/problem/11581
 *
 * 구호물자
 *
 * 문제
 * 서기 2050년 엄청나게 강력한 폭풍이 인천을 강타했다. 강력한 폭풍의 영향으로 모든 사람은 대피소로 대피하였으며, 많은 도로가 유실되었다. 그나마 남아있는 도로도 모든 표지판과 가로등이 작동을 멈춰 제대로 된 길을 찾기란 불가능에 가까웠다.
 *
 * 이런 심각한 상황에 민지는 대피소에 구명 물자를 보내려고 한다. 서기 2050년 인천의 모든 길은 교차로와 도로만으로 이루어져 있다. 한 교차로와 다른 교차로는 일방통행 도로로 연결되어 있으며, 한 교차로와 여러 교차로가 연결될 수 있다. 그리고 도로에 한번 진입하면 교차로에 도착할 때까지 도로를 벗어날 수 없다.
 *
 * 민지는 구호물자로 가득 찬 트럭을 출발시키려고 했지만, 운행을 거부한 트럭운전사들 때문에 난관에 봉착했다. 강력한 폭풍의 영향으로 내비게이션은 정확하지 않고, 도로를 구분할 수 있는 표지판이 망가졌기 때문에 트럭운전사들은 교차로에서 어떤 도로를 선택해야 할지 모른다. 이러한 상황에서 특정 도로를 임의로 선택하면 이미 지나쳤던 교차로를 또다시 방문하는 일이 발생할 수 있고, 만약 그런 상황이 발생하면 트럭의 기름이 부족해 대피소에 도착하지 못할 수 있다.
 *
 * 대피소에 반드시 구호물자를 보내야 한다고 생각하는 민지는 현재 위치인 1번 교차로에서 대피소가 있는 N번 교차로까지 어떤 도로를 선택하며 가더라도 지나친 교차로를 다시 방문하지 않는다는 것을 증명해 트럭 운전사들을 설득하려 한다.
 *
 *
 *
 * 위 그림은 대피소가 3번에 있다고 했을 때 가능한 두 가지 모양이다. 왼쪽 그림에서는 어떠한 도로를 선택하더라도 지나친 교차로를 다시 방문하지 않고 대피소가 있는 3번에 무사히 도착할 수 있다. 하지만 오른쪽 그림에서는 방문했던 교차로를 다시 방문할 가능성이 있다.
 *
 * 민지를 도와 어떠한 길을 선택하더라도 같은 교차로를 다시 방문하는 경우가 있는지 없는지를 판단하는 프로그램을 작성하자.
 *
 * 입력
 * 첫 번째 줄에 교차로의 수 N(1 ≤ N ≤ 100)이 주어진다. 그다음에 1번 교차로부터 N-1번 교차로의 상태가 각각 두 줄에 걸쳐 차례대로 주어진다. (1 ≤ i ≤ N-1)번째 교차로와 연결된 교차로의 수 Mi(0 ≤ Mi ≤ N)가 주어지고 그다음 줄에는 i번째에서 갈 수 있는 교차로의 번호 Ci(1 ≤ Ci ≤ N)가 주어진다. N번 교차로는 대피소가 있는 곳이기 때문에 연결 상태가 주어지지 않는다. 구호물자가 출발하는 장소는 항상 1번이며 대피소가 있는 곳 역시 항상 N번이다.
 *
 * 출력
 * 1번 교차로에서 N번 교차로까지 가는 과정 중 지나쳤던 교차로를 다시 방문하는 경우가 생길 수 있으면 CYCLE, 그렇지 않다면 NO CYCLE을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 2
 * 2 3
 * 1
 * 3
 * 예제 출력 1
 * NO CYCLE
 * 예제 입력 2
 * 3
 * 2
 * 2 3
 * 2
 * 1 3
 * 예제 출력 2
 * CYCLE
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P11581 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P11581().solution();
    }

    public void solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        boolean[][] distance = new boolean[N + 1][N + 1];

        for (int i = 1; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int e = Integer.parseInt(st.nextToken());
                distance[i][e] = true;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][k] && distance[k][j]) {
                        distance[i][j] = true;
                    }
                }
            }
        }

        String ans = "NO CYCLE";
        for (int i = 1; i < N; i++) {
            if (distance[1][i] && distance[i][i]) {
                ans = "CYCLE";
                break;
            }
        }
        bw.write(ans);
        bw.flush();

        bw.close();
        br.close();
    }
}
