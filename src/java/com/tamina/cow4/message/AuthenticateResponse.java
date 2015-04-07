package tamina.cow4.message;

import com.google.common.base.MoreObjects;

public class AuthenticateResponse extends AbstractMessage {
    private long id;

    public AuthenticateResponse() {
        this.setType("id");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", getType())
                .add("id", id)
                .toString();
    }
}