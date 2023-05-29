/**
 *
 * URL : https://www.acmicpc.net/problem/10282
 *
 * 해킹
 *
 * 문제
 * 최흉최악의 해커 yum3이 네트워크 시설의 한 컴퓨터를 해킹했다! 이제 서로에 의존하는 컴퓨터들은 점차 하나둘 전염되기 시작한다. 어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염되고 만다. 이때 b가 a를 의존하지 않는다면, a가 감염되더라도 b는 안전하다.
 *
 * 최흉최악의 해커 yum3이 해킹한 컴퓨터 번호와 각 의존성이 주어질 때, 해킹당한 컴퓨터까지 포함하여 총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 개수는 최대 100개이다. 각 테스트 케이스는 다음과 같이 이루어져 있다.
 *
 * 첫째 줄에 컴퓨터 개수 n, 의존성 개수 d, 해킹당한 컴퓨터의 번호 c가 주어진다(1 ≤ n ≤ 10,000, 1 ≤ d ≤ 100,000, 1 ≤ c ≤ n).
 * 이어서 d개의 줄에 각 의존성을 나타내는 정수 a, b, s가 주어진다(1 ≤ a, b ≤ n, a ≠ b, 0 ≤ s ≤ 1,000). 이는 컴퓨터 a가 컴퓨터 b를 의존하며, 컴퓨터 b가 감염되면 s초 후 컴퓨터 a도 감염됨을 뜻한다.
 * 각 테스트 케이스에서 같은 의존성 (a, b)가 두 번 이상 존재하지 않는다.
 *
 * 출력
 * 각 테스트 케이스마다 한 줄에 걸쳐 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간을 공백으로 구분지어 출력한다.
 *
 * 예제 입력 1
 * 2
 * 3 2 2
 * 2 1 5
 * 3 2 5
 * 3 3 1
 * 2 1 2
 * 3 1 8
 * 3 2 4
 * 예제 출력 1
 * 2 5
 * 3 6
 */

package gold;

import java.io.*;
import java.util.*;

public class P10282 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    List<Computer>[] list;
    boolean[] infectionComputers;
    int[] time;


    public static void main(String[] args) throws IOException {
        new P10282().solution();
    }

    public void solution() throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            infectionComputers = new boolean[n + 1];
            Arrays.fill(infectionComputers, false);

            time = new int[n + 1];
            Arrays.fill(time, Integer.MAX_VALUE);

            list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list[b].add(new Computer(a, s));
            }

            dijkstra(new Computer(c, 0));

            int infectionComputerCount = 0;
            int infectionTime = 0;
            for (int i = 1; i <= n; i++) {
                if (infectionComputers[i]) {
                    infectionComputerCount++;
                    infectionTime = Math.max(infectionTime, time[i]);
                }
            }

            bw.write(infectionComputerCount + " " + infectionTime);
            bw.write(System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private void dijkstra(Computer firstComputer) {
        PriorityQueue<Computer> queue = new PriorityQueue<>();
        queue.offer(firstComputer);
        infectionComputers[firstComputer.number] = true;
        time[firstComputer.number] = firstComputer.time;

        while (!queue.isEmpty()) {
            Computer currentComputer = queue.poll();
            for (Computer nextComputer : list[currentComputer.number]) {
                infectionComputers[nextComputer.number] = true;

                if (time[nextComputer.number] > time[currentComputer.number] + nextComputer.time) {
                    time[nextComputer.number] = time[currentComputer.number] + nextComputer.time;
                    queue.offer(new Computer(nextComputer.number, time[nextComputer.number]));
                }
            }
        }
    }

    static class Computer implements Comparable<Computer> {
        int number;
        int time;

        public Computer(int number, int time) {
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(Computer computer) {
            return this.time - computer.time;
        }
    }
}
