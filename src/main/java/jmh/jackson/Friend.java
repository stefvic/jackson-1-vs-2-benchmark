package jmh.jackson;

import java.util.Objects;

/**
 * @author erabii
 *
 */
public class Friend {

    public String id;

    public String name;

    public Friend() {

    }

    public Friend(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Friend)) {
            return false;
        }

        Friend friend = (Friend) o;

        if (id != null ? !id.equals(friend.id) : friend.id != null) {
            return false;
        }
        return name != null ? name.equals(friend.name) : friend.name == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Friend{" + "id=" + id + ", name=" + name + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
