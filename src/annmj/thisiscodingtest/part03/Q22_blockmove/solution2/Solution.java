package annmj.thisiscodingtest.part03.Q22_blockmove.solution2;

import java.util.*;

class Solution {

    private static final int ROW = 0;
    private static final int COLUMN = 1;

    private static final int BLANK = 0;
    private static final int WALL = 1;

    private static final int ROW_LEFT_UP = 0; // 좌측을 축으로 우측을 위로 돌린다.
    private static final int ROW_LEFT_DOWN = 1; // 좌측을 축으로 우측을 아래로 돌린다.
    private static final int ROW_RIGHT_UP = 2; // 우측을 축으로 좌측을 위로 돌린다.
    private static final int ROW_RIGHT_DOWN = 3; // 우측을 축으로 좌측을 아래로 돌린다.

    private static final int COLUMN_UP_LEFT = 0; // 위 를 축으로 아래 를 좌측으로 돌린다.
    private static final int COLUMN_UP_RIGHT = 1; // 위 를 축으로 아래 를 우측으로 돌린다.
    private static final int COLUMN_DOWN_LEFT = 2; // 아래 를 축으로 위 를 좌측으로 돌린다.
    private static final int COLUMN_DOWN_RIGHT = 3; // 아래 를 축으로 위 를 우측으로 돌린다.
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static Set<Robot> path = new HashSet<>();

    private Map<Robot, Integer> distanceByRobot = new HashMap<>();

    class Node {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            return this.x == node.x && this.y == node.y;
        }

