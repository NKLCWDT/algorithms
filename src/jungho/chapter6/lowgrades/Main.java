package jungho.chapter6.lowgrades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 성적이 낮은 순서로 학생 출력 -> 성적이 동일하면 자유롭게 출력
// 학생이름과 점수를 입력 데이터를 받는 것을 보고 '객체' 를 만들어서 정렬해야겠다고 생각
class Student implements Comparable<Student> {

    private String name;

    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    /**
     * ascending : this.score - o.score;
     * descending : o.score - this.score;
     */
    @Override
    public int compareTo(Student o) {
        return this.score - o.score;
    }
}

public class Main {

    private static int n;
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeInputData();
        ascendingSort();
        printData();
    }

    // 입력 데이터 초기화
    private static void initializeInputData() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();
            students.add(new Student(name, score));
        }
    }

    /**
     * 아래와 같은 메서드를 helper method 라고 하는데, 어떤 역할을 도와준다라고 생각하면 된다.
     * 아래의 메서드는 정렬을 도와준다.
     * Arrays.sort() 나 아래와 같은 객체 정렬 자체의 코드를 보고 바로 이해할 수도 있겠지만,
     * 사실 내부 구현이 어떤식으로 동작하는 지에 대한 이해가 없으면, 내부 구현 코드를 까서 이해해야하기 때문에
     * 가독성이나 유지보수 측면에서 helper method 를 사용하곤 한다.
     */
    private static void ascendingSort() {
        students.sort(Student::compareTo);
    }

    // 데이터 출력
    private static void printData() {
        for(Student student : students) {
            System.out.print(student.getName() + " ");
        }
    }
}
