package crud.dao;

import crud.model.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {
    //@Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.detach(user);
    }
/*    @Override
    public User getUser(Long id) {
        entityManager.find(User.class, user);
        return null;
    }*/

    @Override
    public User getUser(User user) {
        User result = entityManager.find(User.class, user.getId());
        //return entityManager.getReference(User.class, user.getId());
        entityManager.detach(result);
        return result;
    }

/*    @Override
    public void deleteUser(long id) {

    }*/
    @Override
    public void deleteUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        //entityManager.flush();
        transaction.commit();
    }
    @Override
    public void updateUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        entityManager.detach(user);
    }
}
