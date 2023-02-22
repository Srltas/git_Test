/**
 *
 * URL : https://www.acmicpc.net/problem/11582
 *
 * 치킨 TOP N
 *
 * 문제
 * 인하대 주변 치킨칩의 맛의 정도를 측정해 수치화하는 동아리 C.T.P(Chicken Tastes Perfect)의 회장 민호는 치킨집의 맛의 수치를 감소하지 않는 순으로 정렬을 하고 싶었다. 하지만 치킨집이 너무 많아 혼자 정렬을 하기에는 많은 시간이 걸려 C.T.P 회원들을 활용하기로 했다. 치킨집이 N개 있다고 가정을 하자. N개의 치킨의 수치를 무작위로 놓은 뒤 N/2명의 C.T.P 회원이 차례대로 2개의 치킨집을 선택해  정렬을 한다. 그 뒤 N/4명이 차례대로 바로 전 단계의 사람이 정렬한 두 개의 그룹을 차례대로 선택 하여 치킨집을 정렬을 한다. 계속해서 N/8명, N/16명이 정렬을 진행하다가 마지막 사람이 두 개의 정렬된 그룹을 합병하여 작업을 완료한다.
 *
 * 예를 들어 8개의 치킨집의 점수가 (1, 5, 2, 4, 2, 9, 7, 3)과 같을 때, 첫 번째로 4명의 회원이(1, 5), (2, 4), (2, 9), (7, 3)을 각각 정렬하게 되면 (1, 5), (2, 4), (2, 9), (3, 7)이 되고, 두 번째로 2명의 회원이 ((1, 5), (2, 4))와 ((2, 9), (3, 7))을 정렬하면 (1, 2, 4, 5), (2, 3, 7, 9)가 되고, 최종적으로 1명의 회원이 ((1, 2, 4, 5), (2, 3, 7, 9))을 정렬하여 (1, 2, 2, 3, 4, 5, 7, 9)을 만들게 된다.
 *
 * 작업을 진행하던 중 문득 민호는 작업의 중간단계가 궁금해졌다. 현재 단계에서 k명의 회원이 정렬을 진행할 때 정렬을 마친 뒤 결과를 출력해라.
 *
 * 입력
 * 첫 번째 줄에 치킨집의 개수 N(4 ≤ N ≤ 220)이 주어진다. N은 항상 2의 거듭제곱 꼴이다.
 *
 * 두 번째 줄에는 N개의 치킨집의 맛의 수치들이 빈칸을 구분으로 주어지며 이 값은 10억보다 작거나 같은 자연수 또는 0이다.
 *
 * 세 번째 줄에는 현재 정렬을 진행중인 회원들의 수 k(1 ≤ k < N)가 주어진다. k 또한 2의 거듭제곱 꼴이다.
 *
 * 출력
 * 현재 단계에서 k명이 정렬을 진행한다고 할 때, 현재 단계가 완료한 상태를 출력하라.
 *
 * 예제 입력 1
 * 8
 * 1 5 2 4 2 9 7 3
 * 2
 * 예제 출력 1
 * 1 2 4 5 2 3 7 9
 */

package silver;

import java.io.*;
import java.util.StringTokenizer;

public class P11582 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N;
    int K;
    int[] array;
    int[] temp;

    public static void main(String[] args) throws IOException {
        new P11582().solution();
    }

    public void solution() throws IOException {
        N = Integer.parseInt(br.readLine());

        array = new int[N + 1];
        temp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());

        merge(1, N);

        for (int i = 1; i <= N; i++) {
            bw.write(array[i] + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    public void merge(int left, int right) {
        if (right - left < 1) {
            return;
        }

        int mid = left + (right - left) / 2;
        merge(left, mid);
        merge(mid + 1, right);
        check(left, right);
    }

    public void check(int left, int right) {
        if ((right - left) > (N / K)) {
            return;
        }

        int mid = left + (right - left) / 2;
        int l = left;
        int r = mid + 1;
        int index = left;

        for (int i = l; i <= right; i++) {
            temp[i] = array[i];
        }

        while (l <= mid && r <= right) {
            if (temp[l] > temp[r]) {
                array[index] = temp[r];
                index++;
                r++;
            } else {
                array[index] = temp[l];
                index++;
                l++;
            }
        }

        while (l <= mid) {
            array[index] = temp[l];
            index++;
            l++;
        }

        while (r <= right) {
            array[index] = temp[r];
            index++;
            r++;
        }
    }
}
