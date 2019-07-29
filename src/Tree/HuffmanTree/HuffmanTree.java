package Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = creatHuffTree(arr);
        perOrder(root);

    }

    public static void perOrder(Node root) {
        if (root != null)
            root.preorder();
        else
            System.out.println("空树");
    }

    /**
     * @param arr 需要创建成哈夫曼树的数组
     * @return
     */
    //创建哈夫曼树的方法
    public static Node creatHuffTree(int[] arr) {
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到Arraylist中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            // System.out.println(nodes);
            //取出根节点带权值最小的两颗二叉树
            //(1)取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //(2)取出第二小的节点（二叉树）
            Node rightNode = nodes.get(1);
            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4)从ArrList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的root节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }

    //前序遍历的方法
    public void preorder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preorder();
        if (this.right != null)
            this.right.preorder();
    }
}