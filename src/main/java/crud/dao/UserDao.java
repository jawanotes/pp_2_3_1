package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);
    //public User getUser(Long id);
    public User getUser(User user);
    //public void deleteUser(long id);
    public void deleteUser(User user);
    public void updateUser(User user);

    public List<?> getAllUsers();
}
