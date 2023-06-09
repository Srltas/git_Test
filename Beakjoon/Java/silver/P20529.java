/**
 *
 * URL : https://www.acmicpc.net/problem/20529
 *
 * 가장 가까운 세 사람의 심리적 거리
 *
 * 문제
 * 여러분은 요즘 유행하는 심리검사인 MBTI에 대해 들어보았는가?
 *
 * MBTI(Myers-Briggs Type Indicator)는 C.G.Jung의 심리유형론을 근거로 하여 Katharine Cook Briggs와 Isabel Briggs Myers가 보다 쉽고 일상생활에 유용하게 활용할 수 있도록 고안한 자기보고식 성격유형지표이다. (출처: 위키백과)
 *
 * MBTI는 아래와 같이 네 가지 척도로 사람들의 성격을 구분한다.
 *
 * 외향(E) / 내향(I)
 * 감각(S) / 직관(N)
 * 사고(T) / 감정(F)
 * 판단(J) / 인식(P)
 * 각 척도마다 두 가지 분류가 존재하므로, MBTI는 총
 * $2^4 = 16$가지 유형이 있음을 알 수 있다. 일반적으로 MBTI의 유형들은 각 분류를 나타내는 알파벳 한 글자씩을 따 네 글자로 표시하게 된다. 모든 유형의 목록은 다음과 같다.
 *
 * ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ
 * MBTI 성격 유형을 이용하면 두 사람 사이의 심리적인 거리를 정의할 수 있다. 이는 두 사람의 MBTI 유형에서 서로 다른 분류에 속하는 척도의 수로 정의된다. 예를 들어, MBTI 유형이 ISTJ인 사람과 ISFJ인 사람 사이의 거리는 1이며, INTP인 사람과 ENTJ인 사람 사이의 거리는 2이다.
 *
 * 이 정의를 확장해서 세 사람 사이의 심리적인 거리도 정의할 수 있다. 세 사람
 * $A, B, C$가 있을 때 이들의 심리적인 거리는
 *
 * (
 * $A$와
 * $B$ 사이의 심리적인 거리) + (
 * $B$와
 * $C$ 사이의 심리적인 거리) + (
 * $A$와
 * $C$ 사이의 심리적인 거리)
 *
 * 로 정의한다.
 *
 * 대학교에서 심리학 교수로 일하는 종서는 자신이 가르치는 학생들의 심리적인 특성을 분석하고 싶어한다.
 *
 * 오늘이 생일인 종서를 위해
 * $N$명의 학생들의 MBTI 유형이 주어질 때, 가장 가까운 세 학생 사이의 심리적인 거리를 구해보자.
 *
 * 입력
 * 첫 줄에는 테스트 케이스의 수를 나타내는 정수
 * $T$가 주어진다.
 *
 * 각 테스트 케이스의 첫 줄에는 학생의 수를 나타내는 하나의 정수
 * $N$이 주어지며, 두 번째 줄에는 각 학생의 MBTI 성격 유형을 나타내는 문자열들이 사이에 공백을 두고 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대한 답을 정수 형태로 한 줄에 하나씩 출력한다.
 *
 * 제한
 *
 * $1 \le T \le 50$ 
 *
 * $3 \le N \le 100\,000$ 
 * 각 테스트 케이스의
 * $N$의 합은
 * $100\,000$을 넘지 않는다.
 * 예제 입력 1
 * 3
 * 3
 * ENTJ INTP ESFJ
 * 4
 * ESFP ESFP ESFP ESFP
 * 5
 * INFP INFP ESTP ESTJ ISTJ
 * 예제 출력 1
 * 8
 * 0
 * 4
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P20529 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  StringTokenizer st;

  public static void main(String[] args) throws IOException {
      new P20529().solution();
  }

  public void solution() throws IOException {
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      int minDistance = Integer.MAX_VALUE;
      int N = Integer.parseInt(br.readLine());
      String[] MBTI = br.readLine().split(" ");

      if (MBTI.length > 32) {
        bw.write(0 + System.lineSeparator());
        continue;
      }

      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N - 1; j++) {
          for (int k = j + 1; k < N; k++) {
            String a = MBTI[i];
            String b = MBTI[j];
            String c = MBTI[k];

            minDistance = Math.min(minDistance,
                checkDistance(a, b) + checkDistance(a, c) + checkDistance(b, c));
          }
        }
      }
      bw.write(minDistance + System.lineSeparator());
    }
    bw.flush();

    bw.close();
    br.close();
  }

  private int checkDistance(String a, String b) {
    int count = 0;
    for (int i = 0; i < 4; i++) {
      if (a.charAt(i) != b.charAt(i)) {
        count++;
      }
    }
    return count;
  }
}
