package domain.controller.data;

import domain.model.User;

public interface UserDataController<E extends User> extends DataController<E> {
    boolean login(String name, String pass);
}
