package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Role> roles = new ArrayList<>();
}
