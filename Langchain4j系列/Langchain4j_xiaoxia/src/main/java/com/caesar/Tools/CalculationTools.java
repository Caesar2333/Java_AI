package com.caesar.Tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */

@Component
public class CalculationTools {


    @Tool("将两个数相加")
    public double sum(@P("第一个相加的数")double a, @P("第二个相加的数")double b, @ToolMemoryId int memoryId)
    {
        System.out.println("用户的id是：" + memoryId);
        System.out.println("调用加法运算");
        return a+b;
    }

    @Tool("对给定的数进行平方根运算")
    public double squareRoot(double x,@ToolMemoryId int memoryId)
    {
        System.out.println("用户的id是：" + memoryId);
        System.out.println("调用平方根运算");
        return Math.sqrt(x);
    }



}
