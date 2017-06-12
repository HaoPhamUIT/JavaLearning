package com.example;

/**
 * Created by my-tran on 6/1/17.
 */
import javax.xml.ws.Endpoint;
//Endpoint publisher
public class HelloWorldPublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9092/ws/hello", new HelloWorldImpltest());
        // Endpoint.publish("http://localhost:9092/ws/hello", new HelloWorldImpl2());
        // wsimport -keep http://localhost:9092/ws/hello?wsdl  using tool wsiport

    }
}