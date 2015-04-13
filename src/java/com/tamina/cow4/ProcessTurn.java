package tamina.cow4;

import tamina.cow4.message.TurnAction;
import tamina.cow4.model.GameMapVO;

import java.util.List;

@FunctionalInterface
public interface ProcessTurn {
    List<TurnAction> processTurn(GameMapVO map);
}
