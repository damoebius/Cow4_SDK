package tamina.cow4.message;

import com.google.common.base.MoreObjects;
import tamina.cow4.model.GameMapVO;

public class GetTurnOrder extends Message {
    private GameMapVO data;

    public GetTurnOrder() {
        this.setType("getTurnOrder");
    }

    public GameMapVO getData() {
        return data;
    }

    public void setData(GameMapVO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .toString();
    }
}
