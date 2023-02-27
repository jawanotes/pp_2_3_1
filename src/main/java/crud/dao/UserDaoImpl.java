package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;


@Repository
public class UserDaoImpl implements UserDao {
    Logger logger = Logger.getLogger("DaoLogger");

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Nikita Nesterenko: персистенс контекст аннотации достаточно
     *
     * Harin Konstantin: Я пробовал делать тесты, нужен был конструктор
     */

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Nikita Nesterenko: зачем вообще метод дай юзера по юзеру? ))
     *           			убери везде комменты
     *
     * Harin konstantin: Поправил, отдаю юзера по юзеру.
     *                   Комментарии - привычка с прошлой работы, помогает видеть ход мысли. Они мне нужны.
     */
    @Override
    public User getUser(User user) {
        if (entityManager.contains(user)) {
            logger.info("Context contains user");
            return user;
        }
        logger.info("User isn't in context"); // Нельзя просто так взять и отдать юзера по юзеру
        return getUser(user.getId());
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(getUser(user));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<?> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
