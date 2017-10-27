package domain.controller.data;

import domain.model.User;

public interface UserController<E extends User> extends DataController<E> {
    E login(String name, String pass);
}
