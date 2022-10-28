/**
 *
 * URL : https://www.acmicpc.net/problem/11726
 *
 * 2×n 타일링
 *
 * 문제
 * 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.
 *
 *
 *
 * 입력
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 9
 * 예제 출력 2
 * 55
 */

package silver;

import java.util.Scanner;

public class P11726 {

    private static final long MOD = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] D = new long[1001];
        D[1] = 1;
        D[2] = 2;
        for (int i = 3; i <=N; i++) {
            D[i] = (D[i - 1] + D[i - 2]) % MOD;
        }
        System.out.println(D[N]);
    }
}
