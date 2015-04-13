package tamina.cow4.message;

import tamina.cow4.model.IAInfo;

import java.util.List;

public class TurnResult extends Message {

    private IAInfo ia;
    private List<GetTurnOrder> actions;

    public TurnResult() {
        this.setType("turnResult");
    }

    public TurnResult(IAInfo ia, List<GetTurnOrder> actions) {
        this.setType("turnResult");
        this.ia = ia;
        this.actions = actions;
    }

    public IAInfo getIa() {
        return ia;
    }

    public void setIa(IAInfo ia) {
        this.ia = ia;
    }

    public List<GetTurnOrder> getActions() {
        return actions;
    }

    public void setActions(List<GetTurnOrder> actions) {
        this.actions = actions;
    }
}
