/**
 *
 * URL : https://www.acmicpc.net/problem/10026
 *
 * 적록색약
 *
 * 문제
 * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
 *
 * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
 *
 * 예를 들어, 그림이 아래와 같은 경우에
 *
 * RRRBB
 * GGBBB
 * BBBRR
 * BBRRR
 * RRRRR
 * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
 *
 * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
 *
 * 둘째 줄부터 N개 줄에는 그림이 주어진다.
 *
 * 출력
 * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 5
 * RRRBB
 * GGBBB
 * BBBRR
 * BBRRR
 * RRRRR
 * 예제 출력 1
 * 4 3
 */

package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P10026 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    int N;
    char[][] picture;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new P10026().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        picture = new char[N][N];
        for (int i = 0; i < N; i++) {
            picture[i] = br.readLine().toCharArray();
        }

        bw.write(BFS() + " ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (picture[i][j] == 'G') {
                    picture[i][j] = 'R';
                }
            }
        }

        bw.write(BFS() + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }

    public int BFS() {
        visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    queue.offer(new Point(i, j));
                    visited[i][j] = true;
                    count++;

                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        char color = picture[point.x][point.y];

                        for (int d = 0; d < 4; d++) {
                            int x = point.x + dx[d];
                            int y = point.y + dy[d];

                            if (x >= 0 && y >= 0 && x < N && y < N) {
                                if (!visited[x][y] && picture[x][y] == color) {
                                    queue.offer(new Point(x, y));
                                    visited[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y  = y;
        }
    }
}
