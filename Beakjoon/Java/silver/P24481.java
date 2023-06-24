/**
 *
 * URL : https://www.acmicpc.net/problem/24481
 *
 * 알고리즘 수업 - 깊이 우선 탐색 3
 *
 * 문제
 * 오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 *
 * N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다. 정점 R에서 시작하여 깊이 우선 탐색으로 만들어 지는 트리를 깊이 우선 탐색 트리라고 하자. 깊이 우선 탐색 트리에 있는 모든 노드의 깊이(depth)를 출력하자. 시작 정점 R의 깊이는 0이고 방문 되지 않는 노드의 깊이는 -1로 출력하자.
 *
 * 깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.
 *
 * dfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
 *     visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
 *     for each x ∈ E(R)  # E(R) : 정점 R의 인접 정점 집합.(정점 번호를 오름차순으로 방문한다)
 *         if (visited[x] = NO) then dfs(V, E, x);
 * }
 * 입력
 * 첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
 *
 * 다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다. i번째 줄에는 정점 i의 깊이를 출력한다. 시작 정점 R의 깊이는 0이고 방문 되지 않는 노드의 깊이는 -1로 출력하자.
 *
 * 예제 입력 1
 * 5 5 1
 * 1 4
 * 1 2
 * 2 3
 * 2 4
 * 3 4
 * 예제 출력 1
 * 0
 * 1
 * 2
 * 3
 * -1
 */

package silver;

import java.io.*;
import java.util.*;

public class P24481 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  int N, M, R;
  List<Integer>[] list;
  boolean[] visited;
  int[] depth;

  public static void main(String[] args) throws IOException {
      new P24481().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    list = new ArrayList[N + 1];
    depth = new int[N + 1];
    Arrays.fill(depth, -1);
    visited = new boolean[N + 1];
    for (int i = 1; i <= N; i++) {
      list[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      list[u].add(v);
      list[v].add(u);
    }

    for (int i = 1; i <= N; i++) {
      Collections.sort(list[i]);
    }

    depth[R] = 0;
    visited[R] = true;
    DFS(R);

    for (int i = 1; i <= N; i++) {
      bw.write(depth[i] + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }

  private void DFS(int num) {
    for (int next : list[num]) {
      if (!visited[next]) {
        visited[next] = true;
        depth[next] = depth[num] + 1;
        DFS(next);
      }
    }
  }
}
