package presenter.com.app.core;


import java.io.IOException;
import java.net.Socket;

import presenter.com.app.buffers.ActionBuffer;

public class Client {

    private int port;
    private Socket cliente;
    private String ip;
    private ActionBuffer buffer;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.buffer = ActionBuffer.getInstance();
    }

    public void start() {
        try {
            this.cliente = new Socket(this.ip, this.port);
            System.out.println("O cliente se conectou ao servidor!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getCliente() {
        return cliente;
    }
}
