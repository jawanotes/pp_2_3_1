package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    /*    @Override
        public User getUser(Long id) {
            return userDao.getUser(id);
            //return null;
        }*/
/*    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }*/
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
