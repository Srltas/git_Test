/**
 *
 * URL : https://www.acmicpc.net/problem/1463
 *
 * 1로 만들기
 *
 * 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 *
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
 *
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 1
 * 예제 입력 2
 * 10
 * 예제 출력 2
 * 3
 */
package silver;

import java.util.Scanner;

public class P1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] list = new int[N + 1];

        list[1] = 0;
        for (int i = 2; i <= N; i++) {
            list[i] = list[i - 1] + 1;
            if (i % 2 == 0) {
                list[i] = Math.min(list[i], list[i / 2] + 1);
            }
            if (i % 3 == 0) {
                list[i] = Math.min(list[i] , list[i / 3] + 1);
            }
        }
        System.out.println(list[N]);
    }
}
