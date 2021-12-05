# DFS와 BFS
### 탐색(Search)
많은 양의 데이터를 찾는 과정을 말한다. 대표적인 탐색 알고리즘으로 BFS와 DFS를 꼽을  수 있다. 
## 자료구조(Data Structure)
데이터를 표현하고 관리하고 처리하기 위한 구조

### 스택(Stack)
자료구조중 하나로 사전적의미로는 쌓다, 더미라는 뜻이 있다. 조금 더 쉽게 설명하자면 박스 쌓기를 예로 들 수 있다. 박스는 아래에서 부터 쌓는다 그리고 밑에 있는 박스를 꺼내기 위해서 위에 있는 박스를 하나씩 빼서 꺼낼 수 있다. 이런 구조를 선입후출(Fist In Last Out) 또는 후입선출(Last In First Out)구조라고 한다.
![images](/images/chapter5/stack.png)

__Stack 코드__
```java 
import java.util.Stack; //import

Stack<Integer> stack = new Stack<>(); //int형 스택 선언
stack.push(1);     // stack에 값 1 추가
stack.push(2);     // stack에 값 2 추가
stack.pop();       // stack에 값 출력 & 제거
stack.clear();     // stack의 전체 값 제거 (초기화)
stack.push(3);     // stack에 값 3 추가
stack.peek();     // stack의 가장 상단의 값 출력
stack.empty();     // stack이 비어있는제 check (비어있다면 true)
stack.contains(1) // stack에 1이 있는지 check (있다면 true)
```
등등 이외에도 자바에서 제공하는 스택 클래스를 사용해서 쉽게 스택을 사용할 수 있다.

### 큐(Queue)
큐는 스택과는 다르게 대기줄이라고 생각하면 편하다. 먼저온 사람이 먼저 갈 수 있는 것처럼 아주 공정한 자료구조이다. 이런 구조를 선입선출(First In First Out)이라고 한다. 
![images](/images/chapter5/stack.png)

Enqueue : 큐 맨 뒤에 데이터 추가  
Dequeue : 큐 맨 앞쪽의 데이터 삭제

__Queue 코드__
```java
import java.util.LinkedList; //import
import java.util.Queue; //import
Queue<Integer> queue = new LinkedList<>();
queue.add(1);     // queue에 값 1 추가
queue.add(2);     // queue에 값 2 추가
queue.offer(3);
queue.poll();       // queue에 첫번째 값을 반환하고 제거 비어있다면 null
queue.remove();     // queue에 첫번째 값 제거
queue.clear();      // queue 초기화
```
> 자바에서 queue는 쉽게 데이터를 빼고 더할 수 있는 linkedlist로 구현된다.

> __offer와 remove의 차이__  
>  add(value) 메소드는 큐가 꽉차는 상황에서 IllegalStateException을 발생시킨다. 반면, offer(value) 메소드는 false를 반환하도록 되어 있다.

### 재귀 함수
자기 자신을 호출하는 함수  
종료조건을 필수적으로 명시해야 된다. 자기 자신을 호출하기 때문에 종료조건을 명시하지 않으면 무한호출될 수 있다.  
컴퓨터 내부에서 재귀함수는 스택자료구조를 사용한다. 계속해서 자기자신을 호출하기 때문에 마지막에 호출한 함수가 끝이나야 나머지 함수들도 종료되기 때문이다. 따라서 스택 자료구조를 활용해야하는 알고리즘은 재귀함수를 이용해서 구현될 수 있다. DFS 또한 대표적인 예다.

### 팩토리얼 예제로 반복문과 재귀를 비교해보자
```java
//반복문
public static void iteration(int num)
{
    int result = 1;
    for(int i=0; i<num+1; i++){
        result *= i;
    }
    return result;
}
public static void reucursion(int num)
{
    //함수 종료
    if(num <= 1){
        return 1;
    }
    //자기 자신 호출
    return num * recursion(num-1);
}
```
 > 점화식(재귀식)은 특정 함수를 자신보다 더 작은 변수에 대한 함수와의 관계로 표현한것을 뜻한다.   
 - 팩토리얼을 점화식으로 하면 
   - n이 0혹은 1일때 : factorial(n) = 1
   - n이 1보다 클때: factorial(n) = n * factorial(n-1)

### 그래프(Graph)
노드(Node)와 그 노드를 연결하는 간선(Edge)으로 표현되어 있는 자료구조  
연결된어 있는 객체간의 관계를 표현할 수 있다. 조금 더 알아듣기 쉽게 예를 들자면 A라는 도시(노드)와 B라는 도시를 연결하는 도로(간선)이 있고  A에서 B로 가려면 도로(간선)을 거친다. 또 이렇게 간선으로 연결되어 있을 시에는 두 노드가 인접하다라고 표현할 수 있다.
>노드를 정점(vertex)라고도 말함

