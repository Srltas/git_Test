 /**
 *
 * URL : https://www.acmicpc.net/problem/6118
 *
 * 숨바꼭질
 *
 * 문제
 * 재서기는 수혀니와 교외 농장에서 숨바꼭질을 하고 있다. 농장에는 헛간이 많이 널려있고 재서기는 그 중에 하나에 숨어야 한다. 헛간의 개수는 N(2 <= N <= 20,000)개이며, 1 부터 샌다고 하자.
 *
 * 재서기는 수혀니가 1번 헛간부터 찾을 것을 알고 있다. 모든 헛간은 M(1<= M <= 50,000)개의 양방향 길로 이어져 있고, 그 양 끝을 A_i 와 B_i(1<= A_i <= N; 1 <= B_i <= N; A_i != B_i)로 나타낸다. 또한 어떤 헛간에서 다른 헛간으로는 언제나 도달 가능하다고 생각해도 좋다.
 *
 * 재서기는 발냄새가 지독하기 때문에 최대한 냄새가 안나게 숨을 장소를 찾고자 한다. 냄새는 1번 헛간에서의 거리(여기서 거리라 함은 지나야 하는 길의 최소 개수이다)가 멀어질수록 감소한다고 한다. 재서기의 발냄새를 최대한 숨길 수 있는 헛간을 찾을 수 있게 도와주자!
 *
 * 입력
 * 첫 번째 줄에는 N과 M이 공백을 사이에 두고 주어진다.
 *
 * 이후 M줄에 걸쳐서 A_i와 B_i가 공백을 사이에 두고 주어진다.
 *
 *
 *
 * 출력
 * 출력은 한줄로 이루어지며, 세 개의 값을 공백으로 구분지어 출력해야한다.
 *
 * 첫 번째는 숨어야 하는 헛간 번호를(만약 거리가 같은 헛간이 여러개면 가장 작은 헛간 번호를 출력한다), 두 번째는 그 헛간까지의 거리를, 세 번째는 그 헛간과 같은 거리를 갖는 헛간의 개수를 출력해야한다.
 *
 * 예제 입력 1
 * 6 7
 * 3 6
 * 4 3
 * 3 2
 * 1 3
 * 1 2
 * 2 4
 * 5 2
 * 예제 출력 1
 * 4 2 3
 */
package silver;

import java.io.*;
import java.util.*;

public class P6118 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N;
    int M;
    int max = Integer.MIN_VALUE;
    int index = 0;
    int maxCount = 0;
    List<Integer>[] array;
    int count[];
    boolean visited[];

    public static void main(String[] args) throws IOException {
        new P6118().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            array[s].add(e);
            array[e].add(s);
        }

        BFS(1);

        bw.write(index + " " + count[index] + " " + maxCount);
        bw.flush();

        bw.close();
        br.close();
    }

    private void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        count = new int[N + 1];
        visited = new boolean[N + 1];

        queue.offer(node);
        visited[node] = true;
        while (!queue.isEmpty()) {
            int nextNode = queue.poll();
            for (int n : array[nextNode]) {
                if (!visited[n]) {
                    queue.offer(n);
                    visited[n] = true;
                    count[n] = count[nextNode] + 1;
                }
            }
        }

        for (int i = 0; i <= N; i++) {
            if (count[i] > max) {
                max = count[i];
                index = i;
                maxCount = 1;
            } else if (max == count[i]) {
                maxCount++;
            }
        }
    }
}
