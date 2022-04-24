package stack_.calculator;

/**
 * @description: 表达式求值测试
 * @author: WuW
 * @create: 2022/1/2 14:17
 */
public class ExpressionEvaluationDemo {

    public static void main(String[] args) {
        String expression = "111+777*2";
        ExpressionEvaluation evaluation = new ExpressionEvaluation(expression.length());

        int fun = evaluation.fun(expression);
        System.out.println(fun);
    }
}
