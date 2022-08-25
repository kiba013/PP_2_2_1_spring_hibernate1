package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      UserService userService = context.getBean(UserService.class);

      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru",
              new Car("BMW",1)));
      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru",
              new Car("Camry", 75)));
      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru",
              new Car("Camry", 55)));
      userService.addUser(new User("User4", "Lastname4", "user4@mail.ru",
              new Car("w210", 210)));


      List<User> users = userService.getUserList();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.findByCar("w210", 210));
      context.close();
   }
}
