/**
 *
 * URL : https://www.acmicpc.net/problem/14916
 *
 * 거스름돈
 *
 * 문제
 * 춘향이는 편의점 카운터에서 일한다.
 *
 * 손님이 2원짜리와 5원짜리로만 거스름돈을 달라고 한다. 2원짜리 동전과 5원짜리 동전은 무한정 많이 가지고 있다. 동전의 개수가 최소가 되도록 거슬러 주어야 한다. 거스름돈이 n인 경우, 최소 동전의 개수가 몇 개인지 알려주는 프로그램을 작성하시오.
 *
 * 예를 들어, 거스름돈이 15원이면 5원짜리 3개를, 거스름돈이 14원이면 5원짜리 2개와 2원짜리 2개로 총 4개를, 거스름돈이 13원이면 5원짜리 1개와 2원짜리 4개로 총 5개를 주어야 동전의 개수가 최소가 된다.
 *
 * 입력
 * 첫째 줄에 거스름돈 액수 n(1 ≤ n ≤ 100,000)이 주어진다.
 *
 * 출력
 * 거스름돈 동전의 최소 개수를 출력한다. 만약 거슬러 줄 수 없으면 -1을 출력한다.
 *
 * 예제 입력 1
 * 13
 * 예제 출력 1
 * 5
 * 예제 입력 2
 * 14
 * 예제 출력 2
 * 4
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P14916 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P14916().solution();
  }

  public void solution() throws IOException {
    int N = Integer.parseInt(br.readLine());
    int count = 0;
    while (N > 0) {
      if (N % 5 == 0) {
        count = N / 5 + count;
        break;
      }

      N -= 2;
      count++;
    }

    if (N < 0) {
      bw.write("-1" + System.lineSeparator());
    } else {
      bw.write(count + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }
}
