package BinarySortTree;


public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySort binarySort = new BinarySort();
        for (int i = 0; i < arr.length; i++) {
            binarySort.add(new Node(arr[i]));
        }
        binarySort.FixOrder();
        //测试删除叶子节点
        binarySort.delNode(2);
        binarySort.delNode(5);
        binarySort.delNode(9);
        binarySort.delNode(12);
        binarySort.delNode(7);
        binarySort.delNode(3);
        binarySort.delNode(10);
        System.out.println("删除节点后");
        binarySort.FixOrder();
    }

}

class BinarySort {
    private Node root;

    //查找要删除的节点
    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    //查找父节点
    public Node SearchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }
    //编写方法
    //1.返回以node为根节点的二叉排序树的最小节点的值
    //2.删除node为根节点的二叉排序树的最小节点的值

    /**
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null)
            return;
        else {
            //1.先找到要删除的节点
            Node targetnode = search(value);
            //如果没有找到要删除的节点
            if (targetnode == null)
                return;
            //如果当前这个二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetnode的父节点
            Node parent = SearchParent(value);
            //如果要删除的节点时叶子节点
            if (targetnode.left == null && targetnode.right == null) {
                //判断targetnode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetnode.left != null && targetnode.right != null) {
                int minval = delRightTreeMin(targetnode.right);
                targetnode.value = minval;

            } else {//删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetnode.left != null) {
                    if (parent != null) {
                        //如果targetnode是parent的左子节点
                        if (parent.left.value == value)
                            parent.left = targetnode.left;
                        else //如果targetnode是parent的右子节点
                            parent.right = targetnode.left;
                    } else {
                        root = targetnode.left;
                    }
                } else {//如果要删除的节点有右子节点
                    if (parent != null) {
                        //如果targetnode是parent的左子节点
                        if (parent.left.value == value)
                            parent.left = targetnode.right;
                        else //如果targetnode是parent的右子节点
                            parent.right = targetnode.right;
                    } else {
                        root = targetnode.right;
                    }
                }
            }
        }
    }

    //添加节点方法
    public void add(Node node) {
        if (root == null) {//如果root为空，则直接指向node
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void FixOrder() {
        if (root != null)
            root.fixorder();
        else
            System.out.println("二叉排序树为空，不能遍历");
    }
}

class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方法
    //递归的形式添加，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null)
            return;
        //判断传入节点的值和当前子树的根节点值的关系
        if (node.value < this.value) {
            //如果当前节点的左子节点为空
            if (this.left == null)
                this.left = node;
            else {//递归的左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }
    }

    //中序遍历
    public void fixorder() {
        if (this.left != null)
            this.left.fixorder();
        System.out.println(this);
        if (this.right != null)
            this.right.fixorder();
    }

    //查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) { //如果查找的值小于当前节点，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null)
                return null;
        }
        return this.right.search(value);
    }
    //查找要删除节点的父节点

    /**
     * @param value 要找到的节点的值
     * @return 返回要删除节点的父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null)
                return this.right.searchParent(value);
            else {
                return null;
            }
        }
    }
}