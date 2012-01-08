package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.util.DataConversions;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Credentials extends AbstractComponent {

    private String username;

    private String password;

    private long usernameHash;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getUsernameHash() {
        return usernameHash;
    }

    public void setUsername(String username) {
        this.username = username;
        this.usernameHash = DataConversions.usernameToHash(username);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return (username.hashCode() * 31) | (password.hashCode() * 31);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof Credentials)) {
            return false;
        }

        Credentials c = (Credentials) o;
        return username.equals(c.username) && password.equals(c.password);
    }
}
