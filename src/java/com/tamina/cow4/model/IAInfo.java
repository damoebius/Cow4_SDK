package tamina.cow4.model;

import com.google.common.base.MoreObjects;

/**
 * @author kriyss
 */
public class IAInfo {

    private float id;
    private String name;
    private String avatar;
    private int pm = 1;

    public IAInfo() {
    }

    public IAInfo(float id, String name, String avatar) {
        this.id     = id;
        this.name   = name;
        this.avatar = avatar;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("avatar", avatar)
                .add("pm", pm)
                .toString();
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
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

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }
}
