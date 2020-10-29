package com.copower.aviator;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RunScriptExample {

    protected final String path = "D:\\data\\";


    @Test
    public void testA() throws Exception {
        // Compile the script into a Expression instance.
//        Expression exp = AviatorEvaluator.getInstance().compileScript(path + "hello.av");
        Expression exp = AviatorEvaluator.getInstance().compile("## parcelStandard  可分割、适中 较小\n" +
                "\n" +
                "## 判断null值情况\n" +
                "if(parcelStandard == nil ){\n" +
                "    return '' ;\n" +
                "}\n" +
                "\n" +
                "let m = seq.map();\n" +
                "seq.add(m, '可分割' , '面积较大可分期利用；');\n" +
                "seq.add(m, '适中' , '土地面积适中可一次利用；');\n" +
                "seq.add(m, '较小' , '土地面积较小，开发利用经济价值有限；');\n" +
                "\n" +
                "if( include(m,parcelStandard) ) {\n" +
                "    return seq.get(m,parcelStandard) ;\n" +
                "}\n" +
                "\n" +
                "## 下面这种方法也可以 , 不用去注释它 因为在上面已经中断函数返回数据了\n" +
                "for x in m {\n" +
                "    if(parcelStandard ==  x.key){\n" +
                "        return x.value ;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "return '' ;");
        // Run the exprssion.
//        exp.execute();
        Map<String, Object> stringObjectMap = new HashMap<>();
       stringObjectMap.put("parcelStandard", "可分割");
        String string = JSONObject.toJSONString(stringObjectMap);
        Object execute = exp.execute(stringObjectMap);
        System.out.println(string.toString());
        System.out.println(execute.toString());
    }

    @Test
    public void testB() throws Exception {
        // Compile a script
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();
    }

    /**
     * 传入参数和值
     * @throws Exception
     */
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

    /**
     * tuple 创建数组  有点类似于object类型数组，意思是用这个可以创建任何类型数组
     * @throws Exception
     */
    @Test
    public void testE() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("for i in range(0, 20) {\n" +
                "  println(i);\n" +
                "}");

        stringBuilder.append("let arr = tuple(2,5);");
        stringBuilder.append("println(arr);");
        stringBuilder.append("for n in arr {\n" +
                "  println(n);\n" +
                "}");
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
        System.out.println(execute);
    }

    /**
     * 好像这个表达式函数 没有类似fori 的形式 用这个range可以模拟这个形式,最后一个参数相当于迭代步长
     * @throws Exception
     */
    @Test
    public void testRange() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("for i in range(0, 1020,3) {\n" +
                "  println(i);\n" +
                "}");
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
        System.out.println(execute);
    }

    /**
     * 变量作用域问题
     * @throws Exception
     */
    @Test
    public void testVariable() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let c = 2;");
        stringBuilder.append("a = 2;");
        stringBuilder.append("c = 3;");
        stringBuilder.append("println(a) ;");
        stringBuilder.append("let a = 3;");
        stringBuilder.append("println(a) ;");
        stringBuilder.append("println(c) ;");
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }

    /**
     * null的用法
     * @throws Exception
     */
    @Test
    public void testNull() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let b = nil;");
        stringBuilder.append("if(b == nil){");
        stringBuilder.append("println('hei') ;");
        stringBuilder.append("}");
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
        System.out.println(execute);
    }

    /**
     * 类似于java lambda 以及lambda实现的匿名函数 这里lambda有两种写法
     * @throws Exception
     */
    @Test
    public void testLambda() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("let f = ( lambda(x,y) -> x+ y end )(2,4);");
        stringBuilder.append("println('f:'+f) ;");
        stringBuilder.append(" add = (lambda(a,b) -> a + b end) ;")  ;
        stringBuilder.append("mul = (lambda (a,b,c) -> a*b*c end);") ;
        stringBuilder.append("println('add:'+add(4,6));") ;
        stringBuilder.append("println('mul:'+mul(2,3,5));") ;

        stringBuilder.append("fn square(x){x*2} ") ;
        stringBuilder.append("add_n  = (lambda(a,b,f) -> f(a) +f(b) end) ;") ;
        stringBuilder.append("println('add_n:' +  add_n(4,5,square) ) ;") ;

        stringBuilder.append("                println(  'lambda简写:' + lambda(g,h) -> g+h end(5,7)   )    ;                ") ;

        AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }

    /**
     * 闭包环境  需要小心
     * @throws Exception
     */
    @Test
    public void testClosure()throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        String fn1 = "lambda() -> let result = c; c = c+1; return result ; end" ;
        stringBuilder.append(String.format("let counter  = lambda() -> let c = 0; %s end   ;" ,fn1)) ;
        stringBuilder.append("let c1 = counter();") ;
        stringBuilder.append("for n in range(1,10){  v = c1();  println( v ) ;  } ;") ;
        System.out.println(stringBuilder.toString());
        AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }

    /**
     * 集合运用
     * @throws Exception
     */
    @Test
    public void testF() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        //创建集合
        stringBuilder.append("let a1 = seq.list();");
        stringBuilder.append("let a2 = seq.set();");
        stringBuilder.append("let a3 = seq.map();");
        stringBuilder.append("let arr = range(1,5);");
        //集合 添加元素
        stringBuilder.append("for n in arr {\n" +
                " seq.add(a1,n);\n" +
                " seq.add(a2,n);\n" +
                " seq.add(a3,n,n);\n" +
                "};");
        stringBuilder.append("println(a1) ;");
        stringBuilder.append("println(a2) ;");
        stringBuilder.append("println(a3) ;");
        stringBuilder.append("println(arr) ;");


        //访问元素
        //注意的是list 是取得元素索引 ,set 也是索引,map 取得是key
        stringBuilder.append("for n in arr {\n" +
                " println(include(a1,n) +' ' +n );\n" + //include 是判断元素是否存在
                " println(seq.get(a1,n-1) );\n" +// 必须减一
                " println(seq.get(a2,n) );\n" +
                " println(seq.get(a3,n) );\n" +
                "};");

        stringBuilder.append("println('list count:' +count(a1));");
        stringBuilder.append("println('set count:' +count(a2));");
        stringBuilder.append("println('map count:' +count(a3));");

        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
        System.out.println(execute);
    }

    /**
     * 调用自定义函数
     * @throws Exception
     */
    @Test
    public void testDefaultFun()throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        //注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        stringBuilder.append(
                "println(  ' default fun add:' +add(5,6)  ) ;"
        );
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }

    /**
     * 引入java的静态方法
     * @throws Exception
     */
    @Test
    public void testStaticClassFun()throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        AviatorEvaluator.addStaticFunctions("stringUtils", StringUtils.class);
        stringBuilder.append("println(        stringUtils.contains('你好啊!' ,'你')     ) ;") ;
        Object execute = AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }


    /**
     * 引入java 实例方法
     * @throws Exception
     */
    @Test
    public void testInstanceFun()throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        AviatorEvaluator.addInstanceFunctions("string",String.class) ;
        stringBuilder.append(" println(   string.contains('hello' ,'h')  )    ;") ;
        AviatorEvaluator.compile(stringBuilder.toString()).execute();
    }

    /*
    自定义函数
     */
    class AddFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env,
                                  AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }
        public String getName() {
            return "add";
        }
    }


}
