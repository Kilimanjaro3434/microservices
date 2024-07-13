package org.example.resttemplate;


import org.example.resttemplate.configuration.Config;
import org.example.resttemplate.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class RestTemplateApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);
        User user = new User(3L, "James", "Brown", (byte) 34);
        String response0 = communication.saveUser(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        String response2 = communication.editUser(user);

        String response3 = communication.deleteUser(user.getId());
        String sumResponse = response0 + response2 + response3;
        System.out.println(sumResponse);
        System.out.println(sumResponse.length());
    }

}
