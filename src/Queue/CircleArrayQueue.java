package Queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列");
        //创建一个队列
        CircleArray arrayQueue = new CircleArray(4); //设置4，其有效数据最大是3
        char key=' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        e.getMessage();
                    }
                    break;
                case 'h'://查看队列
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

    static class CircleArray{
        private int maxSize; //数组的最大容量
        private int front;//队列头
        private int rear;//队列尾
        private int [] arr;//该数据用于存放数据，模拟队列
        public CircleArray(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[arrMaxSize];
        }
        public boolean isFull(){
            return (rear+1)%maxSize == front;
        }
        //判断队列是否为空
        public boolean isEmpty(){
            return rear == front;
        }
        //添加数据到队列
        public void addQueue(int n){
            //判断队列是否满
            if(isFull()){
                System.out.println("队列满，不能加入数据");
                return;
            }
            //直接将数据加入
            arr[rear]=n;
            //将rear后移，这里必须考虑取模
            rear = (rear+1)%maxSize;
        }
        //获取队列的数据，数据出队列
        public int getQueue(){
            //判断队列是否空
            if(isEmpty()){
                //通过抛出异常来处理
                throw new RuntimeException("队列空，不能取数据"); //throw本身会return 所以不需要再写return
            }
            //这里需要分析出front是指向队列的第一个元素
            //1.先把front对应的值保存到一个临时变量
            //2.将front后移,考虑去摸
            //3.将临时保存的变量返回
            int value = arr[front];
            front = (front+1)%maxSize;
            return value;
        }
        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if(isEmpty()){
                System.out.println("队列空，没有数据....");
                return;
            }
            //思路：从front开始遍历，遍历多少个元素
            for (int i = front; i < front+size(); i++) {
                System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
            }
        }
        //求出当前队列的有效数据个数
        public int size(){
            return (rear+maxSize-front)%maxSize;
        }
        //显示队列的头数据,注意不是取出数据
        public int headQueue(){
            if(isEmpty()){
                throw new RuntimeException("队列空，没有数据");
            }
            return arr[front];
        }
    }
}
