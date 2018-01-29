package presenter.com.messages;

public class Action implements Message {
    private String type;

    public Action(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
