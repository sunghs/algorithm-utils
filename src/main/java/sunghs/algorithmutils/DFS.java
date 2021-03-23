package sunghs.algorithmutils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class DFS {

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
     * @param root TreeNode 의 root
     * @return 순회 순서 배열
     */
    public static int[] treeNode_dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        stack.push(root);

        while(!stack.isEmpty()) {
            // current에 왔으므로 마킹
            TreeNode current = stack.pop();
            result.add(current.val);

            // left의 if가 더 빠르므로, stack 의 아랫쪽에 쌓인다. (root -> left -> left -> left -> right -> right .. 로 쌓이게 될텐데 stack은 꺼내면 right 부터 나오게 되므로)
            // 따라서 아래 코드는 right부터 돌게 된다.
            // 왼쪽순회가 먼저 필요하면 if문 위치를 바꾸면 된다.
            if(current.left != null) {
                stack.push(current.left);
            }
            if(current.right != null) {
                stack.push(current.right);
            }
        }
        return result.stream().mapToInt(value -> value).toArray();
    }

    /**
     * TreeNode 에 대한 bfs 순회
     * @param root TreeNode 의 root
     * @return 순회 순서 배열
     */
    public static int[] treeNode_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            // 왼쪽부터 오른쪽 depth를 쓸고 지나간다.
            // 오른쪽->왼쪽이 필요하면 if 순서를 바꾼다.
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return result.stream().mapToInt(value -> value).toArray();
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
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}