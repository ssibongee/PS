### LIS 



* LIS의 또 다른 구현방법인 이분탐색을 이용한 방법에 대해서 설명한다.
* 앞서 구현한 다이나믹 프로그래밍을 이용한 방법으로는 주어진 큰 수 N에 대해서 문제를 해결할 수 없다.
* 때문에 이러한 문제를 해결하기 위해서 이분탐색을 통해 LIS 문제를 해결하는 방법에 대해서 알아본다.



<br>





### 이분탐색을 이용하여 LIS를 구하는 방법



* 이분탐색을 이용한 방법은 기존의 DP와는 방법이 조금 다르다.
* DP를 이용한 방법에서는 `i` 번째 원소에서의 최대 LIS길이를 배열에 저장하였지만
* 이분탐색에서는 해당 배열에 LIS가 될 수 있는 가능성이 높은 숫자들을 순서대로 저장한다.
* 이분탐색을 통한 문제해결에서 주의할 점은 해당 배열에 저장된 숫자들이 인덱스가 작은 원소들 부터 순서대로 저장되어있지 않다는 점이다.

* 이분탐색을 이용한 방법은 크게 두 가지 경우로 나누어 문제를 처리한다.
  * `i` 번째 원소를 LIS를 저장한 배열의 마지막 원소보다 큰 경우 LIS 배열의 마지막에 삽입하고 최대 길이를 증가시킨다.
  * 위의 경우가 아니라면 해당 배열의 원소들 중에서 자신보다 큰 수들 중에서 가장 작은 수를 찾아서 자기자신으로 갱신한다.



<br>



C++의 경우 자기 자신보다 큰 수들 중에서 가장 작은 수를 찾는 방법으로 API에서 제공하는 `lower_bound` 메서드를 사용할 수 있지만 자바에서는 이를 지원하고 있지 않으므로 직접 함수를 구현해서 사용해야 한다.



<br>



* 초기화

```java
int N;
int[] arr = new int[N];
int[] dp = new int[N];

int size = 0;                     // 만들수 있는 최장 증가 수열의 길이

dp[size++] = arr[0];              // 만들 수 있는 LIS의 초기 값은 가장 첫번째 원소이다.
```



<br>



* 이분탐색의 결과로 최소 인덱스를 구하는 메서드 구현

```java
public static int lowerBound(int[] dp, int start, int end, int key) {
  
  while(start < end) {
    int mid = (start + end) / 2;
    
    if(dp[mid] >= key) {
      end = mid;
    } else {
      start = mid + 1;
    }
  }
  
  return start;
}
```



<br> 

* 최장 증가 수열 구하기

```java
for(int i = 1; i < n; i++) {
  if(dp[max - 1] < arr[i]) {        // 해당 원소가 만들어진 LIS의 마지막 원소보다 큰 경우
    dp[size++] = arr[i];
  } else {                          // LIS 배열 내에서 해당 원소가 들어갈 최적의 위치를 찾는다.
    int index = lowerBound(dp, 0, size, arr[i]);
    dp[index] = arr[i];
  }
}
```



<br>



* 자바에서 제공하는 `binarySearch` 메서드를 이용하는 방법

```java
for(int i = 1; i < n; i++) {
  if(dp[size - 1] < arr[i]) {
    dp[size++] = arr[i];
  } else {
    int index = Arrays.binarySearch(dp, 0, size, arr[i]);
    if(index < 0) index = -index - 1; // 삽입할 위치를 찾을 수 없는 경우 마지막으로 탐색한 인덱스의 음수 값릅 반환한다.
    dp[index] = arr[i];
  }
}
```

* 위에서 처럼 자바에서 제공하는 `binarySearch` 메서드를 응용해서 문제를 해결할 수도 있지만 직접 구현해서 해결하는 것을 추천한다.



<br>



### 이분탐색을 이용한 최장 증가 수열 경로 트래킹하기



* 이분탐색을 이용한 경로 트래킹 방법은 DP와는 다른 방법으로 접근해야 한다.
* 인덱스와 값의 쌍을 저장할 수 있는 자료구조를 만들어서 해당 자료구조를 타입으로 하는 배열을 LIS배열로 한다.



<br>



* 인덱스와 값의 쌍을 저장할 자료구조 생성

```java
public class Pair {
  int index, value;
  
  Pair(int index, int value) {
    this.index = index;
    this.value = value;
  }
}
```



<br>



* 최장 증가 수열과 그 때의 수열 구하기 

```java
dp[size++] = arr[0];
path[0] = new Pair(0, arr[0]);

for(int i = 0; i < n; i++) {
  if(dp[size - 1] < arr[i]) {
    path[i] = new Pair(size, arr[i]);
    dp[size++] = arr[i];
  } else {
    int index = lowerBound(dp, 0, size, arr[i]);
    dp[index] = arr[i];
    path[i] = new Pair(index, arr[i]);
  }
}
```



<br>



* 최장 증가 수열 경로 복원하기

```java
int index = size - 1;
Stack<Integer> stack = new Stack<>();
for(int i = N - 1; i >= 0; i--) {
  if(index == path[i].index) {
    stack.push(path[i].value);
    index--;
  }
}

while(!stack.isEmpty()) {
  // stack.pop();                 // 역순으로 스택에 저장되어있기 때문에 POP을 하면서 경로를 복원한다.
}

```



<br>



### 관련 문제

* [가장 긴 증가하는 부분 수열 2](https://www.acmicpc.net/problem/12015)
* [가장 긴 증가하는 부분 수열 3](https://www.acmicpc.net/problem/12738)
* [가장 긴 증가하는 부분 수열 5](https://www.acmicpc.net/problem/14003)

