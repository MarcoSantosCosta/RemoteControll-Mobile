package presenter.com.app.core;


import java.io.ObjectInputStream;
import java.net.Socket;

import presenter.com.app.buffers.ActionBuffer;
import presenter.com.messages.Action;


public class ThreadReceiver extends Thread {

    private Socket cliente;

    private ActionBuffer buffer;

    public ThreadReceiver(Socket cliente) {
        this.cliente = cliente;
        this.buffer = ActionBuffer.getInstance();

    }
    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
            while (true) {
                Object aux = input.readObject();
                if (aux instanceof Action) {
                    System.out.println("RECEBI UMA AÇÃO UHULLLL");
                    Action act = (Action) aux;
                    buffer.addAction(act);
                } else {
                    System.out.println("Não reconhecido");
                }
            }
        } catch (Exception e) {
            System.out.println("Excecao ocorrida na thread: " + e);
            try {
                cliente.close();
            } catch (Exception ec) {
            }
        }
    }
}
