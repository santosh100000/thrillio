package com.thrillio.services;

import com.thrillio.constants.Gender;
import com.thrillio.constants.UserType;
import com.thrillio.dao.UserDao;
import com.thrillio.entities.User;

import java.util.List;

public class UserService {
    private static UserService instance = new UserService();
    private static UserDao userDao = new UserDao();


    private UserService(){}

    public static UserService getInstance(){
        return instance; }

    //We are creating new user
    public User createUser(long id, String email, String password, String firstName,
                           String lastName, Gender gender, UserType userType){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setUserType(userType);

        return user;

    }

    public List<User> getUsers(){
        return userDao.getUsers();
    }


    public User getUser(long userId){

        User user = new UserDao().getUser(userId);
        return  user;
    }



}
