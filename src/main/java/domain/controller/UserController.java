package domain.controller;

public interface UserController<User> extends DataController<User> {
    User login(String name, String pass);
}
