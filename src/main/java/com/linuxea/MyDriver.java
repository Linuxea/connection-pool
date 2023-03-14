package com.linuxea;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDriver implements Driver {

    private final ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public Connection connect(String url) {
        Object newProxyInstance = Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionInvocationHandler(new MyConnection()));
        return (Connection) newProxyInstance;
    }

    private class ConnectionInvocationHandler implements InvocationHandler {

        private final Connection connection;

        public ConnectionInvocationHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("close")) {
                connectionPool.put(this.connection);
                System.out.println("return pool");
                return null;
            }

            return method.invoke(connection, args);
        }
    }
}
