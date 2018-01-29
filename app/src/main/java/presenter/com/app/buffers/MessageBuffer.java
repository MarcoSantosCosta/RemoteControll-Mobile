package presenter.com.app.buffers;



import java.util.ArrayList;

import presenter.com.messages.Message;


public class MessageBuffer {
    private ArrayList<Message> messages;
    private static MessageBuffer instance;

    private MessageBuffer() {
        this.messages = new ArrayList();
    }

    public static MessageBuffer getInstance() {
        if (instance == null) {
            instance = new MessageBuffer();
        }
        return instance;
    }

    public Message getMessage() {
        if (this.messages.size() == 0) {
            return null;
        }
        Message msg = this.messages.get(0);
        this.messages.remove(0);
        return msg;
    }

    public void addMessage(Message act){
        this.messages.add(act);
      }
}
