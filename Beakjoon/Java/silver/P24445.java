/**
 *
 * URL : https://www.acmicpc.net/problem/24445
 *
 * 알고리즘 수업 - 너비 우선 탐색 2
 *
 * 오늘도 서준이는 너비 우선 탐색(BFS) 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 *
 * N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다. 정점 R에서 시작하여 너비 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
 *
 * 너비 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 내림차순으로 방문한다.
 *
 * bfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
 *     for each v ∈ V - {R}
 *         visited[v] <- NO;
 *     visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
 *     enqueue(Q, R);  # 큐 맨 뒤에 시작 정점 R을 추가한다.
 *     while (Q ≠ ∅) {
 *         u <- dequeue(Q);  # 큐 맨 앞쪽의 요소를 삭제한다.
 *         for each v ∈ E(u)  # E(u) : 정점 u의 인접 정점 집합.(정점 번호를 내림차순으로 방문한다)
 *             if (visited[v] = NO) then {
 *                 visited[v] <- YES;  # 정점 v를 방문 했다고 표시한다.
 *                 enqueue(Q, v);  # 큐 맨 뒤에 정점 v를 추가한다.
 *             }
 *     }
 * }
 * 입력
 * 첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
 *
 * 다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다. i번째 줄에는 정점 i의 방문 순서를 출력한다. 시작 정점의 방문 순서는 1이다. 시작 정점에서 방문할 수 없는 경우 0을 출력한다.
 *
 * 예제 입력 1
 * 5 5 1
 * 1 4
 * 1 2
 * 2 3
 * 2 4
 * 3 4
 * 예제 출력 1
 * 1
 * 3
 * 4
 * 2
 * 0
 */

package silver;

import java.io.*;
import java.util.*;

public class P24445 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    List<Integer>[] list;
    boolean[] visited;
    int[] result;

    int N, M, R;

    public static void main(String[] args) throws IOException {
        new P24445().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        result = new int[N + 1];
        visited = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i]= new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            list[U].add(V);
            list[V].add(U);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i], Collections.reverseOrder());
        }

        BFS(R);

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public void BFS(int start) {
        int count = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        result[start] = count++;

        while (!queue.isEmpty()) {
            int nextNode = queue.poll();

            for (int i : list[nextNode]) {
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    result[i] = count++;
                }
            }
        }
    }
}
