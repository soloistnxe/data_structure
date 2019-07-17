package Stack;
public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-6/2+4";
        //创建两个栈,数栈和符合栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到char保存到ch
        String keepnumber = "";
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.prioity(ch) <= operStack.prioity(operStack.peek())) {
                        //运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //运算结果入栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符优先级大于栈中的操作符，直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            } else { //如果是数则直接入数栈
                //1.当处理多位数时，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //2.因此需要定义一个字符串，用于拼接
                keepnumber += ch;

                //如果ch已经是表达式的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepnumber));
                }

                //判断下一个字符是不是数字，如果是数字，就继续扫面，如果是运算符则入栈
                //注意是看后一位，不是index++
                else if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    //如果后一位是运算符，则入栈 keepnumber = "123"
                    numStack.push(Integer.parseInt(keepnumber));
                    //重要！！！！keepnumber要清空
                    keepnumber = "";
                }
            }
            //让index+1
            index++;
            if (index >= expression.length())
                break;
        }
        //直接按照倒叙出栈进行运算有问题，所以新建两个栈，按照运算顺序去存放数字和运算符
        ArrayStack2 numstack1 = new ArrayStack2(10);
        ArrayStack2 operstack1 = new ArrayStack2(10);
        //将剩余数字和运算符存入到新栈中
        while (true) {
            if (operStack.isEmpty())
                break;
            oper = operStack.pop();
            operstack1.push(oper);
        }
        while (true) {
            if (numStack.isEmpty())
                break;
            num1 = numStack.pop();
            numstack1.push(num1);
        }


        //当表达式扫描完毕，就顺序的从 新的数栈和符号栈中pop出相应的数和符号，并运行.
        while (true) {
            //如果符号栈为空，则计算到最后的结果, 数栈中只有一个数字【结果】
            if (operstack1.isEmpty()) {
                break;
            }
            num2 = numstack1.pop();//这里要注意，新栈中的出栈顺序是正确的运算顺序，所以先出栈的减去后出栈的
            num1 = numstack1.pop();
            oper = operstack1.pop();
            if (oper == '-') {
                num1 = -num1;
                oper = '+';
            }
            res = numstack1.cal(num1, num2, oper);
            numstack1.push(res);//入栈
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numstack1.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }
}

//定义一个ArrayStack 表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //查看栈顶
    public int peek() {
        return stack[top];
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
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常来处理
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
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
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，数字越大，则优先级越高
    public int prioity(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前表达式只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res 存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序，后弹出的数减去先弹出的数
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
