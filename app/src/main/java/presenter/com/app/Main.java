package presenter.com.app;

import presenter.com.app.buffers.MessageBuffer;
import presenter.com.app.core.Client;
import presenter.com.app.core.Teste;
import presenter.com.app.core.ThreadReceiver;
import presenter.com.app.core.ThreadSender;
import presenter.com.messages.Next;
import presenter.com.messages.Prev;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MessageBuffer buff = MessageBuffer.getInstance();
        Client c = new Client("127.0.0.1", 12345);
        c.start();
        new ThreadSender(c.getCliente()).start();
        new ThreadReceiver(c.getCliente()).start();
        Presenter p = new Presenter();

        new Teste(p).start();

        while (true) {
            System.out.println("Digita sua opção");
            Scanner s = new Scanner(System.in);
            int op = s.nextInt();
            switch (op) {
                case 1:
                    System.out.println("next");
                    buff.addMessage(new Next());
                    break;
                case 2:
                    System.out.println("prev");
                    buff.addMessage(new Prev());
                    break;
            }
        }
    }
}