### 그래프 구현 방법 
#### __인접행렬__
2차원 배열에 노드가 연결된 형태를 기록하는 방식
boolean배열을 통해 i와 j가 연결되어 있으면 true 아니면 false  
그래프내에 간선의 수가 많은 밀집 그래프의 경우(Dense Graph) 유리하다. 
```java
if(간선 (i, j)가 그래프에 존재)
  matrix[i][j] = 1;
else
  matrix[i][j] = 0;
```
- 장점
  - 두 정점의 연결하는 간선의 여부를 바로 알 수 있다.
- 단점
  - 어떤 노드에 인접한 노드를 찾기 위해서는 모든 노드 전부 순회해야한다.

  - 그래프에 존재하는 간선의 수를 알기 위해서는 인접 행렬 전체를 조사해야한다. O(n^2)
  - 노드가 많을 수록 메모리 불필요하게 낭비됨


#### __인접리스트(Adjacency List)__
그래프를 표현하는 가장 일반적인 방식  
모든 정점을 인접 리스트에저장하는 방식이다.
```java
0: 1
1: 2
2: 0, 3
3: 2
4: 6
5: 4
6: 5
```

그래프내에 간선의 수가 적은 희소 그래프의 경우(Sparse Graph) 유리하다. 
- 장점
  - 연결된 정보만 저장하기에 메모리를 효율적으로 사용
  - 어떤 노드에 인접한 노드를 쉽게 찾을 수 있다.

- 단점
  - 간선의 정보를 얻는데에 많은 시간이 소요된다.

## DFS와 BFS
둘다 그래프를 탐색하는 알고리즘의 종류
### DFS(Depth First Search)
깊이 우선 탐색이라고 부르며 그래프에서 한 브랜치를 타고 깊이내려가서 그 브랜치의 마지막을 찍고 올라와 다른 브랜치로 넘어가서 탐색하는 방식

__DFS의 특징__
- 자기 자신을 호출하는 특징을 가지고 있어 스택이나 재귀를 사용한다.
- 어떤 노드를 방문했는지 반드시 검사해야한다. 
- 모든 노드를 방문해야 할때 자주 사용한다.

__DFS의 동작과정__
1. 시작 노드를 스택에 삽입 후 방문 처리
2. 스택의 최상단 노드에 방문하지 않은 인접 노드가 있으면 그 노드를 스택에 넣고 방문처리. 없으면 스택에서 최상단 노드를 꺼낸다.(반복)
![images](/images/chapter5/dfs.png)
> 코딩테스트에서는 낮은 순서부터 처리하도록 명시하는 경우가 종종 있어 보통 낮은 순서부터 처리하도록 구현

### BFS(Breadth First Search)
너비 우선 탐색이라는 의미를 가진다. 가까운 노드부터 즉 인접한 노드부터 탐색하는 알고리즘이다.

__BFS의 특징__
- 인접한 노드를 차례로 저장한 후 꺼낼 수 있느 Queue사용한다.
- 어떤 노드를 방문했는지 반드시 검사해야한다.
- 두 노드 사이의 최단 경로 또는 임의의 경로르 찾을 때 사용한다. 

__BFS의 동작과정__
1. 시작노드를 큐에 삽입 후 방문처리
2. 큐에서 노드를 꺼내 해당 노드중 방문하지 않은 모든 노드를 큐에 삽입(반복)
![images](/images/chapter5/bfs1.png)
![images](/images/chapter5/bfs2.png)

### BFS와 DFS의 차이
백준 1260문제를 통해 bfs와 dfs의 차이를 명확하게 확인해보자.
```java
public static void dfs(int V){
    visit[V] = true;//방문했음을 표시해준다.
    System.out.print(V + " ");
    if(V == graph.length){
        return;
    }
    for(int j=0; j < graph.length; j++){
        if(graph[V][j] == 1 && visit[j] == false){//방문하지 않았고 만일 간선이 존재할때
            dfs(j);
        }
    }
}

public static void bfs(int V){
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(V);
    visit[V] = true;
    System.out.print(V + " ");
    while(!queue.isEmpty()){
        int temp = queue.poll();
        for(int i=1; i< graph.length; i++){//그래프의 길이 만큼 다 둘러본다.
            if(graph[temp][i] == 1 && visit[i] == false){//방문하지 않았고 만일 간선이 존재할때
                queue.add(i);
                visit[i] = true;
                System.out.print(i + " ");
            }
        }
    }
}
```

### DFS와 BFS의 비교




>참고자료  

https://coding-factory.tistory.com/601

https://coding-factory.tistory.com/602

https://gmlwjd9405.github.io/2018/08/13/data-structure-graph.html

https://gmlwjd9405.github.io/2018/08/14/algorithm-dfs.html

https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html