package hiber.dao;

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
    public List<User> findByCar(String model, int series) {
        TypedQuery<User> query =
               sessionFactory.getCurrentSession().createQuery(
                       "From User Where car.model = :model AND car.series = :series")
                       .setParameter("model", model)
                       .setParameter("series", series);
        if(query.getResultList().isEmpty()) {
            System.out.println("Sorry, Car by model '" + model + "' not found!");
        }
     return query.getResultList();
    }
}
