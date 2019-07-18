package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//（逆波兰表达式）后缀表达式编写计算器
public class PolandNotation {
    public static void main(String[] args) {
        //1.完成将以一个中缀表达式转换为后缀表达式
        //2.因为直接对str进行操作不方便，所以先将str的中缀表达式存入list
        //3.将得到的中缀表达式list 转换为后缀表达式对应的list
        String expression = "1+((2+3)*40)-5";
        List<String> infixExpression = toInfixExpressionList(expression); //转为了list
        System.out.println("中缀表达式对应的list" + infixExpression);
        List<String> parse = parseSuffixExpressionList(infixExpression);
        System.out.println("后缀表达式对应的list" + parse);

        System.out.println("计算结果是" + calculate(parse));

        //先定义一个后缀表达式(30+4)*5-6-> 30 4 + 5 × 6 -
        //4 * 5-8 +60 +8 /2 -> 4 5 * 8 - 60 + 8 2 / +
        //为了方便，数字和符号用空格隔开
        /*String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将表达式放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈，完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("后缀表达式是:"+rpnList);
        int res = calculate(rpnList);
        System.out.println("计算结果是"+res);*/
    }

    //中缀表达式对应的list转换为后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //因为s2在整个过程中没有pop，而且后面还需要逆序输出,因此直接用list存储
        List<String> s2 = new ArrayList<>();
        //遍历ls
        for (String item : ls) {
            //如果是一个数，入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!!将（ 弹出s1栈,消除小括号
            } else {
                //item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getvalue(s1.peek()) >= Operation.getvalue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀表达式转为对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式的内容
        List<String> ls = new ArrayList<>();
        int index = 0;//这是一个指针，用于遍历中缀字符串
        String str; //多位数的拼接用
        char c;//每遍历一个字符，就放入c
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c = s.charAt(index)) < 48 || (c = s.charAt(index)) > 57) {
                ls.add("" + c);
                index++; //指针后移
            } else { //如果是一个数，需要考虑多位数
                str = "";//先将str置空
                while (index < s.length() && (c = s.charAt(index)) >= 48 && (c = s.charAt(index)) <= 57) {
                    str += c;//拼接
                    index++;
                }
                ls.add(str);

            }
        } while (index < s.length());
        return ls;
    }

    //将一个后缀表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成后缀表达式的运算
    /*
    1.从左至右扫描，将3和4压入堆栈；
    2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
    3.将5入栈；
    4.接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
    5.将6入栈；
    6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    * */
    public static int calculate(List<String> ls) {
        //创建栈,只需要一个
        Stack<String> stack = new Stack<>();
        //遍历List
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//正则表达式，匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("×") || item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else
                    throw new RuntimeException("符号有问题");
                stack.push(res + "");
            }


        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getvalue(String operstion) {
        int res = 0;
        switch (operstion) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "×":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }

}