package presenter.com.app.core;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import presenter.com.app.buffers.MessageBuffer;
import presenter.com.messages.Message;

public class ThreadSender extends Thread {

    private Socket client;
    private MessageBuffer buffer;

    public ThreadSender(Socket cli) {
        this.client = cli;
        this.buffer = MessageBuffer.getInstance();
    }

    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(this.client.getOutputStream());
            long last = System.currentTimeMillis();
            while (true) {
                long now = System.currentTimeMillis();
                if (now - last > 1000) {
                    Message aux = buffer.getMessage();

                    System.out.println(aux);

                    if (aux != null) {
                        output.writeObject(aux);
                        output.flush();
                        output.reset();
                    }
                    last = now;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
