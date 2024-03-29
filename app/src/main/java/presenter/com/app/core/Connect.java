package presenter.com.app.core;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import presenter.com.app.buffers.ActionBuffer;
import presenter.com.app.buffers.MessageBuffer;
import presenter.com.messages.Action;
import presenter.com.messages.Message;

public class Connect extends AsyncTask<Void, Void, Void> {

    private String ip;
    private int port;
    private ActionBuffer actionBuffer;
    private MessageBuffer messageBuffer;
    private Socket socket;

    public Connect(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.messageBuffer = MessageBuffer.getInstance();

    }

    public boolean connect() {
        try {
            this.socket = new Socket(this.ip, this.port);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return this.socket != null;
    }

    public void send(ObjectOutputStream output) throws IOException {
        Message aux = messageBuffer.getMessage();
        if (aux != null) {
            output.writeObject(aux);
            output.flush();
            output.reset();
        }
    }

    public void recive(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Object aux = input.readObject();
        if (aux instanceof Action) {
            Action act = (Action) aux;
            actionBuffer.addAction(act);
        } else {
            Log.e("Error", "Não reconhecido");
        }

    }


    @Override
    protected Void doInBackground(Void... voids) {
        this.connect();
        try {
            ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());
            long last = System.currentTimeMillis();
            while (true) {
                long now = System.currentTimeMillis();
                if (now - last > 100) {
                    send(output);
                    // recive(input);
                    last = now;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
