/**
 *
 * URL : https://www.acmicpc.net/problem/1700
 *
 * 멀티탭 스케줄링
 *
 * 문제
 * 기숙사에서 살고 있는 준규는 한 개의 멀티탭을 이용하고 있다. 준규는 키보드, 헤어드라이기, 핸드폰 충전기, 디지털 카메라 충전기 등 여러 개의 전기용품을 사용하면서 어쩔 수 없이 각종 전기용품의 플러그를 뺐다 꽂았다 하는 불편함을 겪고 있다. 그래서 준규는 자신의 생활 패턴을 분석하여, 자기가 사용하고 있는 전기용품의 사용순서를 알아내었고, 이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법을 고안하여 보다 쾌적한 생활환경을 만들려고 한다.
 *
 * 예를 들어 3 구(구멍이 세 개 달린) 멀티탭을 쓸 때, 전기용품의 사용 순서가 아래와 같이 주어진다면,
 *
 * 키보드
 * 헤어드라이기
 * 핸드폰 충전기
 * 디지털 카메라 충전기
 * 키보드
 * 헤어드라이기
 * 키보드, 헤어드라이기, 핸드폰 충전기의 플러그를 순서대로 멀티탭에 꽂은 다음 디지털 카메라 충전기 플러그를 꽂기 전에 핸드폰충전기 플러그를 빼는 것이 최적일 것이므로 플러그는 한 번만 빼면 된다.
 *
 * 입력
 * 첫 줄에는 멀티탭 구멍의 개수 N (1 ≤ N ≤ 100)과 전기 용품의 총 사용횟수 K (1 ≤ K ≤ 100)가 정수로 주어진다. 두 번째 줄에는 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어진다. 각 줄의 모든 정수 사이는 공백문자로 구분되어 있다.
 *
 * 출력
 * 하나씩 플러그를 빼는 최소의 횟수를 출력하시오.
 *
 * 예제 입력 1
 * 2 7
 * 2 3 2 3 1 2 7
 * 예제 출력 1
 * 2
 */

package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1700 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P1700().solution();
  }

  public void solution() throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    boolean[] socket = new boolean[101];
    int[] array = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    int answer = 0;
    int putCount = 0;
    for (int i = 0; i < K; i++) {
      int tmp = array[i];

      // 콘센트에 꽂혀 있지 않은 경우
      if (!socket[tmp]) {
        // 콘센트를 꽂을 수 있는 공간이 있는 경우
        if (putCount < N) {
          socket[tmp]  = true;
          putCount++;
        } else { // 콘센트를 꽂을 수 있는 공간이 없는 경우
          List<Integer> list = new ArrayList<>();
          // 현재 꽂혀 있는 콘센트가 나중에도 사용되는지 확인
          for (int j = i; j < K; j++) {
            if (socket[array[j]] && !list.contains(array[j])) {
              list.add(array[j]);
            }
          }

          // 나중에도 사용되는 콘센트가 구멍의 개수보다 작을 경우
          if (list.size() != N) {
            for (int j = 0; j < socket.length; j++) {
              // 나중에 사용되지 않는 기계가 콘센트에 꽂혀 있는 경우 제거
              if (socket[j] && !list.contains(j)) {
                socket[j] = false;
                break;
              }
            }
          } else { // 현재 꽂혀 있는 모든 콘센트가 나중에도 사용될 경우
            int remove = list.get(list.size() - 1); // 가장 마지막에 사용될 콘센트 제거
            socket[remove] = false;
          }
          socket[tmp] = true;
          answer++;
        }
      }
    }
    bw.write(answer + System.lineSeparator());
    bw.flush();

    bw.close();
    br.close();
  }
}
