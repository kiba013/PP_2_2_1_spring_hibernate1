package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      Car car = new Car("white", 55);
      Car car1 = new Car("whiteAndBlack", 535);
      carService.addCar(car);
      carService.addCar(car1);


      UserService userService = context.getBean(UserService.class);
      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru", car));
      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru", car1));
      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", car));
      userService.addUser(new User("User4", "Lastname4", "user4@mail.ru", car));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      context.close();
   }
}
