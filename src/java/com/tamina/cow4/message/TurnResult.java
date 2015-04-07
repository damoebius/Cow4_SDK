package tamina.cow4.message;

import java.util.List;

public class TurnResult extends AbstractMessage {

    private Object ia;
    private List<Object> actions;

    public TurnResult() {
        this.setType("turnResult");
    }

    public List<Object> getActions() {
        return actions;
    }

    public void setActions(List<Object> actions) {
        this.actions = actions;
    }

    public Object getIa() {
        return ia;
    }

    public void setIa(Object ia) {
        this.ia = ia;
    }
}
