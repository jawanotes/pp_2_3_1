package crud.dao;

import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    //public UserDaoImpl() {

    //}

    @Autowired
    public UserDaoImpl(@Qualifier("getEntityManager") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user); // cannot flush() here...
        /*
        entityManager.flush();
        entityManager.detach(user);*/
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
        user = entityManager.merge(user);
        entityManager.remove(user);
    }
    @Override
    public void updateUser(User user) {
/*        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        transaction.commit();*/
        user = entityManager.merge(user);
        entityManager.flush();
        entityManager.detach(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<?> getAllUsers() {
        //String query = "select * from users;";
        //EntityTransaction transaction = entityManager.getTransaction();

/*        CriteriaQuery<User> cr = entityManager.getCriteriaBuilder().createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Query<User> userQuery = entityManager.getCriteriaBuilder().createQuery(cr);
        List<User> results = userQuery.getResultList();*/
        /*Query userQuery = (Query) entityManager.getCriteriaBuilder().createQuery(User.class);
        List<User> results = userQuery.getResultList();*/
        //return results;


        //List<User> results = entityManager.getCriteriaBuilder().createQuery(User.class).getSelection().getResultList();

        //List<?> userList = entityManager.createNativeQuery(query).getResultList();
        //List<?> userList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList(); // Error "User is not mapped"
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        //return userList;

    }
}
