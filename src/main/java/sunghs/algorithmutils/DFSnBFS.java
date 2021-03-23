package sunghs.algorithmutils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
     * recursive dfs 를 위한 호출,
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




    @Test
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
    public void test2() {
        int[][] map = {
            {0, 0, 1, 1, 0},
            {0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };
        int result = n_m_dfs(map);
        log.info("result {}", result);
    }
}