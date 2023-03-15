/**
 *
 * URL : https://www.acmicpc.net/problem/2502
 *
 * 떡 먹는 호랑이
 *
 * 문제
 * 하루에 한 번 산을 넘어가는 떡 장사 할머니는 호랑이에게 떡을 주어야 산을 넘어갈 수 있는데, 욕심 많은 호랑이는 어제 받은 떡의 개수와 그저께 받은 떡의 개수를 더한 만큼의 떡을 받아야만 할머니를 무사히 보내 준다고 한다.
 *
 * 예를 들어 첫째 날에 떡을 1개 주었고, 둘째 날에는 떡을 2개 주었다면 셋째 날에는 1+2=3개, 넷째 날에는 2+3=5개, 다섯째 날에는 3+5=8개, 여섯째 날에는 5+8=13개를 주어야만 무사히 산을 넘어갈 수 있다.
 *
 * 우리는 산을 무사히 넘어온 할머니에게 오늘 호랑이에게 몇 개의 떡을 주었는지, 그리고 오늘이 호랑이를 만나 떡을 준지 며칠이 되었는지를 알아내었다. 할머니가 호랑이를 만나서 무사히 넘어온 D째 날에 준 떡의 개수가 K개임을 알 때, 여러분은 할머니가 호랑이를 처음 만난 날에 준 떡의 개수 A, 그리고 그 다음 날에 호랑이에게 준 떡의 개수 B를 계산하는 프로그램을 작성하시오. 이 문제에서는 항상 1 ≤ A ≤ B 이다.
 *
 * 예를 들어 여섯 번째 날에 산을 무사히 넘어온 할머니가 호랑이에게 준 떡이 모두 41개라면, 호랑이를 만난 첫 날에 준 떡의 수는 2개, 둘째 날에 준 떡의 수는 7개이다. 즉 셋째 날에는 9개, 넷째 날에는 16개, 다섯째 날에는 25개, 여섯째 날에는 41개이다. 따라서 A=2, B=7 이 된다. 단 어떤 경우에는 답이 되는 A, B가 하나 이상일 때도 있는데 이 경우에는 그 중 하나만 구해서 출력하면 된다.
 *
 * 입력
 * 첫째 줄에는 할머니가 넘어온 날 D (3 ≤ D ≤ 30)와 그 날 호랑이에게 준 떡의 개수 K (10 ≤ K ≤ 100,000)가 하나의 빈칸을 사이에 두고 주어진다.
 *
 * 출력
 * 첫줄에 첫 날에 준 떡의 개수 A를 출력하고 그 다음 둘째 줄에는 둘째 날에 준 떡의 개수 B를 출력한다. 이 문제에서 주어진 D, K에 대해서는 항상 정수 A, B (1≤ A ≤ B)가 존재한다.
 *
 * 예제 입력 1
 * 6 41
 * 예제 출력 1
 * 2
 * 7
 * 예제 입력 2
 * 7 218
 * 예제 출력 2
 * 10
 * 21
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P2502 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new P2502().solution();
    }

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[31];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= 30; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int A = 1;
        int B;
        while (true) {
            int num = K - dp[D - 3] * A;
            if (num % dp[D - 2] == 0) {
                B = num / dp[D - 2];
                break;
            }
            A++;
        }

        bw.write(A + System.lineSeparator());
        bw.write(B + System.lineSeparator());
        bw.flush();

        bw.close();
        br.close();
    }
}
