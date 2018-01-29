package presenter.com.app.buffers;



import java.util.ArrayList;

import presenter.com.messages.Action;


public class ActionBuffer {
    private ArrayList<Action> actions;
    private static ActionBuffer instance;

    private ActionBuffer() {
        this.actions = new ArrayList();
    }

    public static ActionBuffer getInstance() {
        if (instance == null) {
            instance = new ActionBuffer();
        }
        return instance;
    }

    public Action getAction() {
        if (this.actions.size() == 0) {
            return null;
        }
        Action act = this.actions.get(0);
        this.actions.remove(0);
        return act;
    }

    public void addAction(Action act){
        this.actions.add(act);
    }
}
