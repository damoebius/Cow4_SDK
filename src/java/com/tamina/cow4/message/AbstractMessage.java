package tamina.cow4.message;

public abstract class AbstractMessage {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
