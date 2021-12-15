## Union-Find
그래프의 어느 두 노드를 같은 집합으로 묶어주거나, 두 노드가 같은 그룹에 있는지 확인하는 알고리즘 (disjoint-set)  

<br>

### 서로소 집합
수학에서 서로소 집합이란 공통원소가 없는 두 집합을 말한다. ex) [1,2], [3,4]

![IMAGES](/images/chapter10/disjoint-set.png)

  
서로소 집합은 union과 find 이 2개의 연산으로 조작할 수 있다.

- union(합집합) 연산 : 2개의 원소가 포함된 집합을 하나의 집합으로 합치는 연산
- find(찾기) 연산 : 특정한 원소가 속한 집합이 어떤 집합인지 알려주는 연산

<br>

### Union 동작 방식

따로 떨어진 노드가 1~8 까지 존재하는 상황이다.  
union 연산을 진행하면서 노드가 어떻게 하나로 합쳐지는지 살펴보겠다.

<br>

![IMAGES](/images/chapter10/union1.png)

현재 모든 노드들은 각자의 집합을 이루고 있다.
그것을 어떻게 알 수 있냐면, parent 배열을 보고 알 수 있다.

parent 배열에 담긴 값이 자기자신이라면, 해당 노드는 집합의 대표(root)이다.  
각각의 노드가 모두 집합의 대표가 자기자신인 상태이다.

<br>

![IMAGES](/images/chapter10/union2.png)

union은 두 집합을 합치는 연산이다.  
union(1,2)는 "1노드가 속한 집합과 2노드가 속한 집합을 합쳐라" 라는 의미이다.  

1이 속한 집합은 자기자신인 1이었고, 2가 속한 집합 또한 자기자신인 2 이었다.  

이제 2집합이 1을 가리키게 됨 으로써, 1집합에 소속되었다. (보통 더 작은 번호를 갖는 루트로 편입 되도록 한다.)  

<br>

![IMAGES](/images/chapter10/union3.png)

3,4를 union 하는 연산을 수행하였다.

<br>

![IMAGES](/images/chapter10/union4.png)

2번 노드와 3번 노드를 union연산 후 모습이다.  
2번 노드는 1집합에 소속된 상태였다.  
3번 노드는 3집합에 소속된 상태였다.    

1집합이 3집합보다 숫자가 더 작으므로, 3집합이 1집합에 소속되어야 한다.  
따라서, 3집합의 루트(3)노드가 1집합의 루트노드(1)을 가리키게 되었다.    

4노드는 자연스럽게 1집합에 소속되었다. (루트노드가 1노드이기 때문)

<br>

### Find 동작 방식
아래와 같은 상황에서 4번노드에 find 연산을 수행하는 과정을 살펴보겠다.

![IMAGES](/images/chapter10/find1.png)

4번노드는 3번을 가리키고 있다. 3번에 find 연산을 수행한다. (재귀적으로 반복)  
3번노드는 1번을 가리키고 있다. 1번에 find 연산을 수행한다. (재귀적으로 반복)  
1번노드는 1번을 가리키고 있다. 자기자신을 가리키므로 이 노드가 집합 번호가 된다.    

즉, 4번노드는 1번 집합에 속한게 된다.

<br>

### Union 코드
```java
void union(int x, int y) {
    // x,y의 루트를 각각 찾는다.
    x = find(x);
    y = find(y);
    if (x != y) {
        parent[Math.max(x,y)] = Math.min(x,y);
    }
}
```

<br>

### find 코드
- 일반적인 방식
```java
int find(int x) {
    if (x != parent[x]) {
        // 재귀적으로 루트를 찾는다.
        return find(parent[x]);
    }
    return x
}
```

- 경로 압축 방식
```java
int find(int x) {
    if (x != parent[x]) {
        // 재귀적으로 루트를 찾는다.
        parent[x] = find(parent[x]);
    }
    return parent[x]
}
```

경로 압축 방식은 find 함수를 재귀적으로 호출한 뒤에 부모 테이블 값을 갱신하는 기법이다.  
각 노드에 대하여 find 함수를 호출한 이후에, 해당 노드의 루트 노드가 바로 부모 노드가 된다.  
위에 그림에서 4노드에 parent값이 3이 아니라 1이 되게된다.

<br>

### 시간복잡도

일반적으로 유니온파인드를 사용하게 될 경우, 평균적으로 트리의 높이만큼 탐색하는 O(logN)이지만,  
트리를 형성하는 과정에서 사향트리가 될 수 있으며, 이렇게 될 경우 시간복잡도는 O(N)이 되어버린다.  

> 사향트리(skewed tree) : 한쪽방향으로 치우친 트리

  
따라서, 효율성을 위해 Find 과정에서 경로압축을 하게 될 경우 find의 시간 복잡도는 O(a(N))이 되는데  
여기서 a(N)은 아커만 함수를 의미한다. 아커만 함수에서 N이 2^65536일 때, 아커만 함수의 값은 5가 되므로  

유니온 파인드는 상수의 시간복잡도를 가진다고 봐도 무방하다.

> 유니온 파인드 시간복잡도 증명  
> https://www.secmem.org/blog/2021/04/19/Union-Find-Time-Complexity-Proof/

<br>

### 사이클 판별

유니온-파인드를 이용하여 무방향 그래프 내에서의 사이클을 판별할 때 사용할 수 있다는 특징이 있다.  
> 방향 그래프에서의 사이클 여부는 DFS를 이용하여 판별 가능하다.

1. 각 간선을 확인하며 두 노드의 루트 노드를 확인한다.
   1. 루트 노드가 서로 다르다면 두 노드에 대하여 union 연산을 수행한다.
   2. 루트 노드가 서로 같다면 사이클이 발생한 것이다.
   
2. 그래프에 포함되어 있는 모든 간선에 대하여 1번 과정을 반복한다.

```java
public class Main {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        parent = new int[V+1];

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 0; i < V + 1; i++) {
            parent[i] = i;
        }

        // 사이클 발생 여부
        boolean cycle = false;

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // 사이클이 발생한 경우 종료
            if (find(a) == find(b)) {
                cycle = true;
                break;
            } else {
                union(a, b);
            }
        }
        if (cycle) {
            System.out.println("사이클이 발생했습니다.");
        } else {
            System.out.println("사이클이 발생하지 않았습니다.");
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[Math.max(x,y)] = Math.min(x,y);
        }
    }

    private static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}

```

---
### Reference

[그래프 이론 : 유니온 파인드](https://velog.io/@syoung125/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%EA%B7%B8%EB%9E%98%ED%94%84-%EC%9D%B4%EB%A1%A0-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9Cunion-find-%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Kruskal-Algorithm-%EC%9C%84%EC%83%81-%EC%A0%95%EB%A0%ACTopology-Sort)

[이것이 코딩 테스트다](http://www.yes24.com/Product/Goods/91433923)

https://dheldh77.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9C%A0%EB%8B%88%EC%98%A8%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-1
