package crud.service;

import crud.model.User;

public interface UserService {
    public void addUser(User user);
    public User getUser(User user);
    //public User getUser(Long id);
    //public void deleteUser(long id);
    public void deleteUser(User user);
    public void updateUser(User user);
}
