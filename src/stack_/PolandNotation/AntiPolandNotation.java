package stack_.PolandNotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @description: 逆波兰表达式求值
 * @author: WuW
 * @create: 2022/1/3 9:17
 */
public class AntiPolandNotation {

    public static void main(String[] args) {
        String polandExpression = "( 34 + 45 ) * 2 - 23 * 2 + 34";
        String antiPolandExpression = InfixToPostfix.invert(polandExpression);
        try {
            int cal = cal(antiPolandExpression);
            System.out.println(cal);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * 逆波兰计算
     *
     * @param polandExpression
     * @return
     */
    public static int cal(String polandExpression) {
        String[] strings = polandExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, strings);
        Stack<String> stack = new Stack<>();
        for (String str : list) {
            int res;
            if (str.matches("\\d+")) {
                // 匹配多位数
                stack.push(str);
            } else if (str.equals("*")) {
                String pop2 = stack.pop();
                String pop1 = stack.pop();
                res = Integer.parseInt(pop1) * Integer.parseInt(pop2);
                stack.push(res + "");
            } else if (str.equals("/")) {
                String pop2 = stack.pop();
                String pop1 = stack.pop();
                res = Integer.parseInt(pop1) / Integer.parseInt(pop2);
                stack.push(res + "");
            } else if (str.equals("+")) {
                String pop2 = stack.pop();
                String pop1 = stack.pop();
                res = Integer.parseInt(pop1) + Integer.parseInt(pop2);
                stack.push(res + "");
            } else if (str.equals("-")) {
                String pop2 = stack.pop();
                String pop1 = stack.pop();
                res = Integer.parseInt(pop1) - Integer.parseInt(pop2);
                stack.push(res + "");
            } else {
                throw new RuntimeException("表达式有误！");
            }
        }
        return Integer.parseInt(stack.pop());
    }


}
