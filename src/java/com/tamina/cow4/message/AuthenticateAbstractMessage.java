package tamina.cow4.message;

import com.google.common.base.MoreObjects;

public class AuthenticateAbstractMessage extends Message {

    private String name;
    private String avatar;

    public AuthenticateAbstractMessage() {
        this.setType("authenticate");
    }

    public AuthenticateAbstractMessage(String name, String avatar) {
        this.setType("authenticate");
        this.name   = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", getType())
                .add("name", name)
                .add("avatar", avatar)
                .toString();
    }
}