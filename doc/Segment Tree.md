### Segment Tree



### 세그먼트 트리 초기화



* `init(1, 1, N)` : 초기화 과정을 거쳐서 1번 노드는 1번 노드 부터 시작해서 N번 노드까지의 값을 대표한다는 의미

```java
public int init(int node, int from, int to) {
  if(from == to) {
    return tree[node] = a[from];
  }
  
  int mid = (from + to) / 2;
  
  return tree[node] = init(node * 2, from, mid) + init(node * 2 + 1, mid + 1, to);
}
```





### 세그먼트 트리 질의 하기



* `query(1, 1, N, left, right)` : 1번부터 N번까지를 대표하는 값 중에서 `left` 부터 `right` 까지의 대표값을 구함

```java
public int query(int node, int from, int to, int left, int right) {
  if(from > right || to < left) return 0;            // 범위를 벗어나는 값이면 기본값을 반환한다.
  
  if(from >= left && to <= right) return tree[node]; // 범위에 포함되는 경우 구해놓은 값을 반환한다.
  
  int mid = (from + to) / 2;
  
  return query(node * 2, from, mid, left, right) + 
    						query(node * 2 + 1, mid + 1, to, left, right);
}
```





### 세그먼트 트리 업데이트 하기



```java
public int update(int node, int from, int to, int index, int value) {
  if(index < from || index > to) return tree[node] ;// 범위를 벗어나는 경우 해당 노드의 값을 반환한다.
  
  if(from == to) return tree[node] += value;        // 리프노드에 도달한 경우
  
  int mid = (from + to) / 2;
  
  return tree[node] = update(node * 2, from, mid, index, value) +
    update(node * 2 + 1, mid + 1, to, index, value); 
}
```





### 세그먼트 트리 K번째 원소 구하기



```java
public int findKth(int node, int from, int to, int kth) {
  if(from == to) return from;
  
  int mid = (from + to) / 2;
  
  if(tree[node * 2] >= kth) {
    return findKth(node * 2, from, mid, kth);
  } else {
    return findKth(node * 2 + 1, mid + 1, to, kth - tree[node * 2]);
  }
}
```

