package edu.hitsz.ranking;

import java.io.IOException;
import java.util.List;

public interface UserDao {
    public abstract List<User> getAllUsers();
    public abstract void doAdd(User user);
    public abstract void update() throws IOException;
    public abstract void doDelete(int num);
}