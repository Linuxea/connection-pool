package com.linuxea;

import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private final ArrayBlockingQueue<Connection> connections;

    public ConnectionPool() {
        int coreSize = 10;
        this.connections = new ArrayBlockingQueue<>(coreSize);
    }

    public void put(Connection connection) throws InterruptedException {
        this.connections.put(connection);
    }

    public Connection getConnection() throws InterruptedException {
        return this.connections.take();
    }

    public void destroyAll() {
        connections.forEach(Connection::close);
    }

}
