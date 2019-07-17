package LinkedListDemo;

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(heroNode1);
        doubleLinkList.add(heroNode2);
        doubleLinkList.add(heroNode3);
        doubleLinkList.add(heroNode4);
        doubleLinkList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkList.update(newHeroNode);
        doubleLinkList.list();
        //删除
        doubleLinkList.delete(3);
        doubleLinkList.list();
    }
}

class DoubleLinkList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    //删除节点
    //对于双向链表，可以直接找到要删除的节点
    //
    public void delete(int no) {
        //
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false; //表示是否找到待删除节点
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break; //已经遍历完
            }
            if (temp.no == no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话了
            if (temp.next != null)
                temp.next.pre = temp.pre;
        } else {//没有找到
            System.out.printf("没有找打编号 %d 的节点，不能删除\n", no);
        }
    }

    //修改节点的信息，根据no编号来修改，no编号不能改
    //1.根据no修改
    //同单向链表一样
    public void update(HeroNode2 newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;

    }

    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量
        HeroNode2 temp = head.next;
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
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为null
    public HeroNode2 pre;  //指向上一个节点，默认为null

    //构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
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