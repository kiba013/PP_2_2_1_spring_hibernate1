package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findByCar(String model, int series) {
        TypedQuery<Car> query =
                sessionFactory.getCurrentSession().createQuery(
                                "FROM Car WHERE model = :model AND series = :series")
                        .setParameter("model", model)
                        .setParameter("series", series);
        List<Car> findCarList = query.getResultList();
        if (!findCarList.isEmpty()) {
            Car findCar = findCarList.get(0);
            List<User> ListUser = getUserList();
            User FindUser = ListUser.stream()
                    .filter(user -> user.getCar().equals(findCar))
                    .findAny()
                    .orElse(null);
            return FindUser;
        }
        return null;
    }
}
