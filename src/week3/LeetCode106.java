package week3;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class LeetCode106 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    Map<Integer, Integer> indexForInOrder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 存储中序数组元素与索引关系，区分左右字数
        indexForInOrder = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexForInOrder.put(inorder[i], i);
        }
        return find(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode find(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        // 数组左边大于右边，则退出递归
        if (inLeft > inRight) {
            return null;
        }
        // 后续遍历，最后一个元素即为根节点，[【9】,【15,7,20】,【3】]
        int rootNum = postorder[postRight];
        TreeNode root = new TreeNode(rootNum);
        // 中序遍历 inorder =[【9】,3,【15,20,7】]
        int indexForRoot = indexForInOrder.get(rootNum);
        // 左子树个数
        int leftSize = indexForRoot - inLeft;
        // 数组从0开始，postLeft+leftSize 需要 -1
        root.left = find(inorder, inLeft, indexForRoot - 1, postorder, postLeft, postLeft + leftSize - 1);
        root.right = find(inorder, indexForRoot + 1, inRight, postorder, postLeft + leftSize, postRight - 1);
        return root;
    }
}
