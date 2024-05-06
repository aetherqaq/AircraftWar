package edu.hitsz.ranking;

import java.io.IOException;
import java.util.List;

public interface UserDao {
    public abstract List<User> getAllUsers();
    public abstract void doAdd(User user);
    public abstract void printRanking();
    public abstract void update() throws IOException;
}