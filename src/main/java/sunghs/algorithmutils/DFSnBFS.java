package sunghs.algorithmutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sunghs.algorithmutils.data.Coordinate;
import sunghs.algorithmutils.data.TreeNode;

@Slf4j
public class DFSnBFS {

    public static TreeNode get() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(2);
        root.left.right.right = new TreeNode(6);
        root.right = new TreeNode(11);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(13);
        return root;
    }

    /**
     * TreeNode 에 대한 dfs 순회
     *
     * @param root TreeNode 의 root
     * @return 순회 순서 배열
     */
    public static int[] treeNode_dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            // current에 왔으므로 마킹
            TreeNode current = stack.pop();
            result.add(current.val);

            // left의 if가 더 빠르므로, stack 의 아랫쪽에 쌓인다. (root -> left -> left -> left -> right -> right .. 로 쌓이게 될텐데 stack은 꺼내면 right 부터 나오게 되므로)
            // 따라서 아래 코드는 right부터 돌게 된다.
            // 왼쪽순회가 먼저 필요하면 if문 위치를 바꾸면 된다.
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        return result.stream().mapToInt(value -> value).toArray();
    }

    /**
     * TreeNode 에 대한 bfs 순회
     *
     * @param root TreeNode 의 root
     * @return 순회 순서 배열
     */
    public static int[] treeNode_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            // 왼쪽부터 오른쪽 depth를 쓸고 지나간다.
            // 오른쪽->왼쪽이 필요하면 if 순서를 바꾼다.
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return result.stream().mapToInt(value -> value).toArray();
    }

    /**
     * 음료수 얼려먹기의 N x M 맵 dfs 문제
     *
     * @param map 지도, 0이 음료수 얼음을 얼릴수 있고, 1이 얼릴수 없음
     * @return 음료수 얼음 갯수
     */
    public int n_m_dfs(int[][] map) {
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (n_m_core_dfs(map, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * n_m_dfs 메소드의 recursive dfs 를 위한 호출,
     * 해당 좌표의 map을 1로 만들고 동서남북으로 recursive 해 1로 만들고 한덩어리 true 를 return 함
     *
     * @param map NxM 지도
     * @param x   가로
     * @param y   세로
     * @return 만들었는지 여부
     */
    public boolean n_m_core_dfs(int[][] map, int x, int y) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return false;
        }
        if (map[x][y] == 0) {
            map[x][y] = 1;
            n_m_core_dfs(map, x + 1, y); //동
            n_m_core_dfs(map, x - 1, y); //서
            n_m_core_dfs(map, x, y - 1); //남
            n_m_core_dfs(map, x, y + 1); //북
            return true;
        }
        return false;
    }

    /**
     * 미로찾기, 미로찾기/길찾기 문제는 bfs로 해결
     * @param map 맵지도, 1이 길, 0이 벽
     * @return 최단거리 이동횟수
     */
    public int miro_bfs(int[][] map) {
        List<Integer> findList = new ArrayList<>();
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0,0, 1));

        int arriveX = map.length - 1;
        int arriveY = map[0].length - 1;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            map[current.x][current.y] = 2; // 1이 길이고, 0이 벽이므로, 2는 지나온 걸로 마킹

            // 동서남북 조회해서 길이면 순회할 수 있도록 queue에 삽입
            miro_core_bfs(map, current.x-1, current.y, current.moveCnt, queue);
            miro_core_bfs(map, current.x+1, current.y, current.moveCnt, queue);
            miro_core_bfs(map, current.x, current.y-1, current.moveCnt, queue);
            miro_core_bfs(map, current.x, current.y+1, current.moveCnt, queue);

            // 맵의 마지막 좌표 (도착지점)에 먼저 들어오면 찾은 길 리스트에 넣어줌
            if(current.x == arriveX && current.y == arriveY) {
                findList.add(current.moveCnt);
            }
        }
        Collections.sort(findList);
        // 정렬 후 가장 적은 moveCnt 는 최단거리, findList.size 는 찾은 길의 리스트
        log.info("Road Count : {}", findList.size());
        return findList.size() > 0 ? findList.get(0) : -1;
    }

    /**
     * recursive 할 메소드, 특정 좌표가 길인지 여부를 판단해서 queue에 삽입
     * @param map 마킹할 지도
     * @param x 움직일 좌표 x
     * @param y 움직일 좌표 y
     * @param moveCnt 직전 움직인 횟수, 길을 찾았다면 직전 움직인 횟수 + 1을 해서 큐에 넣는다.
     * @param queue 순회 할 큐
     */
    public void miro_core_bfs(int[][] map, int x, int y, int moveCnt, Queue<Coordinate> queue) {
        if(x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return;
        }
        if(map[x][y] == 1) {
            queue.add(new Coordinate(x, y, ++moveCnt));
        }
    }


    @Test
    @DisplayName("TreeNode dfs, bfs 순회")
    public void test() {
        TreeNode treeNode = get();

        System.out.print("dfs : ");
        int[] result = treeNode_dfs(treeNode);
        for (int value : result) {
            System.out.print(value + " -> ");
        }

        System.out.println();

        System.out.print("bfs : ");
        result = treeNode_bfs(treeNode);
        for (int value : result) {
            System.out.print(value + " -> ");
        }
    }

    @Test
    @DisplayName("NxM 맵 경우의 수 찾기 (음료수 얼려먹기)")
    public void test2() {
        int[][] map = {
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };
        int result = n_m_dfs(map);
        log.info("result : {}", result);
    }

    @Test
    @DisplayName("미로 길찾기, 1이 길 0이 벽")
    public void test3() {
        int[][] map = {
            {1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1}
        };
        int result = miro_bfs(map);
        log.info("result : {}", result);
    }
}