package com.example;

/**
 * Created by my-tran on 6/1/17.
 */

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "com.example.HelloWorld")
public class HelloWorldImpltest implements HelloWorld{

    @Override
    public String getHelloWorldAsString(String firstname, String lastname) {
        return "Hello World JAX-WS FirstName:" + firstname+"\nLastName:"+ lastname;
    }
    @Override
    public String getUserString(String username, String password) {
        return "Hello World JAX-WS FirstName:" + username+"\nLastName:"+ password;
    }

}