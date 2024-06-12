package _3_java_core.multithreading_network.lesson_3.workshop;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private transient String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
