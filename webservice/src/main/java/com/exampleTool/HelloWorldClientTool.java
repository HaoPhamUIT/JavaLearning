package com.exampleTool;

/**
 * Created by my-tran on 6/1/17.
 */
public class HelloWorldClientTool {
    public static void main(String[] args) {

        HelloWorldImpltestService helloService = new HelloWorldImpltestService();
        HelloWorld hello = helloService.getHelloWorldImpltestPort();

        System.out.println(hello.getHelloWorldAsString("hao1","abcxyz"));

    }
}
