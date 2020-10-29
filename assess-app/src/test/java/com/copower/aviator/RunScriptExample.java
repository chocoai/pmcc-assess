package com.copower.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

public class RunScriptExample {

    protected final String path = "D:\\data\\";


    @Test
    public void testA() throws Exception {
        // Compile the script into a Expression instance.
        Expression exp = AviatorEvaluator.getInstance().compileScript(path + "hello.av");
        // Run the exprssion.
        exp.execute();
    }

    @Test
    public void testB() throws Exception {
        // Compile a script
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();
    }

    @Test
    public void testC() throws Exception {
        String expression = "a-(b-c) > 100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        Boolean result =
                (Boolean) compiledExp.execute(compiledExp.newEnv("a", 100.3, "b", 45, "c", -199.100));
        System.out.println(result);
    }

    @Test
    public void testC1() throws Exception {
        String expression = "a-(b-c) + 100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        Object result = compiledExp.execute(compiledExp.newEnv("a", 100.3, "b", 45, "c", -199.100));
        System.out.println(result);
    }

    @Test
    public void testD() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let a = 99;");
        stringBuilder.append("let b = 0xFF;");
        stringBuilder.append("let c = -99;");

        stringBuilder.append("println(a + b);");
        stringBuilder.append("println(a / b);");
        stringBuilder.append("println(a- b + c);");
        stringBuilder.append("println(a + b * c);");
        stringBuilder.append("println(a- (b - c));");
        stringBuilder.append("println(a/b * b + a % b)");

        Expression compiledExp = AviatorEvaluator.compile(stringBuilder.toString());
        compiledExp.execute();
    }

    @Test
    public void testD1() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let a = 2M;");
        stringBuilder.append("let err = 1e-15M;");
        stringBuilder.append("let root = a;");

        stringBuilder.append("while math.abs(a - root * root) > err {");
        stringBuilder.append("     root = (a/root + root) / 2.0M;");
        stringBuilder.append("}");
        stringBuilder.append("println( 'square root of 2M is:' + root);");

        Expression compiledExp = AviatorEvaluator.compile(stringBuilder.toString());
        compiledExp.execute();
    }

    @Test
    public void testD2() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let a = 2;");
        stringBuilder.append("let err = 1e-15;");
        stringBuilder.append("let root = a;");

        stringBuilder.append("while math.abs(a - root * root) > err {");
        stringBuilder.append("     root = (a/root + root) / 2.0;");
        stringBuilder.append("}");
        stringBuilder.append("println( 'square root of 2 is:' + root);");

        Expression compiledExp = AviatorEvaluator.compile(stringBuilder.toString());
        compiledExp.execute();
    }


    @Test
    public void testD3() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let a = 2;");

        stringBuilder.append("println( 'ais:' + a);");
        stringBuilder.append(" a = '你好中国';");
        stringBuilder.append("println( 'ais:' + a);");
        Expression compiledExp = AviatorEvaluator.compile(stringBuilder.toString());
        compiledExp.execute();
        Object execute = AviatorEvaluator.compile("let a = 1+2; return a ;").execute();
        System.out.println(execute);
    }


}
