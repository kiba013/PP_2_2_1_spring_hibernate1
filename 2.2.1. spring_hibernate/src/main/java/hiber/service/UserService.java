package hiber.service;

import hiber.model.User;

import java.util.List;


public interface UserService {
    void addUser(User user);
    List<User> getUserList();
    List<User> findByCar(String model, int series);

}
