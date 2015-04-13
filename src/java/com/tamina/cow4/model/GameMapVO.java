package tamina.cow4.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author kriyss
 */
public class GameMapVO {
    private float id;
    private List<List<CellVO>> cells = Lists.newArrayList();
    private List<IAInfo> iaList = Lists.newArrayList();
    private int currentTurn     = 0;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public List<List<CellVO>> getCells() {
        return cells;
    }

    public void setCells(List<List<CellVO>> cells) {
        this.cells = cells;
    }

    public List<IAInfo> getIaList() {
        return iaList;
    }

    public void setIaList(List<IAInfo> iaList) {
        this.iaList = iaList;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
