/**
 *
 * URL : https://www.acmicpc.net/problem/1748
 *
 * 수 이어 쓰기 1
 *
 * 문제
 * 1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.
 *
 * 1234567891011121314151617181920212223...
 *
 * 이렇게 만들어진 새로운 수는 몇 자리 수일까? 이 수의 자릿수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100,000,000)이 주어진다.
 *
 * 출력
 * 첫째 줄에 새로운 수의 자릿수를 출력한다.
 *
 * 예제 입력 1
 * 5
 * 예제 출력 1
 * 5
 * 예제 입력 2
 * 15
 * 예제 출력 2
 * 21
 * 예제 입력 3
 * 120
 * 예제 출력 3
 * 252
 */

package silver;

import java.util.Scanner;

public class P1748 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int count = 0;
        int plusNum = 1;
        int digit = 10;
        for (int i = 1; i <= N; i++) {
            if (i % digit == 0) {
                digit *= 10;
                plusNum++;
            }
            count += plusNum;
        }
        System.out.println(count);
    }
}
