package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      UserService userService = context.getBean(UserService.class);

//      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru",
//              new Car("BMW",735)));
//      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru",
//              new Car("Camry", 75)));
//      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru",
//              new Car("Camry", 55)));
//      userService.addUser(new User("kiba", "kiba", "user4@mail.ru",
//              new Car("BMW", 735)));


      List<User> users = userService.getUserList();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }


      List<User> usersByCar = userService.findByCar("ho", 75);
      for (User user : usersByCar) {
         System.out.println("User firstname: " + user.getFirstName());
         System.out.println("User lastname: " + user.getLastName());
         System.out.println("User's car: " + user.getCar());
         System.out.println();
      }
      context.close();
   }
}
