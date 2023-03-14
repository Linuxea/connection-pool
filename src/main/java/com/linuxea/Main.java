package com.linuxea;

public class Main {

    public static void main(String[] args) {

        // instantiate our driver impl
        Driver myDriver = new MyDriver();
        Connection connection = myDriver.connect("some url");
        connection.close();
    }

}
