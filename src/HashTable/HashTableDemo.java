package HashTable;
//哈希表，哈希表是一个数组，每个数组中存入的是一个链表，通过hash值将元素放入到对应的链表中
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建一个hash表
        HashTable hashTable = new HashTable(7);
        //写一个菜单来测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.DeleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }
        }

    }
}


//创建哈希表
class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size; //表示有多少条链表

    //构造器
    public HashTable(int size) {
        //初始化链表
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //不要忘了分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到哪个链表
        int emplinklistno = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedLists[emplinklistno].add(emp);
    }

    //编写一个散列函数，使用取模法
    public int hashFun(int id) {
        return id % size;
    }

    //遍历hash表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].print(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        int empLinklistno = hashFun(id);
        Emp emp = empLinkedLists[empLinklistno].findbyid(id);
        if (emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinklistno + 1), id);

        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //根据输入id删除雇员
    public void DeleteById(int id) {
        int empLinklistno = hashFun(id);
        empLinkedLists[empLinklistno].delete(id);
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建链表
class EmpLinkedList {
    //头指针，指向第一个Emp，因此这个链表的head就是第一个雇员
    private Emp head; //默认为空

    //添加员工到链表
    //1.假定添加雇员就是加在链表的背后，id是自增长的，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是添加第一个雇员,则使用一个辅助指针
        Emp cur = head;
        while (true) {
            if (cur.next == null) {//说明到了链表的最后
                break;
            }
            cur = cur.next; //后移
        }
        cur.next = emp;

    }

    //遍历链表信息
    public void print(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的信息为");
        Emp cur = head;
        while (cur != null) {
            System.out.printf("->id=%d name=%s\t", cur.id, cur.name);
            cur = cur.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findbyid(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表空");
            return null;
        }
        Emp cur = head;
        while (true) {
            if (cur.id == id) { //找到
                break;
            }
            if (cur.next == null) {//说明遍历完了没找到
                cur = null;
                break;
            }
            cur = cur.next;
        }
        return cur;
    }

    //根据id删除雇员
    public void delete(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表空");
        }
        Emp cur = head;
        if (head.id == id) { //如果头结点就是要删除的节点
            head = head.next;
        } else {
            while (true) {
                if (cur.next.id == id) { //找到了待删除的节点
                    if (cur.next.next != null)
                        cur.next = cur.next.next;
                    else
                        cur.next = null;
                    break;
                }
                cur = cur.next;
            }
        }
    }
}
