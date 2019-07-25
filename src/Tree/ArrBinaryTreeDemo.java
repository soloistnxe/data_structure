package Tree;

/*顺序二叉树通常只考虑完全二叉树
第n个元素的左子节点为  2 * n + 1
第n个元素的右子节点为  2 * n + 2
第n个元素的父节点为  (n-1) / 2

* */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postorder();
    }
}

class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preorder() {
        this.preorder(0);
    }

    //编写一个方法完成顺序存储二叉树的一个前序遍历
    //index表示数组的下标
    public void preorder(int index) {
        //如果数组为空,或者arr.length =0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length)
            preorder(2 * index + 1);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preorder(index * 2 + 2);
        }
    }

    //重载
    public void fixorder() {
        this.fixorder(0);
    }

    //编写一个方法完成顺序存储二叉树的一个中序遍历
    // index表示数组的下标
    public void fixorder(int index) {
        //如果数组为空,或者arr.length =0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length)
            fixorder(2 * index + 1);
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            fixorder(index * 2 + 2);
        }
    }

    //重载
    public void postorder() {
        this.postorder(0);
    }

    //编写一个方法完成顺序存储二叉树的一个中序遍历
    // index表示数组的下标
    public void postorder(int index) {
        //如果数组为空,或者arr.length =0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length)
            postorder(2 * index + 1);

        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postorder(index * 2 + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}