package Tree;

public class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preorder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void fixOrder() {
        if (this.root != null) {
            this.root.fixorder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postorder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public Node PreOrderSearch(int id) {
        if (root != null)
            return root.preOrdersearch(id);
        else
            return null;
    }

    //中序遍历查找
    public Node FixOrdeerSearch(int id) {
        if (root != null)
            return root.fixOrdersearch(id);
        else
            return null;
    }

    //后序遍历查找
    public Node PostOrderSearch(int id) {
        if (root != null)
            return root.postOrdersearch(id);
        else
            return null;
    }

    //删除节点
    public void DeleteNode(int id) {
        if (root != null) {
            //如果只有一个root节点，这里判断root是不是要删除的节点
            if (root.getId() == id)
                root = null;
            else
                root.delete(id);
        } else
            System.out.println("空树，不能删除");
    }

    public static void main(String[] args) {
        //现需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        Node root = new Node(1, "鸣人");
        Node node2 = new Node(2, "佐助");
        Node node3 = new Node(3, "卡卡西");
        Node node4 = new Node(4, "路飞");
        Node node5 = new Node(5, "孙悟空");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);
        /*System.out.println("前序遍历结果");
        binaryTree.preOrder();
        System.out.println("中序遍历结果");
        binaryTree.fixOrder();
        System.out.println("后序遍历结果");
        binaryTree.postOrder();

        System.out.println("前序遍历查找");
        Node node = binaryTree.PreOrderSearch(5);
        System.out.println(node);*/

        System.out.println("删除前，前序遍历的结果");
        binaryTree.preOrder();
        binaryTree.DeleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

//先创建Node 节点
class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preorder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preorder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preorder();
        }
    }

    //编写中序遍历的方法
    public void fixorder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.fixorder();
        }
        //输出父节点
        System.out.println(this);
        if (this.right != null) {
            this.right.fixorder();
        }
    }

    //编写后序遍历的方法
    public void postorder() {
        if (this.left != null) {
            this.left.postorder();
        }
        if (this.right != null) {
            this.right.postorder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public Node preOrdersearch(int id) {
        System.out.println("进入前序遍历");
        //先比较当前节点是不是
        if (this.id == id) {
            return this;
        }
        //则判断当前节点的左子节点是否为空，如果不为空，递归前序查找
        Node res = null;
        if (this.left != null) {
            res = this.left.preOrdersearch(id);
        }
        if (res != null) {//说明左子树找到了
            return res;
        }
        //判断当前节点的右子节点是否为空
        if (this.right != null) {
            res = this.right.preOrdersearch(id);
        }
        return res;
    }

    //中序遍历查找
    public Node fixOrdersearch(int id) {
        //先判断当前节点的左子节点
        Node res = null;
        if (this.left != null) {
            res = this.left.fixOrdersearch(id);
        }
        if (res != null)
            return res;
        if (this.id == id) {
            return this;
        }
        //向右中序查找
        if (this.right != null) {
            res = this.right.fixOrdersearch(id);
        }
        return res;
    }

    //后序便利查找
    public Node postOrdersearch(int id) {
        //向左递归查找
        Node res = null;
        if (this.left != null) {
            res = this.left.postOrdersearch(id);
        }
        if (res != null)
            return res;
        if (this.right != null) {
            res = this.right.postOrdersearch(id);
        }
        if (this.id == id)
            return this;
        return res;
    }

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delete(int id) {
        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delete(id);
        }
        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delete(id);
        }
    }
}