        public boolean isFinished(int[][] board) {
            return (this.x == (board.length - 1)) && (this.y == (board.length - 1));
        }
    }

    class Robot {
        private Node left;
        private Node right;
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Robot rotate(int direction) {
            Robot rotated = new Robot(new Node(this.left.x, this.left.y), new Node(this.right.x, this.right.y), state);
            if (state == ROW) {
                rotated.state = COLUMN;
                if (direction == ROW_LEFT_UP) {
                    rotated.right.x = this.left.x - 1;
                    rotated.right.y = this.left.y;
                } else if (direction == ROW_LEFT_DOWN) {
                    rotated.right.x = this.left.x + 1;
                    rotated.right.y = this.left.y;
                } else if (direction == ROW_RIGHT_UP) {
                    rotated.left.x = this.right.x - 1;
                    rotated.left.y = this.right.y;
                } else if (direction == ROW_RIGHT_DOWN) {
                    rotated.left.x = this.right.x + 1;
                    rotated.left.y = this.right.y;
                }
            } else {
                rotated.setState(ROW);
                if (direction == COLUMN_UP_LEFT) {
                    rotated.right.x = this.left.x;
                    rotated.right.y = this.left.y - 1;
                } else if (direction == COLUMN_UP_RIGHT) {
                    rotated.right.x = this.left.x;
                    rotated.right.y = this.left.y + 1;
                } else if (direction == COLUMN_DOWN_LEFT) {
                    rotated.left.x = this.right.x;
                    rotated.left.y = this.right.y - 1;
                } else if (direction == COLUMN_DOWN_RIGHT) {
                    rotated.left.x = this.right.x;
                    rotated.left.y = this.right.y + 1;
                }
            }
            return rotated;
        }

        public boolean isFinished(int[][] board) {
            return this.left.isFinished(board) || this.right.isFinished(board);
        }

        public Robot(Node left, Node right, int state) {
            this.left = left;
            this.right = right;
            this.state = state;
        }

        public Robot(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public int hashCode() {
            return left.hashCode() + right.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Robot robot = (Robot) obj;
            return this.left.equals(robot.left) && this.right.equals(robot.right)
                    || this.right.equals(robot.left) && this.left.equals(robot.right);
        }

        public boolean isRotatable(int[][] board, int direction) {
            int nextLeftX = this.left.x;
            int nextLeftY = this.left.y;
            int nextRightX = this.right.x;
            int nextRightY = this.right.y;

            /* 축이 되는 곳의 대각선에는 벽이 없어야 함.*/
            int diagonalX = 0;
            int diagonalY = 0;

            if (this.state == ROW) {
                if (direction == ROW_LEFT_UP) {
                    nextRightX = this.left.x - 1;
                    nextRightY = this.left.y;
                    diagonalX = this.right.x - 1;
                    diagonalY = this.right.y;
                } else if (direction == ROW_LEFT_DOWN) {
                    nextRightX = this.left.x + 1;
                    nextRightY = this.left.y;
                    diagonalX = this.right.x + 1;
                    diagonalY = this.right.y;
                } else if (direction == ROW_RIGHT_UP) {
                    nextLeftX = this.right.x - 1;
                    nextLeftY = this.right.y;
                    diagonalX = this.left.x - 1;
                    diagonalY = this.left.y;
                } else if (direction == ROW_RIGHT_DOWN) {
                    nextLeftX = this.right.x + 1;
                    nextLeftY = this.right.y;
                    diagonalX = this.left.x + 1;
                    diagonalY = this.left.y;
                }
            } else {
                if (direction == COLUMN_UP_LEFT) {
                    nextRightX = this.left.x;
                    nextRightY = this.left.y - 1;
                    diagonalX = this.right.x;
                    diagonalY = this.right.y - 1;
                } else if (direction == COLUMN_UP_RIGHT) {
                    nextRightX = this.left.x;
                    nextRightY = this.left.y + 1;
                    diagonalX = this.right.x;
                    diagonalY = this.right.y + 1;
                } else if (direction == COLUMN_DOWN_LEFT) {
                    nextLeftX = this.right.x;
                    nextLeftY = this.right.y - 1;
                    diagonalX = this.left.x;
                    diagonalY = this.left.y - 1;
                } else if (direction == COLUMN_DOWN_RIGHT) {
                    nextLeftX = this.right.x;
                    nextLeftY = this.right.y + 1;
                    diagonalX = this.left.x;
                    diagonalY = this.left.y + 1;
                }
            }
            return (isMovable(board, new Robot(new Node(nextLeftX, nextLeftY), new Node(nextRightX, nextRightY)))
                    && board[diagonalX][diagonalY] == BLANK);
        }
    }


    public int solution(int[][] board) {
        int answer = 0;
        Node left = new Node(0, 0);
        Node right = new Node(0, 1);
        Robot robot = new Robot(left, right, 0);
        answer = bfs(board, robot);
        return answer;
    }

    private int bfs(int[][] board, Robot robot) {
        Queue<Robot> Q = new LinkedList<>();
        Q.add(robot);
        distanceByRobot.put(robot, 0);
        int result = Integer.MAX_VALUE;

        while (!Q.isEmpty()) {
            Robot poll = Q.poll();
            if (poll.isFinished(board)) {
                return distanceByRobot.get(poll);
            }
            for (int i = 0; i < 4; i++) {
                int nextLeftX = poll.getLeft().getX() + dx[i];
                int nextLeftY = poll.getLeft().getY() + dy[i];
                int nextRightX = poll.getRight().getX() + dx[i];
                int nextRightY = poll.getRight().getY() + dy[i];
                Robot next = new Robot(new Node(nextLeftX, nextLeftY), new Node(nextRightX, nextRightY), poll.getState());
                if (visited(path, next)) continue;
                if (isMovable(board, next)) {
                    path.add(next);
                    Q.add(next);
                    distanceByRobot.put(next, distanceByRobot.get(poll) + 1);
                }
            }

            for (int i = 0; i < 4; i++) {
                if (poll.isRotatable(board, i)) {
                    Robot next = poll.rotate(i);
                    if (visited(path, next)) continue;
                    path.add(next);
                    Q.add(next);
                    distanceByRobot.put(next, distanceByRobot.get(poll) + 1);
                }
            }
        }
        return result;
    }

    private boolean notInMap(int[][] board, int nextLeftX, int nextLeftY, int nextRightX, int nextRightY) {
        return nextLeftX < 0 || nextLeftX >= board.length
                || nextLeftY < 0 || nextLeftY >= board.length
                || nextRightX < 0 || nextRightX >= board.length
                || nextRightY < 0 || nextRightY >= board.length;
    }

    private boolean isMovable(int[][] board, Robot robot) {
        if (notInMap(board, robot.getLeft().getX(), robot.getLeft().getY(), robot.getRight().getX(), robot.getRight().getY()))
            return false;

        return board[robot.getLeft().getX()][robot.getLeft().getY()] == BLANK && board[robot.getRight().getX()][robot.getRight().getY()] == BLANK;
    }

    public boolean visited(Set<Robot> path, Robot robot) {
        return path.contains(robot);
    }
}
