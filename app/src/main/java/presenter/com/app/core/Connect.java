package presenter.com.app.core;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import presenter.com.app.buffers.MessageBuffer;
import presenter.com.messages.Message;

public class Connect extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        MessageBuffer buffer = MessageBuffer.getInstance();
        try {
            Socket client = new Socket("192.168.0.101", 12345);
            try {
                ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
