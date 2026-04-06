package utils;

import dto.User;
import net.datafaker.Faker;

import static utils.PropertiesReader.getProperty;

public class UserFactory {
   static Faker faker = new Faker();
     public  static User positiveUser(){
         User user = new User(faker.internet().emailAddress(),getProperty("base.properties", "password"));
         return  user;
     }
}
