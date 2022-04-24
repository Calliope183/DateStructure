package stack_.PolandNotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @description: 中缀表达式转后缀表达式
 * @author: WuW
 * @create: 2022/1/3 9:39
 */
public class InfixToPostfix {



    public static void main(String[] args) {
        String infix = "5 * ( 20 + 6 ) - 3";
        String invert = invert(infix);
        System.out.println(invert);
    }

    
    /**
     * 实现步骤：
     *      1. 初始化两个栈：符号栈和存放中间结果的临时栈
     *      2. 从左至右扫描传进去的前缀表达式
     *      3. 如果是数字直接入符号栈
     *      4. 如果是运算符：
     *          （1）符号栈为空的时候直接入符号栈
     *          （2）符号栈不为空时：
     *              若当前运算符的优先级小于等于栈顶运算符的优先级，将栈顶运算符压入临时栈，回到4（1）继续判断；
     *              否则直接入符号栈
     *      5. 如果是括号：
     *          （1）若是"("，直接入栈
     *          （2）若是")"，将符号栈中第一个(上的符号（包含"("）压入临时栈，再将当前符号入栈
     *      6. 如果不是上述情况，抛异常
     *      7. 将符号栈中的所有符号依次压入临时栈中
     *      8. 将临时栈中的符号依次出栈得到后缀表达式的逆序
     *      9. 翻转字符串得到后缀表达式
     */
    public static String invert(String infix){
        Stack<String> opStack = new Stack<>();
        Stack<String> tempStack = new Stack<>();
        List<String> list = new ArrayList<>();
        Collections.addAll(list, infix.split(" "));
        for(String str : list){
            if(str.matches("\\d+")){
                // 是数字，直接入栈
                tempStack.push(str);
            } else if(isOperator(str)){
                // 是运算符
                if(!opStack.isEmpty()){
                    // 运算符栈不空
                    String peek = opStack.peek();
                    // 判断优先级
                    while (getPriority(str) <= getPriority(peek)){
                        // 当前运算符优先级小于栈顶运算符的优先级，则将栈顶的运算符加到临时栈中
                        tempStack.push(opStack.pop());
                        if (!opStack.isEmpty()) {
                            // 防止栈空抛异常
                            peek = opStack.peek();
                        } else {
                            break;
                        }
                    }
                }
                opStack.push(str);// 将当前运算符入符号栈
            } else if(str.equals("(")){
                // 遇到(直接入栈
                opStack.push(str);
            } else if(str.equals(")")){
                // 遇到)，将符号栈中(之上的所有符号压入临时栈
                while(!(opStack.peek()).equals("(")){
                    tempStack.push(opStack.pop());
                }
                opStack.pop();// (出栈
            } else {
                // 若有运算符、括号、操作数以外的字符直接抛异常
                throw new RuntimeException("表达式有误！");
            }
        }

        // 将运算符栈剩下的元素放进临时栈中
        while(!opStack.isEmpty()){
            tempStack.push(opStack.pop());
        }
        // 将临时栈中的元素依次出栈拼接成串，此时的拼成的串是后缀表达式的逆序
        StringBuilder stringBuilder = new StringBuilder();
        while(!tempStack.isEmpty()){
            stringBuilder.append(tempStack.pop()).append(" ");
        }

        // 将拼接好的字符串翻转得到最后的后缀表达式
        String[] resReverse = stringBuilder.toString().split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = resReverse.length - 1; i >= 0; i--) {
            res.append(resReverse[i]).append(" ");
        }

        return res.toString();
    }


    /**
     * 获取符号的优先级
     *
     * @return
     */
    public static int getPriority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 1;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 0;
        } else if (operator.equals("(")) {
            return -1;
        } else {
            return -2;
        }
    }



    /**
     * 判断字符是否是操作符
     *
     * @param c
     * @return
     */
    public static boolean isOperator(String c) {
        return c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-");
    }

}
