package LinkedListDemo;

public class Josepfu {
    public static void main(String[] args) {
        //测试构建链表和遍历是否正确
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.ShowBoy();
    }
}

//创建一个环形链表
class CircleSingleLinkedList {
    //创建一个first节点,当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums) {
        //nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助创建环形链表
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    //遍历当前的环形链表
    public void ShowBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    /*
    startNo 表示从第几个小孩开始数数
    countNum 表示数几下
    nums 表示最初有多少个小孩在圈中
    * */
    public void conutBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) { //说明help指向最后节点
                break;
            }
            helper = helper.getNext();
        }
    }

}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}