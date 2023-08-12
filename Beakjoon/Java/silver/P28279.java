/**
 *
 * URL : https://www.acmicpc.net/problem/28279
 *
 * 덱 2
 *
 * 문제
 * 정수를 저장하는 덱을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 *
 * 명령은 총 여덟 가지이다.
 *
 * 1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
 * 2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
 * 3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 5: 덱에 들어있는 정수의 개수를 출력한다.
 * 6: 덱이 비어있으면 1, 아니면 0을 출력한다.
 * 7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 * 8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 * 입력
 * 첫째 줄에 명령의 수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 *
 * 둘째 줄부터 N개 줄에 명령이 하나씩 주어진다.
 *
 * 출력을 요구하는 명령은 하나 이상 주어진다.
 *
 * 출력
 * 출력을 요구하는 명령이 주어질 때마다 명령의 결과를 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 11
 * 6
 * 1 3
 * 1 8
 * 7
 * 8
 * 3
 * 2 5
 * 1 2
 * 5
 * 4
 * 4
 * 예제 출력 1
 * 1
 * 8
 * 3
 * 8
 * 3
 * 5
 * 3
 */

package silver;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P28279 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P28279().solution();
  }

  public void solution() throws IOException {
    Deque<Integer> deque = new ArrayDeque<>();

    int N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      switch (Integer.parseInt(st.nextToken())) {
        case 1:
          deque.offerFirst(Integer.parseInt(st.nextToken()));
          break;
        case 2:
          deque.offerLast(Integer.parseInt(st.nextToken()));
          break;
        case 3:
          bw.write((deque.isEmpty() ? "-1" : deque.pollFirst()) + System.lineSeparator());
          break;
        case 4:
          bw.write((deque.isEmpty() ? "-1" : deque.pollLast()) + System.lineSeparator());
          break;
        case 5:
          bw.write((deque.size()) + System.lineSeparator());
          break;
        case 6:
          bw.write((deque.isEmpty() ? "1" : "0") + System.lineSeparator());
          break;
        case 7:
          bw.write((deque.isEmpty() ? "-1" : deque.peekFirst()) + System.lineSeparator());
          break;
        case 8:
          bw.write((deque.isEmpty() ? "-1" : deque.peekLast()) + System.lineSeparator());
          break;
      }
    }
    bw.flush();

    bw.close();
    br.close();
  }
}
