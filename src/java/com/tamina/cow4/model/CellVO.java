package tamina.cow4.model;

/**
 * @author kriyss
 */
public class CellVO {

    private float  id;
    private float  top;
    private float  bottom;
    private float  left;
    private float  right;
    private IAInfo occupant;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public IAInfo getOccupant() {
        return occupant;
    }

    public void setOccupant(IAInfo occupant) {
        this.occupant = occupant;
    }
}
