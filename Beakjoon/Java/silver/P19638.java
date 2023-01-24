/**
 *
 * URL : https://www.acmicpc.net/problem/19638
 *
 * 센티와 마법의 뿅망치
 *
 * 문제
 * 센티는 마법 도구들을 지니고 여행을 떠나는 것이 취미인 악당이다.
 *
 * 거인의 나라에 도착한 센티는 자신보다 키가 크거나 같은 거인들이 있다는 사실이 마음에 들지 않았다.
 *
 * 센티가 꺼내 들은 마법 도구는 바로 마법의 뿅망치로, 이 뿅망치에 맞은 사람의 키가 ⌊ 뿅망치에 맞은 사람의 키 / 2 ⌋로 변하는 마법 도구이다. 단, 키가 1인 경우 더 줄어들 수가 없어 뿅망치의 영향을 받지 않는다.
 *
 * 하지만 마법의 뿅망치는 횟수 제한이 있다. 그래서 센티는 마법의 뿅망치를 효율적으로 사용하기 위한 전략을 수립했다. 바로 매번 가장 키가 큰 거인 가운데 하나를 때리는 것이다.
 *
 * 과연 센티가 수립한 전략에 맞게 마법의 뿅망치를 이용한다면 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있을까?
 *
 * 입력
 * 첫 번째 줄에는 센티를 제외한 거인의 나라의 인구수 N (1 ≤ N ≤ 105)과 센티의 키를 나타내는 정수 Hcenti (1 ≤ Hcenti ≤ 2 × 109), 마법의 뿅망치의 횟수 제한 T (1 ≤ T ≤ 105)가 빈칸을 사이에 두고 주어진다.
 *
 * 두 번째 줄부터 N개의 줄에 각 거인의 키를 나타내는 정수 H (1 ≤ H ≤ 2 × 109)가 주어진다.
 *
 * 출력
 * 마법의 뿅망치를 센티의 전략대로 이용하여 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있는 경우, 첫 번째 줄에 YES를 출력하고, 두 번째 줄에 마법의 뿅망치를 최소로 사용한 횟수를 출력한다.
 *
 * 마법의 뿅망치를 센티의 전략대로 남은 횟수 전부 이용하고도 거인의 나라에서 센티보다 키가 크거나 같은 거인이 있는 경우, 첫 번째 줄에 NO를 출력하고, 두 번째 줄에 마법의 뿅망치 사용 이후 거인의 나라에서 키가 가장 큰 거인의 키를 출력한다.
 *
 * 예제 입력 1
 * 1 10 1
 * 20
 * 예제 출력 1
 * NO
 * 10
 * 예제 입력 2
 * 2 10 3
 * 16
 * 32
 * 예제 출력 2
 * YES
 * 3
 * 예제 입력 3
 * 2 10 3
 * 127
 * 8
 * 예제 출력 3
 * NO
 * 15
 * 예제 입력 4
 * 1 1 100000
 * 1
 * 예제 출력 4
 * NO
 * 1
 */

package silver;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P19638 {
    public static void main(String[] args) throws IOException {
        new P19638().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        Queue<Long> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (long n = 0; n < N; n++) {
            queue.offer(Long.parseLong(br.readLine()));
        }

        long count = 0;
        for (long t = 0; t < T; t++) {
            long tallest = queue.poll();
            if (tallest >= H && tallest > 1) {
                queue.offer(tallest / 2);
                count++;
            } else {
                queue.offer(tallest);
                break;
            }
        }

        long giant = queue.poll();
        if (H > giant) {
            bw.write("YES" + System.lineSeparator());
            bw.write(count + System.lineSeparator());
        } else {
            bw.write("NO" + System.lineSeparator());
            bw.write(giant + System.lineSeparator());
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
