### Indexed Tree 



* 인덱스 트리, 세그먼트 트리, 팬윅 트리 같은 트리를 이용한 자료구조들은 로그 시간복잡도 만에 연산을 수행할 수 있다.
* 일반적으로 구간합을 구하는 경우 누적합을 이용한 방법을 통해 구간합을 구할 수 있지만 한계가 존재한다.
* 구간합을 업데이트 하는 경우 O(NM) 만큼의 시간복잡도를 필요로 하는데 이 때 N이 100,000 이상의 큰 수라면 제한 시간내에 문제를 해결할 수 없다.
* 이와 같은 경우에 위에서 소개한 자료구조들을 활용하여 `update` 할 경우 로그 시간만에 구간에 대한 업데이트를 적용할 수 있다.

<br>



### 트리 초기화 하기 



* 인덱스 트리의 초기화는 다음의 4가지 단계를 거친다.
* 주어진 입력의 개수 N을 이용하여 리프노드의 시작 인덱스 `offset` 의 값을 구한다.
* 최소값이나 최대값 구간합 등 문제 조건에 따라 알맞은 값으로 트리 전체의 초기값을 업데이트한다
* `offset`  을 시작 인덱스로 하거나 `offset` 의 다음 번호를 시작 인덱스로 하여 주어진 데이터를 저장한다.
* 리프노드에서 시작하여 루트노드까지 주어진 문제의 조건에 따라서 값을 업데이트해 나아간다.



```java
int n, offset;
int[] tree;

for(offset = 1; offset <= n + 1; offset *= 2);       // 주어진 데이터의 개수를 통해 리프노드의 시작 인덱스 값을 구한다.

tree = new int[(offset << 1) + 1];                   // 트리의 크기는 주어진 리프노드의 시작인덱스 * 2 + 1 만큼 할당한다.

for(int i = 0; i < tree.length; i++) {
  tree[i] = 0;                                       // 구간합을 구하는 경우						
  tree[i] = Integer.MAX_VALUE;                       // 최소값을 구하는 경우
  tree[i] = Integer.MIN_VALUE;                       // 최대값을 구하는 경우
}

for(int i = 1; i <= n; i++) {
  tree[offset + i] = Integer.parseInt(br.nextLine());// 리프노드의 시작번호 + 1부터 차례로 입력데이터로 초기화한다.
}

for(int i = offset - 1; i > 0; i--) {                // 루트노드까지 트리의 초기 상태를 업데이트한다.
  tree[i] = tree[i * 2] + tree[i * 2 + 1];           // 구간합을 구하는 경우
  tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);  // 최소값을 구하는 경우
  tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);  // 최대값을 구하는 경우
}
```



<br>



### 트리에 주어진 조건으로 질의 하기



* 주어진 범위 `from` 부터 `to` 사이의 구간합 구하기 
* 먼저 주어진 범위를 리프노드의 인덱스와 일치시키기 위해서 주어진 값에 리프노드의 시작 인덱스인 `offset` 만큼을 더한다.
* 리프노드부터 시작해서 루트노드까지 트리를 거슬러 올라가면서 구간합을 구하는데 이 때 두 가지 경우로 나누어진다.
  * 시작 인덱스가 홀수인 경우 부모 인덱스의 구간합에 포함되지 않으므로 해당 값을 결과값에 따로 더한다.
  * 끝나는 인덱스가 짝수인 경우 부모 인덱스의 구간합에 포함되지 않으므로 해당 값을 결과값에 따로 더한다.
  * 이를 루트노드에 도달할 때 까지 반복한다.

```java
public int query(int from, int to) {
  int result = 0;
  
  from += offset; to += offset;
  
  while(from <= to) {
    if(from % 2 == 1) {
      result += tree[from++];
    }
    if(to % 2 == 0) {
      result += tree[to--];
    }
    from /= 2; to /= 2;
  }
  
  return result;
}
```





### 트리에 주어진 조건으로 업데이트 하기 



* 특정 인덱스의 값을 업데이트 하는 경우 

* 주어진 인덱스를 리프노드의 인덱스와 일치시키기 위해서 주어진 값에 리프노드의 시작 인덱스인 `offset` 만큼을 더한다.
* 해당 인덱스의 값을 주어진 값 `value` 로 업데이트한다.
* 해당 인덱스를 포함하는 부모노드부터 루트노드까지 거슬러 올라가면서 값을 업데이트한다.

```java
public void update(int index, int value) {
  index += offset;
  
  tree[index] = value;
  
  while(index > 0) {
    tree[index] = tree[index * 2] + tree[index * 2 + 1]; 
    index /= 2;
  }
}
```





### 트리에서 K 번째 원소의 값 구하기 



* 인덱스 트리를 구성하는 마지막 연산은 K번째 원소의 값을 구하는 연산이다.
* 루트에서 시작하여 아래로 내려가면서 왼쪽의 원소의 개수가 K이상인지 확인한다.
  * 왼쪽 서브트리의 원소의 개수가 K개 이상인 경우 K는 해당 서브트리의 범위에 속한다.
  * 왼쪽 서브트리의 원소의 개수가 K보다 작은 경우 K번째 원소는 오른쪽 서브트리에 속하므로 왼쪽 서브트리의 개수 만큼을 빼준다.
* 이와 같은 연산을 리프노드까지 반복하여 해당 원소의 인덱스 번호는 `index` 에서 리프노드의 시작 인덱스 `offset` 을 뺀 결과 값이다.

```java
public int findKth(int kth) {
  int index = 1, left, right;
  
  while(index < offset) {
    left = index * 2; right = left + 1;
    
    if(tree[left] >= k) {
      index = left;
    } else {
      kth -= tree[left];
      index = right;
    }
  }
  
  return index - offset;
}
```

