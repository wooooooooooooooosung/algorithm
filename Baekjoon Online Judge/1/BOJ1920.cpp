#include <stdio.h>
#include <algorithm>

using namespace std;

/**
 * Baekjoon Online Judge No.1920 Problem: 수 찾기
 *
 * @date : 2022-10-08
 *
 * @see https://www.acmicpc.net/problem/1920수 찾기
 * @version 1.0.0
 * @author Woosung Jo
 * 
 * Category 자료구조, 이분 탐색
 *
 * Timeout Limit : 1000 ms
 * Memory Limit  : 128 MB
 */
int function_binary_search(int chkVal, int minVal, int maxVal);

const int kArraySize = 100001;
int arr[kArraySize];

int main() {
    int n, m, x;

    scanf("%d\n", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    sort(arr, arr + n);

    scanf("%d\n", &m);
    while (m--) {
        scanf("%d", &x);
        printf("%d\n", binary_search(arr, arr + n, x));
    }

    return 0;
}


/// <summary>
/// 정렬된 배열을 이분 탐색하는 메소드
/// </summary>
/// <param name="chkVal">탐색할 값</param>
/// <param name="minVal">탐색 시작 위치</param>
/// <param name="maxVal">탐색 종료 위치</param>
/// <returns></returns>
int function_binary_search(int chkVal, int minVal, int maxVal) {
    int midVal = (minVal + maxVal) / 2;
    if (arr[midVal] == chkVal) return 1;
    else if (midVal == minVal || midVal == maxVal) return 0;
    else if (arr[midVal] > chkVal) return function_binary_search(chkVal, minVal, midVal);
    else if (arr[midVal] < chkVal) return function_binary_search(chkVal, midVal, maxVal);

    else return 0; // 실행되지 않음. 컴파일 에러 방지용으로 작성
}
