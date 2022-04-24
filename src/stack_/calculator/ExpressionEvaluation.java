package stack_.calculator;

/**
 * @description: 利用栈进行表达式求值
 * @author: WuW
 * @create: 2022/1/2 14:13
 */
public class ExpressionEvaluation {


    public static ArrayStack operatorStack;
    public static ArrayStack numberStack;

    public ExpressionEvaluation(int maxsize) {
        operatorStack = new ArrayStack(maxsize);
        numberStack = new ArrayStack(maxsize);
    }


    public int fun(String expression) {
        int index = 0;
        String keepNum = "";
        while (index < expression.length()) {
            String c = expression.substring(index, index + 1);
            try {
                if (isOperator(c)) {
                    if (!operatorStack.isEmpty()) {
                        String top = operatorStack.getTop();
                        if (getPriority(c) <= getPriority(top)) {
                            String n2 = numberStack.pop();
                            String n1 = numberStack.pop();
                            String pop = operatorStack.pop();
                            String res = calculate(Integer.parseInt(n1), Integer.parseInt(n2), pop);
                            numberStack.push(res);
                        }
                    }
                    operatorStack.push(c);
                } else {
                    // 数字
                    keepNum += c;
                    if (index == expression.length() - 1) {
                        // 判断是否是最后一个字符
                        numberStack.push(keepNum);
                    }else {
                        if (isOperator(String.valueOf(expression.substring(index + 1, index + 2).charAt(0)))) {
                            // 下一个字符是符号的话才入栈，并清空拼接字符串
                            numberStack.push(keepNum);
                            keepNum = "";
                        }
                    }
                    // 如果使用char的话，要将 c - 48 入栈
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            index++;
        }
        while (!operatorStack.isEmpty()) {
            String n2 = numberStack.pop();
            String n1 = numberStack.pop();
            String pop = operatorStack.pop();
            String res = calculate(Integer.parseInt(n1), Integer.parseInt(n2), pop);
            numberStack.push(res);
        }
        return Integer.parseInt(numberStack.pop());
    }


    /**
     * 获取符号的优先级
     *
     * @return
     */
    public int getPriority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 1;
        } else if (operator.equals("+") || operator.equals("-")) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 判断字符是否是操作符
     * @param c
     * @return
     */
    public boolean isOperator(String c) {
        return c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-");
    }



    /**
     * 计算
     *
     * @param number1
     * @param number2
     * @param operator
     * @return
     */
    public String calculate(int number1, int number2, String operator) {
        int res;
        switch (operator) {
            case "*":
                res = number1 * number2;
                break;
            case "/":
                res = number1 / number2;
                break;
            case "+":
                res = number1 + number2;
                break;
            case "-":
                res = number1 - number2;
                break;
            default:
                throw new RuntimeException("暂时不提供这种运算哦！");
        }
        return String.valueOf(res);
    }


}
