package tamina.cow4;

import tamina.cow4.message.GetTurnOrder;
import tamina.cow4.model.GameMapVO;

import java.util.List;

@FunctionalInterface
public interface ProcessTurn {
    List<GetTurnOrder> processTurn(GameMapVO map);
}
