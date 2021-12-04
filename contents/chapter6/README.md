## 정렬(Sorting)
데이터를 특정한 기준에 따라서 순서대로 나열하는것
프로그래밍시에 데이터를 오름차순이나 내림차순으로 정렬해서 사용하는 경우가 많다. 그 만큼 많이 사용되는 알고리즘중 하나이자 종류또한 다양하다. 선택 정렬, 삽입 정렬, 퀵 정렬, 계수 정렬 등등 다양하게 존재한다. 또 각 정렬들을 보면서 알고리즘의 효율성을 쉽게 이해할 수 있다. 

## 선택 정렬(Selection Sort)
오름차순으로 정렬한다는 가정하에 데이터 중에서 가장 작은 데이터를 선택해 맨 앞에 있는 데이터와 바꾸고 그 다음 작은 데이터를 선택해 앞에서 두번째 데이터와 바꾸는 과정을 반복적으로 하는것이다. 제자리(in place) 정렬 알고리즘의 종류다. 즉, 데이터를 넣을 자리를 정하고 어떤 원소를 넣을지 선택하는 알고리즘이다.

![images](/images/chapter6/selectionSort.png)

### 선택정렬 코드

```java
void sort(int arr[])
{
    int n = arr.length;

    // 한칸씩 정렬되지 않은 array을 돈다.
    for (int i = 0; i < n-1; i++)
    {
        // 가장 작은 값을 찾는다.
        int min_idx = i;
        for (int j = i+1; j < n; j++)
            if (arr[j] < arr[min_idx])
                min_idx = j;

        // 가장 작은 값과 첫번째 값을 바꾼다.
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    }
    }
```

### 선택정렬의 시간 복잡도
선택정렬은 N-1번 만큼 작은 수를 찾아 앞으로 보내야 한다. 그래서 매번 작은 수 를 찾기 위한 비교 연산이 필요하다. 그래서 빅오 표기법으로 O(N^2)이라고 표현할 수 있다. 코드를 보면 중첩 반복문이 사용되었기 때문이라 이해할 수 있다. 선택 정렬이 매우 비효율적이라는 것을 알 수 있다. 하지만, 선택정렬이 코딩테스트에 사용될일이 많으니 익숙해져야함.

## 삽입 정렬(Insertion Sort)
데이터를 하나씩 확인하며, 각 데이터를 적절한 위치에 삽입하는 방법이다. 선택정렬에 비해서 실행시간이 더 효율적인 편이다. 또 필요할 때만 위치를 바꾸므로 데이터가 거의 정렬되었을때 효율적이다. 
![images](/images/chapter6/insertionSort.png)

### 삽입정렬 코드
```java
void sort(int arr[])
{
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;

        /* 
        그 전의 값고 비교해서 key보다 더 큰값을 앞으로
        옮겨준다.
         */
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
```

### 삽입 정렬 시간 복잡도
삽입정렬 또한 선택정렬과 마찬가지로 반복문이 두번 중첩되어 시간복잡도는 O(N^2)이다. 선택정렬과 시간이 비슷하게 소요되지만 거의 정렬되어 있는 상태라면 매우 빠르게 동작한다. 보통은 퀵 정렬이 더 효율적이지만 거의 정렬된 상태라면 삽입정렬을 사용하는 것이 더 효율적이다.

## 병합정렬(Merge Sort)
분할 정복 알고리즘 중 하나이다.

>Divide: 2개의 리스트로 나눈다.  
>Conquer: 부분 배열을 정렬한다.  
>Combine: 합쳐준다.    

Merge Sort는 더이상 나누어지지 않을 때 까지 반 씩(1/2)분할하다가 더 이상 나누어지지 않은 경우(데이터가 하나인 배열일 때)에는 자기 자신, 즉 데이터 하나를 반환한다. 데이터가 하나인 경우에는 정렬할 필요가 없기 때문이다. 이 때 반환한 값끼리 combine될 때, 비교가 이뤄지며, 비교 결과를 기반으로 정렬되어 임시 배열에 저장된다. 그리고 이 임시 배열에 저장된 순서를 합쳐진 값으로 반환한다. 실제 정렬은 나눈 것을 병합하는 과정에서 이뤄지는 것이다.

![images](/images/chapter6/mergeSort.png)

## 퀵 정렬(Quick Sort)
가장 많이 사용되는 알고리즘. 병합 정렬과 비슷하게 작동하지고 분할정복의 알고리즘이다. 하지만, 병합정렬과는 다르게 퀵정렬의 경우는 비균등하게 분배된다. 퀵정렬에서는 피벗(pivot)이 사용되는데 큰 숫자와 작은 숫자를 나누기 위한 기준이라고 생각하면된다. pivot을 어떻게 설정하고 분할하느냐에 따라 여러 퀵 정렬을 구분 할 수 있다고 한다. 

