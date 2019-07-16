package Stack;

public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}
//定义一个ArrayStack 表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int [] stack; //数组，数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈 Push
    public void push(int value){
        //先判断栈是否满
        if(isFull()){
            System.out.println();
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        //先判断栈是否空
        if(isEmpty()){
            //抛出异常来处理
            throw  new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈,便立时需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for(int i =top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
