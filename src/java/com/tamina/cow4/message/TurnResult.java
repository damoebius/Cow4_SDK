package tamina.cow4.message;

import tamina.cow4.model.IAInfo;

import java.util.List;

public class TurnResult extends Message {

    private IAInfo ia;
    private List<TurnAction> actions;

    public TurnResult() {
        this.setType("turnResult");
    }


    public IAInfo getIa() {
        return ia;
    }

    public void setIa(IAInfo ia) {
        this.ia = ia;
    }

    public List<TurnAction> getActions() {
        return actions;
    }

    public void setActions(List<TurnAction> actions) {
        this.actions = actions;
    }
}
