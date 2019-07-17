package Stack;

import java.util.Scanner;

public class LinkListStackDemo {
    public static void main(String[] args) {
        //测试一下LinkListStack是否正确
        //先创建一个ArrayStack对象->表示栈
        LinkListStack stack = new LinkListStack(4);
        String key;
        boolean falg = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (falg) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈（入栈）");
            System.out.println("show: 表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    falg = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//定义一个LinkListStack 表示栈
class LinkListStack {
    private int maxSize;//栈的大小
    private LinkList Linkstack = new LinkList(); //链表，链表模拟栈，数据就放在该链表中
    private int top = -1; //top表示栈顶，初始化为-1

    public LinkListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 Push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        Node temp = new Node(top, value);
        System.out.println(temp.getId() + temp.getValue());
        Linkstack.add(temp);
    }

    //出栈
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常来处理
            throw new RuntimeException("栈空");
        }
        int value = Linkstack.LastNode();
        Linkstack.DeletLast();
        top--;
        return value;
    }

    //遍历栈,便立时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, Linkstack.PrintInId(i));
        }
    }

    /*
    创建链表的节点
    * */
    class Node {
        private int id;
        private int value;
        private Node next;

        public Node(int id, int value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    //创建链表
    class LinkList {
        private Node head = new Node(-1, -1);

        public void add(Node node) {
            //因为head节点不能动，因此需要一个辅助遍历temp
            Node temp = head;
            //遍历链表，找到最后
            while (true) {
                //找到链表的最后
                if (temp.getNext() == null) {
                    break;
                }
                //如果没有找到就将temp后移
                temp = temp.getNext();
            }
            //当退出while循环时，temp就指向了链表的最后
            //将最后这个节点的next指向新的节点
            temp.setNext(node);
        }

        //返回链表的最后一个节点
        public int LastNode() {
            Node temp = head;
            //遍历链表，找到最后
            while (true) {
                //找到链表的最后
                if (temp.getNext() == null) {
                    break;
                }
                //如果没有找到就将temp后移
                temp = temp.getNext();
            }
            //当退出while循环时，temp就指向了链表的最后
            return temp.getValue();
        }

        //删除最后一个节点
        public void DeletLast() {
            Node temp = head;
            Node next = head.getNext();
            if (head.next == null) {
                System.out.println("链表空，无法删除最后一个节点");
                return;
            }
            //遍历链表，找到最后
            while (true) {
                //找到链表的最后
                if (next.getNext() == null) {
                    break;
                }
                //如果没有找到就将temp后移
                temp = temp.getNext();
                next = next.getNext();
            }
            //当退出while循环时，temp就指向了链表最后一个节点的前一个
            //将这个节点的next指null
            temp.setNext(null);
        }

        //按照节点的Id打印节点值
        public int PrintInId(int id) {
            Node temp = head.next;
            while (true) {
                if (temp.getId() == id) {
                    return temp.getValue();
                }
                temp = temp.getNext();
            }
        }
    }
}