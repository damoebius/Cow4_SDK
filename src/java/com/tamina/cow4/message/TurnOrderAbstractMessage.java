package tamina.cow4.message;

public class TurnOrderAbstractMessage extends AbstractMessage {
    private Object data;

    public TurnOrderAbstractMessage() {
        this.setType("getTurnOrder");
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
