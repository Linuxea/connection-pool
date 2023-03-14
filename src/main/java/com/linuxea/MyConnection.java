package com.linuxea;

public class MyConnection implements Connection {


    @Override
    public void close() {
        System.out.println("destroy connection");
    }
}
