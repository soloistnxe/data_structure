package LinkedListDemo;

import java.util.Stack;

//为链表添加节点时一个节点的添加只能使用一次，因为每添加一个节点之后，该节点的next就会存储进下一个节点的信息
//若再次添加已经添加过的节点，相当于添加了给节点为头结点的剩余链表，很可能造成死循环的现象
//如果想要添加两个相同信息的节点，需要创建两个节点，这两个节点的信息一致即可
//即可以创建两个信息一样的节点分别添加，但是同一个节点不可以添加两次
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试

        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "宋江", "及时雨");
        HeroNode heroNode6 = new HeroNode(6, "卢俊义", "玉麒麟");
        HeroNode heroNode7 = new HeroNode(7, "吴用", "智多星");
        HeroNode heroNode8 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //测试合并链表
        SingleLinkedList newsingleLinkList = new SingleLinkedList();


        /*singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);*/


        singleLinkedList1.add(heroNode1);
        singleLinkedList1.add(heroNode3);
        singleLinkedList1.add(heroNode4);
        singleLinkedList1.add(heroNode8);

        singleLinkedList2.add(heroNode2);
        singleLinkedList2.add(heroNode5);
        singleLinkedList2.add(heroNode6);
        singleLinkedList2.add(heroNode7);

        //singleLinkedList1.list();
        System.out.println("____________________");
        //singleLinkedList2.list();
        newsingleLinkList = MergeList(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        newsingleLinkList.list();
        //singleLinkedList2.list();
        //测试逆序打印单链表
       /* System.out.println("测试逆序打印单链表");
        recersePrint(singleLinkedList1.getHead());
        //测试一下单链表的反转功能
        System.out.println("反转单链表");
        reversetList(singleLinkedList1.getHead());
        singleLinkedList1.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小卢","小玉");

        singleLinkedList1.update(newHeroNode);
        System.out.println("修改后的链表");

        singleLinkedList1.list();
        //测试一下求有效节点个数
        System.out.println("有效的节点个数为"+getLength(singleLinkedList1.getHead()));
        //测试一下是否得到倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList1.getHead(),3);
        System.out.println("res="+res);*/
    }

    //实现两个有序链表的合并
    public static SingleLinkedList MergeList(HeroNode head1, HeroNode head2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();//新建一个链表
        if (head1.next == null && head2.next == null) { //若需要合并的两个链表都为空则直接返回空
            return null;
        } else if (head1 == null) {  //若其中一个链表为空则直接返回另一个链表
            singleLinkedList.getHead().next = head2.next;
            return singleLinkedList;
        } else if (head2 == null) {
            singleLinkedList.getHead().next = head1.next;
            return singleLinkedList;
        }
        HeroNode cur1 = head1.next; //辅助变量，用于操作链表1
        HeroNode cur2 = head2.next; //用于操作链表2
        HeroNode newList = new HeroNode(0, "", "");
        HeroNode temp = newList; //头结点不能动，用辅助变量来向链表增加节点
        while (cur1 != null && cur2 != null) { //两个链表同时遍历，将no小的节点，增加到新链表的尾部，更新两个链表的节点位置
            if (cur1.no <= cur2.no) {
                temp.next = cur1;
                cur1 = cur1.next;
            } else {
                temp.next = cur2;
                cur2 = cur2.next;
            }
            temp = temp.next;
        }
        while (cur1 != null) { //将没有遍历完的那个链表的剩余节点直接加入到新链表的尾部
            temp.next = cur1;
            cur1 = cur1.next;
            temp = temp.next;
            //System.out.println("a");
        }
        while (cur2 != null) {
            temp.next = cur2;
            cur2 = cur2.next;
            temp = temp.next;
            //System.out.println("a");
        }
        singleLinkedList.getHead().next = newList.next;
        return singleLinkedList;
    }

    //使用栈实现逆序打印
    public static void recersePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;   //cur后移
        }
        //将栈中的节点打印。pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    //将单链表进行反转
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助节点，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表reverseHead的最前
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next; //cur后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中倒数第k个节点【新浪面试题】
    //思路
    //1.编写一个方法，接受head节点，同时接受一个index
    //2.index 表示是倒数第index节点
    //3.先把链表从头到尾遍历，得到链表的总的长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    //5.如果找到了，则返回该节点，找不到返回空
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历size-index 位置，就是我们倒数的第k个节点
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //方法：获取到单链表的节点个数（如果是带头结点的链表，需要不统计头结点）
    /*head 链表的头结点
     返回的就是有效节点的个数
    */

    public static int getLength(HeroNode head) {
        if (head.next == null) {//链表空
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {
    //初始化一个头结点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;

    }

    //第二种添加英雄的方式，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们仍通过一个辅助变量来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的node编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d 已经存在\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移,一定小心
            temp = temp.next;
        }
    }

    //修改节点的信息，根据no编号来修改，no编号不能改
    //1.根据no修改
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找打编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    //1.head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
    //2.在比较时是temp.next.no 和待删除节点的no比较
    public void delete(HeroNode heroNode) {
        //
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false; //表示是否找到待删除节点的前一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break; //已经遍历完
            }
            if (temp.next.no == heroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = heroNode.next;
        } else {//没有找到
            System.out.printf("没有找打编号 %d 的节点，不能删除\n", heroNode.no);
        }
    }
}

//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}