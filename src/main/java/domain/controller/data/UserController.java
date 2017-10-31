package domain.controller.data;

import domain.model.User;

public interface UserController<E extends User> extends DataController<E> {
    boolean login(String name, String pass);
}