>Divide: 2개의 리스트로 나눈다.  
>Conquer: 부분 배열을 정렬한다.  
>Combine: 합쳐준다. 

![images](/images/chapter6/quickSort.png)
이렇게 pivot을 정해 정렬을 한 수 재귀함수의 형태로 작성해 구현할 수 있다. 종료조건은 데이터의 개수가 1개인 경우이다. 

### 퀵 정렬 코드
```java
public class qucikSort {
    private static void quickSort(int[] arr,int start, int end) {
        int part=partition(arr,start,end);
        if(start<part-1) quickSort(arr,start,part-1);
        if(end>part) quickSort(arr,part,end);
    }

    private static int partition(int[] arr,int start,int end) {
        int pivot=arr[(start+end)/2];
        while(start<=end) {
            while(arr[start]<pivot) start++;
            while(arr[end]>pivot) end--;
            if(start<=end) {
                swap(arr,start,end);
                start++;
                end--;
            }
        }
        return start;
    }

    private static void swap(int[] arr,int start,int end) {
        int tmp=arr[start];
        arr[start]=arr[end];
        arr[end]=tmp;
        return;
    }


    public static void main(String[] args) {
        int[] arr= {7,4,2,8,3,5,1,6,10,9};
        quickSort(arr,0,arr.length-1);
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+",");
        }
    }

}

```

### 퀵 정렬 시간 복잡도
퀵정렬은 평균적으로 O(NlogN)의 복잡도를 가지고 있다. 분할이 일어날때 마다 반씩 줄어들어 분할이 이루어지는 횟수가 감소하게 된다. 그래서 log를 사용해 해당 복잡도가 나온다. 하지만 데이터가 이미 정렬 되어 있는 경우에는 삽입정렬이 더 빠르다.

> 지금까지 나온 정렬들은 모두 comparison 방식을 사용한다. 즉, 데이터의 값들을 직접 비교해서 정렬하는 형태를 취한다. 이제 부터는 non comparison 즉, 비교를 하지 않고 정렬을 하는 방법을 보자.

## 계수 정렬(Count Sort)  
특정한 조건이 부합해야만 사용할 수 있지만 매우 빠른 정렬 알고리즘 중하나이다. 또 non comparison방식을 사용하기 때문에 데이터의 크기 범위가 제한되어 정수 형태로 표현할 수 있을 때 만 사용할 수 있다. 

계수 정렬은 가장 큰 데이터와 가장 작은 데이터의 범위가 모두 담길 수 있도록 하나의 리스트를 생성한다. 그리고 그 리스트를 0으로 초기화한다. 각 데이터들의 갯수를 count array에 입력해 나중에 그것을 토대로 출력한다.   

![images](/images/chapter6/countSort.png)

이렇게 따로 array를 만들어 입력해야하는 특성 때문에 1,000,000을 넘지 않을 때 효과적으로 사용할 수 있다. 또 가장 큰 데이터와 가장 작은 데이터의 차이가 적을 때 유리하다.

```java
static void countSort(int[] arr)
{
    int max = Arrays.stream(arr).max().getAsInt();
    int min = Arrays.stream(arr).min().getAsInt();
    int range = max - min + 1;

    //range의 크기인 배열을 만든다.
    int count[] = new int[range];
    //정렬된 결과를 담을 배열을 만든다.
    int output[] = new int[arr.length];


    for (int i = 0; i < arr.length; i++) {
        count[arr[i] - min]++;
    }

    for (int i = 1; i < count.length; i++) {
        count[i] += count[i - 1];
    }

    for (int i = arr.length - 1; i >= 0; i--) {
        output[count[arr[i] - min] - 1] = arr[i];
        count[arr[i] - min]--;
    }

    for (int i = 0; i < arr.length; i++) {
        arr[i] = output[i];
    }
}
```

### 계수 정렬의 시간 복잡도
모든 데이터가 양의 정수인 상황에서 데이터의 개수를 N, 그리고 데이터중 최댓값의 크기를 K라고 할때 계수 정렬 시간복잡도는 O(N+K)이다. 데이터의 범위만 한정되어 있다면 효과적이고 빠르게 동작한다. 기수정렬(Radix Sort)과 더불어 가장 빠르다고 한다. 

> 계수 정렬의 공간 복잡도  
데이터가 0과 999,999 두개만 있다고 해도 리스트의 크기가 100만개가 되도록 선언해야 하기애 동일한 값을 여러개 가지고 있는 경우에 유리하다.

## Java의 정렬 라이브러리


https://www.geeksforgeeks.org/selection-sort/

https://gwang920.github.io/algorithm%20non%20ps/qucikSort/

